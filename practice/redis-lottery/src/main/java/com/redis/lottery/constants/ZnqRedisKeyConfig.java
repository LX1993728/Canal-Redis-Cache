package com.redis.lottery.constants;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Date;
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

    // 周年庆活动 每个用户信息的前缀 HASH结构
    private static final  String MASTER_INFO_PREFIX = ZNQ_PREFIX +SPLIT_FLAG + "MASTERINFO";

    // 每个奖品的统一前缀 HASH 结构
    private static final String PRIZE_INFO_PREFIX = ZNQ_PREFIX + SPLIT_FLAG + "PRIZEINFO";

    /**
     * 获取用户相关的信息的redis key
     * @param masterId
     * @return
     */
    public static String getMasterInfoKey(String masterId){
        return MASTER_INFO_PREFIX + SPLIT_FLAG + masterId;
    }

    /**
     * 获取奖品相关信息的 redis key
     * @param prizeId
     * @return
     */
    public static String getPrizeInfoKey(String prizeId){
        return PRIZE_INFO_PREFIX + SPLIT_FLAG + prizeId;
    }





}
