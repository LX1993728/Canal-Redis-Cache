package com.redis.lottery.constants;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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



}
