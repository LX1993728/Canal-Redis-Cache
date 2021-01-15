package com.redis.cache.service;

/**
 * 由接入方实现此抽象
 */
public interface PersonalCacheService {
    /**
     * 返回当前用户的 唯一标识/UserId
     * @return
     */
     String getPersonalUserId();
}
