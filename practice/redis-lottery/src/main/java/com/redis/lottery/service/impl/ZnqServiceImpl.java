package com.redis.lottery.service.impl;

import cn.hutool.core.lang.Assert;
import com.redis.lottery.constants.ZnqRedisKeyConfig;
import com.redis.lottery.domains.ZnqPrize;
import com.redis.lottery.service.ZnqService;
import com.redis.lottery.utils.DateUtils;
import com.redis.lottery.utils.JedisUtils;
import com.redis.lottery.vo.ZnqLotteryVO;
import com.redis.lottery.vo.ZnqPrizeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    }

    @Override
    public ZnqPrizeVO getTodayPrizeInfoFromPrizeId(Long prizeId){
        Assert.notNull(prizeId);
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
    public ZnqLotteryVO lottery(Long masterId, Long targetMasterId) {
        final String roomInfoKey = ZnqRedisKeyConfig.getLiveRoomInfoKey(Long.toString(targetMasterId));
        final Boolean exists = jedisUtils.exists(roomInfoKey);
        if (!exists){


        }

        return null;
    }


    // ------------------------- the base is all private methods -----------------------

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
