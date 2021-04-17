package com.redis.lottery.service;

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
     * 往redis设置当天某一项奖品的信息(新建覆盖类型)
     * @param prizeId
     * @return
     */
    public boolean setTodayPrizeInfoFromPrizeEntity(Long prizeId);

    /**
     *  redis更新当天某一项奖品的属性信息(存在修改型号)
     * @param prizeVO
     * @return
     */
    public boolean updateTodayPrizeInfoFromPrizeVO(ZnqPrizeVO prizeVO);

    /**
     *
     * @param targetRoomId
     * @return
     */
    public ZnqRoomInfoVO updateTodayRoomInfo(ZnqRoomInfoVO znqRoomInfoVO);

    // 抽奖逻辑
    public ZnqLotteryVO lottery(Long masterId, Long targetMasterId);
}
