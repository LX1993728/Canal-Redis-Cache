package com.redis.lottery.service.impl;

import com.alibaba.fastjson.JSON;
import com.redis.lottery.constants.ZnqRedisKeyConfig;
import com.redis.lottery.domains.ZnqPrize;
import com.redis.lottery.service.ZnqService;
import com.redis.lottery.utils.DateUtils;
import com.redis.lottery.utils.JedisUtils;
import com.redis.lottery.vo.ZnqLotteryVO;
import com.redis.lottery.vo.ZnqPrizeItemVO;
import com.redis.lottery.vo.ZnqPrizeVO;
import com.redis.lottery.vo.ZnqRoomInfoVO;
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
public class ZnqServiceImpl implements ZnqService {
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
        String znqPattern = ZnqRedisKeyConfig.getZNQ_PREFIX() + "_*";
        Set<String> znqKeys = jedisUtils.action(jedis -> jedis.keys(znqPattern));
        if (znqKeys != null && !znqKeys.isEmpty()){
            long delCount  = jedisUtils.action(jedis -> jedis.del(znqKeys.toArray(new String[]{})));
            log.info("清理周年庆keys 清理个数:{}个, 已重置删除完毕", delCount);
        }else {
            log.info("不存在历史周年庆活动的key, 无需删除...");
        }

        // 初始化奖品信息
        final String prizeIdPool1key = ZnqRedisKeyConfig.getPrizeIdPoolKey(1);
        final String prizeIdPool2key = ZnqRedisKeyConfig.getPrizeIdPoolKey(2);
        final String prizeIdPool3key = ZnqRedisKeyConfig.getPrizeIdPoolKey(3);
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

        // test drawn
        lottery(1L, 2L);
    }

    @Override
    public ZnqPrizeVO getTodayPrizeInfoFromPrizeId(Long prizeId){
        Assert.notNull(prizeId, "prizeId cannot be null");
        final String prizeInfoKey = ZnqRedisKeyConfig.getPrizeInfoKey(Long.toString(prizeId));
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
    public boolean setTodayPrizeInfoFromPrizeEntity(Long prizeId){
        ZnqPrize zp = null;
        try {
            zp = entityManager.find(ZnqPrize.class, prizeId);
        }catch (Exception e){
            log.error("cannot find prize by id={}", prizeId);
        }
        return setPrizeInfoFromPrizeEntity(zp);
    }

    @Override
    public boolean updateTodayPrizeInfoFromPrizeVO(ZnqPrizeVO prizeVO){
        final String prizeInfoKey = ZnqRedisKeyConfig.getPrizeInfoKey(Long.toString(prizeVO.getId()));
        final Boolean exists = jedisUtils.exists(prizeInfoKey);
        if (!exists){
            log.error("prizeinfo not exists for key {}", prizeInfoKey);
            return false;
        }
        if (prizeVO.getName() != null){
            jedisUtils.hSet(prizeInfoKey, "name", prizeVO.getName());
        }
        if (prizeVO.getBroad() != null){
            jedisUtils.hSet(prizeInfoKey, "broad", Integer.toString(prizeVO.getBroad()));
        }
        if (prizeVO.getProbability() != null){
            jedisUtils.hSet(prizeInfoKey, "probability", Double.toString(prizeVO.getProbability()));
        }
        if (prizeVO.getTotal() != null){
            jedisUtils.hSet(prizeInfoKey, "total", Integer.toString(prizeVO.getTotal()));
        }
        if (prizeVO.getIssued() != null){
            jedisUtils.hSet(prizeInfoKey, "issued", Integer.toString(prizeVO.getIssued()));
        }
        if (prizeVO.getTypes() != null){
            jedisUtils.hSet(prizeInfoKey, "types", prizeVO.getTypes());
        }
        return true;
    }

    @Override
    public ZnqRoomInfoVO updateTodayRoomInfo(ZnqRoomInfoVO znqRoomInfoVO) {
        final Long targetMasterId = znqRoomInfoVO.getMasterId();
        Assert.notNull(targetMasterId, "targetid must not be mull !!!");
        final ZnqRoomInfoVO roomInfoVO = initOrGetRoomInfo(targetMasterId);
        Assert.notNull(roomInfoVO, "roomInfoVO must not be mull !!!");
        return null;
    }

    @Override
    public ZnqLotteryVO lottery(Long masterId, Long targetMasterId) {
        final String roomInfoKey = ZnqRedisKeyConfig.getLiveRoomInfoKey(Long.toString(targetMasterId));
        ZnqRoomInfoVO znqRVO = null;
        // 抽奖锁 LockPrefix = znq_lottery_master_{targetMasterId}
        // 更新锁 LockPrefix = znq_update_master_{targetMasterId} / znq_prize_{prizeId}

        // 抽奖锁 start Lock key: targetMasterId 一个直播间只能由一个人抽奖
        final Boolean exists = jedisUtils.exists(roomInfoKey);
        if (!exists){
            //init create ZnqRoomInfoVO to redis use fast json
            znqRVO = new ZnqRoomInfoVO(targetMasterId, 0, 0, new HashSet<>(), 0, 999, 0);
            String znqRVOStr = JSON.toJSONString(znqRVO);
            jedisUtils.set(roomInfoKey, znqRVOStr);
        }

        if (znqRVO == null){
            String znqRRVOStr = jedisUtils.get(roomInfoKey);
            znqRVO = JSON.parseObject(znqRRVOStr, ZnqRoomInfoVO.class);
        }

        if (!znqRVO.canDrawn()){
            log.warn("不允许抽奖(任务未完成或已经抽完)");
            return null;
        }
        String prizeIdPoolKey = ZnqRedisKeyConfig.getPrizeIdPoolKey(znqRVO.getType());
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
        // 只抽一次 如果没抽中 或者 奖品没了 不重新抽
        Random rand = new Random();
        int num = rand.nextInt((a - min) + 1) + min;
        for (ZnqPrizeItemVO item : itemVOS){
            if (item.drawn(num)){
                prizeId = item.getPrizeId();
            }
        }

        log.info("random={} prizeId={}", num, prizeId);

        if (prizeId != null){
            // lock prizeId

            // release lock prizeId
        }

        // 抽完 更新roomInfo
        String newStr = jedisUtils.get(roomInfoKey);
        znqRVO = JSON.parseObject(newStr, ZnqRoomInfoVO.class);
        znqRVO.setDrawn(znqRVO.getDrawn() + 1);
        jedisUtils.set(roomInfoKey, JSON.toJSONString(znqRVO));

        // 外围锁 release Lock

        // 如果抽中存储抽奖记录
        return null;
    }


    // ------------------------- the base is all private methods -----------------------

    private ZnqRoomInfoVO initOrGetRoomInfo(Long targetMasterId){
        final String roomInfoKey = ZnqRedisKeyConfig.getLiveRoomInfoKey(Long.toString(targetMasterId));
        ZnqRoomInfoVO znqRVO = null;
        final Boolean exists = jedisUtils.exists(roomInfoKey);
        if (!exists){
            znqRVO = new ZnqRoomInfoVO(targetMasterId, 0, 0, new HashSet<>(), 0, 999, 0);
            String znqRVOStr = JSON.toJSONString(znqRVO);
            jedisUtils.set(roomInfoKey, znqRVOStr);
        }

        if (znqRVO == null){
            String znqRRVOStr = jedisUtils.get(roomInfoKey);
            znqRVO = JSON.parseObject(znqRRVOStr, ZnqRoomInfoVO.class);
        }
        return znqRVO;
    }

    private boolean setPrizeInfoFromPrizeEntity(ZnqPrize zp){
        if (zp == null){
            log.error("zp cannot be null !!!");
            return false;
        }
        final String prizeInfoKey = ZnqRedisKeyConfig.getPrizeInfoKey(Long.toString(zp.getId()));
        Map<String, String> map =  getMapFromPrizeEntity(zp);
        String result = jedisUtils.hmSet(prizeInfoKey, map);
        log.info(result);
        return true;
    }

    private Map<String, String> getMapFromPrizeEntity(ZnqPrize zp){
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
        return prizeVoMap;
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
