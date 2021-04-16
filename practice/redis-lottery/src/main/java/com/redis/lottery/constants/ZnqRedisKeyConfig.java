package com.redis.lottery.constants;

import com.redis.lottery.utils.DateUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * 周年庆活动redis-key的配置表
 */
@Slf4j
public class ZnqRedisKeyConfig {
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

    /**
     * 获取直播间相关的信息的redis key
     * @param targetMasterId 抽奖所在直播间对应的主播ID
     * @return
     */
    public static String getLiveRoomInfoKey(String targetMasterId){
        return ROOM_INFO_PREFIX + SPLIT_FLAG + targetMasterId + SPLIT_FLAG + DateUtils.getCurrentDate();
    }

    /**
     * 获取奖品相关信息的 redis key
     * @param prizeId
     * @return
     */
    public static String getPrizeInfoKey(String prizeId){
        return PRIZE_INFO_PREFIX + SPLIT_FLAG + prizeId + SPLIT_FLAG + DateUtils.getCurrentDate();
    }


    /**
     *
     * @param type 每个任务阶段类型的标识
     * @return
     */
    public static String getPrizeIdPoolKey(int type){
        Assert.isTrue(type >= 1 && type <= 3, "the znq task type must in [1,3] !!!");
        return String.format("%s%s%s%s%s", PRIZE_ID_POOL_PREFIX, SPLIT_FLAG,type,SPLIT_FLAG,DateUtils.getCurrentDate());
    }

}
