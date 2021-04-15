package com.redis.lottery.service.impl;

import com.redis.lottery.constants.ZnqRedisKeyConfig;
import com.redis.lottery.service.ZnqLotteryService;
import com.redis.lottery.utils.DateUtils;
import com.redis.lottery.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;

@Slf4j
@Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {Exception.class})
@Service
public class ZnaLotteryServiceImpl implements ZnqLotteryService {
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
        // TODO: Jedis 新建活动相关的数据结构 集合 以及Hash对应的对象...
    }


}
