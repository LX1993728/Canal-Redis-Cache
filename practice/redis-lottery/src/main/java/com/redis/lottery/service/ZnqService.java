package com.redis.lottery.service;

import com.redis.lottery.service.impl.ZnqServiceImpl;
import com.redis.lottery.vo.ZnqLotteryVO;
import com.redis.lottery.vo.ZnqPrizeVO;
import com.redis.lottery.vo.ZnqRoomInfoVO;

/**
 * 周年庆活动
 */
public interface ZnqService {
    /**
     * 判断周年庆活动是否开启
     * @return
     */
    boolean isZnqTurnOn();

    /**
     * 每天的重置操作
     */
    void clearAndResetEveryDay();

    /**
     * 从redis获取当天某一项奖品的信息
     * @param prizeId
     * @return
     */
    public ZnqPrizeVO getTodayPrizeInfoFromPrizeId(Long prizeId);

    /**
     * 处理并获取roominfo
     * @param targetMasterId
     * @param setRedis
     * @param action
     * @return
     */
    public ZnqRoomInfoVO resolveAndGetRoomInfo(Long targetMasterId,  boolean setRedis,ZnqServiceImpl.UpdateRoomAction<ZnqRoomInfoVO> action);

    // 抽奖逻辑
    public ZnqLotteryVO lottery(Long masterId, Long targetMasterId);
}
