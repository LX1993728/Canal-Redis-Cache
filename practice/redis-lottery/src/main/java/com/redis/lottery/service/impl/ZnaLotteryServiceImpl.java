package com.redis.lottery.service.impl;

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





}
