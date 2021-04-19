package com.redis.lottery.constants;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 周年庆活动redis-key的配置表
 */
@Slf4j
public class ZnqKeyConfig {
    @Getter
    private static final String ZNQ_PREFIX = "ZNQ"; // 所有周年庆活动的标识
    // 周年庆活动 key的连接符
    private static final String SPLIT_FLAG = "_";

    // 所有key的超时时间
    @Getter
    private static final long EXPIRE_TIME = TimeUnit.DAYS.toSeconds(1) + 10;

    // 周年庆活动 每个直播间信息的前缀 JSON结构
    private static final  String ROOM_INFO_PREFIX = ZNQ_PREFIX +SPLIT_FLAG + "ROOMINFO";

    // 每个奖品的统一前缀 HASH 结构
    private static final String PRIZE_INFO_PREFIX = ZNQ_PREFIX + SPLIT_FLAG + "PRIZEINFO";

    // 每个奖池统一前缀 ZSET 结构
    private static final String PRIZE_ID_POOL_PREFIX = ZNQ_PREFIX + SPLIT_FLAG + "PRIZEIDPOOL";

    // 某一粉丝当天在一个直播间的抽奖次数
    private static final String FAN_LOTTERY_FOR_ROOM_PREFIX = ZNQ_PREFIX + SPLIT_FLAG + "FANLOTTERY";

    // 统一的锁标识
    private static final String LOCKPREFIX = ZNQ_PREFIX + SPLIT_FLAG + "LOCK";

    /**
     * 获取直播间相关的信息的redis key
     * @param targetMasterId 抽奖所在直播间对应的主播ID
     * @return
     */
    public static String getLiveRoomInfoKey(String targetMasterId){
        return ROOM_INFO_PREFIX + SPLIT_FLAG + targetMasterId + SPLIT_FLAG + getCurrentDate();
    }

    /**
     * 获取奖品相关信息的 redis key
     * @param prizeId
     * @return
     */
    public static String getPrizeInfoKey(String prizeId){
        return PRIZE_INFO_PREFIX + SPLIT_FLAG + prizeId + SPLIT_FLAG + getCurrentDate();
    }


    /**
     *
     * @param type 每个任务阶段类型的标识
     * @return
     */
    public static String getPrizeIdPoolKey(int type){
        Assert.isTrue(type >= 1 && type <= 3, "the znq task type must in [1,3] !!!");
        return String.format("%s%s%s%s%s", PRIZE_ID_POOL_PREFIX, SPLIT_FLAG,type,SPLIT_FLAG, getCurrentDate());
    }

    /**
     * 创建修改更新room info时使用
     * @param targetMasterId
     * @return
     */
    public static String getLockUpdateRoomInfoKey(String targetMasterId){
        return String.format("%s%s%s%s%s%s%s", LOCKPREFIX,SPLIT_FLAG, "UPDATEROOMINFO", SPLIT_FLAG, targetMasterId, SPLIT_FLAG, getCurrentDate());
    }

    /**
     * 创建修改更新prize info时使用
     * @param prizeId
     * @return
     */
    public static String getLockUpdatePrizeInfoKey(String prizeId){
        return String.format("%s%s%s%s%s%s%s", LOCKPREFIX,SPLIT_FLAG, "UPDATEPRIZEINFO", SPLIT_FLAG, prizeId, SPLIT_FLAG, getCurrentDate());
    }

    /**
     *
     * @param masterId fan masterId
     * @param targetMasterId
     * @return
     */
    public static String getFanLotteryForRoomKey(String masterId, String targetMasterId){
        return String.format("%s%s%s%s%s%s%s", FAN_LOTTERY_FOR_ROOM_PREFIX, SPLIT_FLAG,masterId, SPLIT_FLAG, targetMasterId,SPLIT_FLAG, getCurrentDate());
    }


    /**
     * 获取当前的日期（19000101）
     * @return
     */
    private static int getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(df.format(new Date()));
    }

}
