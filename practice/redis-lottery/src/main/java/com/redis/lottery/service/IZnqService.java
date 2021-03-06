package com.redis.lottery.service;

import com.redis.lottery.constants.ZnqRoomAction;
import com.redis.lottery.domains.ZnqPrize;
import com.redis.lottery.vo.ZnqLotteryVO;
import com.redis.lottery.vo.ZnqPrizeVO;
import com.redis.lottery.vo.ZnqRoomInfoVO;

import java.util.List;

/**
 * 周年庆活动
 */
public interface IZnqService {
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
    public ZnqRoomInfoVO resolveAndGetRoomInfo(Long targetMasterId, boolean setRedis, ZnqRoomAction<ZnqRoomInfoVO> action);

    // 抽奖逻辑
    public ZnqLotteryVO lottery(Long masterId, Long targetMasterId);

    public List<ZnqPrize> getAndMonitorPrizes();
    // TODO:// 封装活动任务(魅力值/分享/弹幕/蛋糕) 业务逻辑方法
}
