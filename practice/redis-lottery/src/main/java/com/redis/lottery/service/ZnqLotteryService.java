package com.redis.lottery.service;

/**
 * 周年庆活动
 */
public interface ZnqLotteryService {
    /**
     * 判断周年庆活动是否开启
     * @return
     */
    boolean isZnqTurnOn();

    /**
     * 每天的重置操作
     */
    void clearAndResetEveryDay();
}
