package com.redis.lottery.service.impl;

import com.alibaba.fastjson.JSON;
import com.redis.lottery.constants.ZnqKeyConfig;
import com.redis.lottery.constants.ZnqRoomAction;
import com.redis.lottery.domains.ZnqPrize;
import com.redis.lottery.service.IZnqService;
import com.redis.lottery.utils.DateUtils;
import com.redis.lottery.utils.JedisUtils;
import com.redis.lottery.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Tuple;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {Exception.class})
@Service
public class ZnqServiceImpl implements IZnqService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JedisUtils jedisUtils;

    @Value("${znq.begin}")
    private String beginTimeStr;

    @Value("${znq.end}")
    private String endTimeStr;


    @Override
    public boolean isZnqTurnOn() {
        final Date nowDate = new Date();
        return DateUtils.belongCalendar(nowDate, beginTimeStr, endTimeStr);
    }

    @Override
    public void clearAndResetEveryDay() {
        String znqPattern = ZnqKeyConfig.getZNQ_PREFIX() + "_*";
        Set<String> znqKeys = jedisUtils.action(jedis -> jedis.keys(znqPattern));
        if (znqKeys != null && !znqKeys.isEmpty()){
            long delCount  = jedisUtils.action(jedis -> jedis.del(znqKeys.toArray(new String[]{})));
            log.info("清理周年庆keys 清理个数:{}个, 已重置删除完毕", delCount);
        }else {
            log.info("不存在历史周年庆活动的key, 无需删除...");
        }

        // 初始化奖品信息
        final String prizeIdPool1key = ZnqKeyConfig.getPrizeIdPoolKey(1);
        final String prizeIdPool2key = ZnqKeyConfig.getPrizeIdPoolKey(2);
        final String prizeIdPool3key = ZnqKeyConfig.getPrizeIdPoolKey(3);
        final List<ZnqPrize> znqPrizes = entityManager.createQuery(
                "SELECT zp FROM ZnqPrize zp WHERE zp.starDiamonds <> -2 ORDER BY zp.probability ASC",
                ZnqPrize.class).getResultList();
        for (ZnqPrize zp : znqPrizes){
            log.debug("{}-{}-{}", zp.getId(), zp.getName(), zp.getProbability());
            setPrizeInfoFromPrizeEntity(zp);
            final Set<Integer> types = getTypesFromStr(zp.getTypes());
            if (types.contains(1)){
                jedisUtils.action(jedis -> jedis.zadd(prizeIdPool1key, zp.getProbability(), Long.toString(zp.getId())));
            }
            if (types.contains(2)){
                jedisUtils.action(jedis -> jedis.zadd(prizeIdPool2key, zp.getProbability(), Long.toString(zp.getId())));
            }
            if (types.contains(3)){
                jedisUtils.action(jedis -> jedis.zadd(prizeIdPool3key, zp.getProbability(), Long.toString(zp.getId())));
            }
        }

        // test
        jedisUtils.action(jedis -> jedis.zrem(prizeIdPool1key, Long.toString(100)));
    }

    @Override
    public ZnqPrizeVO getTodayPrizeInfoFromPrizeId(Long prizeId){
        Assert.notNull(prizeId, "prizeId cannot be null");
        final String prizeInfoKey = ZnqKeyConfig.getPrizeInfoKey(Long.toString(prizeId));
        final Boolean exists = jedisUtils.exists(prizeInfoKey);
        if (exists){
            ZnqPrizeVO prizeVO = new ZnqPrizeVO();
            Map<String, String> map = jedisUtils.hGet(prizeInfoKey);
            prizeVO.setId(Long.parseLong(map.get("id")));
            prizeVO.setName(map.get("name"));
            prizeVO.setBroad(Integer.parseInt(map.get("broad")));
            prizeVO.setProbability(Double.parseDouble(map.get("probability")));
            prizeVO.setTotal(Integer.parseInt(map.get("total")));
            prizeVO.setIssued(Integer.parseInt(map.get("issued")));
            prizeVO.setTypes(map.get("types"));
            return prizeVO;
        }
        return null;
    }

    @Override
    public ZnqRoomInfoVO resolveAndGetRoomInfo(Long targetMasterId, boolean setRedis, ZnqRoomAction<ZnqRoomInfoVO> action) {
        // TODO://update room lock
        Assert.notNull(targetMasterId, "targetid must not be mull !!!");
        final String roomInfoKey = ZnqKeyConfig.getLiveRoomInfoKey(Long.toString(targetMasterId));
        ZnqRoomInfoVO znqRVO = null;
        final Boolean exists = jedisUtils.exists(roomInfoKey);
        if (!exists){
            znqRVO = new ZnqRoomInfoVO(targetMasterId, 0, 0, new HashSet<>(), 0, 999);
            String znqRVOStr = JSON.toJSONString(znqRVO);
            jedisUtils.set(roomInfoKey, znqRVOStr);
        }
        if(!setRedis){
            // TODO:// release update lock
        }

        if (znqRVO == null){
            String znqRRVOStr = jedisUtils.get(roomInfoKey);
            znqRVO = JSON.parseObject(znqRRVOStr, ZnqRoomInfoVO.class);
        }
        Assert.notNull(znqRVO, "roomInfoVO must not be mull !!!");
        ZnqRoomInfoVO infoVO = action.action(znqRVO, roomInfoKey);
        if (setRedis){
            jedisUtils.set(roomInfoKey, JSON.toJSONString(infoVO));
            // TODO:// release update lock
        }
        return infoVO;
    }


    @Override
    public ZnqLotteryVO lottery(Long masterId, Long targetMasterId) {
        final ZnqRoomInfoVO znqRVO = resolveAndGetRoomInfo(targetMasterId, false, (roomInfoVO, key) -> roomInfoVO);
        final ZnqRoomInfoVO roomInfoVO = resolveAndGetRoomInfo(targetMasterId, false, (roomInfoVO1, key) -> roomInfoVO1);
        final ZnqTaskConfigVO taskConfig = getTaskConfig(false);
        final ZnqRoomInfoVO.Info info = roomInfoVO.acquireInfoResult(taskConfig);
       if (info.getDrawn() != 0){
            log.warn("不允许抽奖(任务未完成或已经抽完)");
            return null;
        }
        String prizeIdPoolKey = ZnqKeyConfig.getPrizeIdPoolKey(info.getType());
        Set<Tuple> prizets = jedisUtils.action(jedis -> jedis.zrangeWithScores(prizeIdPoolKey, 0, 100));
        final int a = 1000000;
        List<ZnqPrizeItemVO> itemVOS = new ArrayList<>();
        int temp = 1;
        for (Tuple t : prizets){
            String id = t.getElement();
            int begin = temp;
            int end = (int) (a * t.getScore() * 0.01 + temp);
            itemVOS.add(new ZnqPrizeItemVO(Long.parseLong(id), begin, end));
            temp = end + 1;
        }

       for (ZnqPrizeItemVO vo : itemVOS){
           log.info("{}", vo);
       }

        Long prizeId = null;
        int min = 1;
        Random rand = new Random();
        int num = rand.nextInt((a - min) + 1) + min;
        for (ZnqPrizeItemVO item : itemVOS){
            if (item.drawn(num)){
                prizeId = item.getPrizeId();
            }
        }
        log.info("random={} prizeId={}", num, prizeId);

        boolean hasLottery = false;
        if (prizeId != null){
           boolean resolved = resolveIssuedAndGetPrizedInfo(prizeId);
           if (resolved){
               hasLottery = true;
           }
        }
       // update fan lottery count in the room
        updateFanLotteryCountInRoom(masterId, targetMasterId, info);

        if (hasLottery){
            // TODO:// 抽中奖品后的逻辑 DelayTask
            // 如果抽中则进行以下操作
            // 1. 存储抽奖记录
            // 2. 判断获奖类型 如果是星钻或特效则延时发放到粉丝 账户
            // 3. 发送TCP消息到直播间，如果broad 则全站广播

            // 且封装ZnqLotteryVO 并返回

        }else {
            return null;
        }
        return null;
    }




    // ------------------------- the base is all private methods -----------------------
    // 更新某粉丝在某直播间的已抽奖次数
    private boolean updateFanLotteryCountInRoom(Long masterId, Long targetMasterId, ZnqRoomInfoVO.Info info){
        // update self, so no need a lock
        final int drawn = info.getDrawn();
        if (drawn == 0){
            log.warn("the master room havent completed any task");
            return false;
        }
        final int lotteryCount = getFanLotteryCountInRoom(masterId, targetMasterId);
        if (lotteryCount < drawn){
            final String lotteryForRoomKey = ZnqKeyConfig.getFanLotteryForRoomKey(Long.toString(masterId), Long.toString(targetMasterId));
            jedisUtils.set(lotteryForRoomKey, Integer.toString(lotteryCount + 1));
            return true;
        }
        return false;
    }

    // 获取某粉丝在某直播间的已抽奖次数
    private Integer getFanLotteryCountInRoom(Long masterId, Long targetMasterId){
        final String lotteryForRoomKey = ZnqKeyConfig.getFanLotteryForRoomKey(Long.toString(masterId), Long.toString(targetMasterId));
        final Boolean exists = jedisUtils.exists(lotteryForRoomKey);
        if (!exists){
            jedisUtils.set(lotteryForRoomKey, "0");
        }
        return Integer.parseInt(jedisUtils.get(lotteryForRoomKey));
    }

    private boolean resolveIssuedAndGetPrizedInfo(Long prizeId){
        // TODO://lock prize
        final String prizeInfoKey = ZnqKeyConfig.getPrizeInfoKey(Long.toString(prizeId));
        final Boolean exists = jedisUtils.exists(prizeInfoKey);
        Assert.isTrue(exists, "not exist prizeInfo !!!");
        boolean result;
        int total = Integer.parseInt(jedisUtils.hGet(prizeInfoKey, "totol"));
        int issued = Integer.parseInt(jedisUtils.hGet(prizeInfoKey, "issued"));
        if (issued < total){
            jedisUtils.hSet(prizeInfoKey, "issued", Integer.toString(issued + 1));
            result = true;
        }else {
            // remove prize from pool
            String pool1Key = ZnqKeyConfig.getPrizeIdPoolKey(1);
            String pool2Key = ZnqKeyConfig.getPrizeIdPoolKey(2);
            String pool3Key = ZnqKeyConfig.getPrizeIdPoolKey(3);
            jedisUtils.action(jedis -> jedis.zrem(pool1Key, Long.toString(prizeId)));
            jedisUtils.action(jedis -> jedis.zrem(pool2Key, Long.toString(prizeId)));
            jedisUtils.action(jedis -> jedis.zrem(pool3Key, Long.toString(prizeId)));
            result =  false;
        }

        // end TODO://lock prize
        return result;
    }

    /**
     * 获取
     * @param isReset 是否重新设置并获取
     * @return
     */
    private ZnqTaskConfigVO getTaskConfig(boolean isReset){
        if (isReset){
            // TODO:// 直接从数据库查询出来 封装VO 传输到redis
        }else {
            // TODO:// 判断redis是否存在对应的配置内容 KV ， 如果存在则直接获取JSON-VO return 否则则从数据库查询传输VO到redis return VO
        }
        return null;
    }

    private void setPrizeInfoFromPrizeEntity(ZnqPrize zp){
        final String prizeInfoKey = ZnqKeyConfig.getPrizeInfoKey(Long.toString(zp.getId()));
        final ZnqPrizeVO prizeVO = new ZnqPrizeVO(zp.getId(), zp.getName(), zp.getProbability(), zp.getTotal(), 0, zp.getBroad(), zp.getTypes());
        Map<String, String> prizeVoMap = new HashMap<>();
        if (prizeVO.getId() != null){
            prizeVoMap.put("id", Long.toString(prizeVO.getId()));
        }
        if (prizeVO.getName() != null){
            prizeVoMap.put("name", prizeVO.getName());
        }
        if (prizeVO.getBroad() != null){
            prizeVoMap.put("broad", Integer.toString(prizeVO.getBroad()));
        }
        if (prizeVO.getProbability() != null){
            prizeVoMap.put("probability", Double.toString(prizeVO.getProbability()));
        }
        if (prizeVO.getTotal() != null){
            prizeVoMap.put("total", Integer.toString(prizeVO.getTotal()));
        }
        if (prizeVO.getIssued() != null){
            prizeVoMap.put("issued", Integer.toString(prizeVO.getIssued()));
        }
        if (prizeVO.getTypes() != null){
            prizeVoMap.put("types", prizeVO.getTypes());
        }
        String result = jedisUtils.hmSet(prizeInfoKey, prizeVoMap);
        log.info(result);
    }

    private Set<Integer> getTypesFromStr(String types){
        Set<Integer> typesSet = new HashSet<>();
        if (types == null){
            return typesSet;
        }
        final String[] split = types.split(",");
        if (split.length > 0){
            for (String t : split){
                typesSet.add(Integer.parseInt(t));
            }
        }
        return typesSet;
    }

}
