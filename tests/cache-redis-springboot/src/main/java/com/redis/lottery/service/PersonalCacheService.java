package com.redis.lottery.service;

/**
 * 由接入方实现此抽象
 */
public interface PersonalCacheService {
    /**
     * 返回当前用户的 唯一标识/UserId
     * @return
     */
     String getPersonalUserId();

    /**
     * 如果想灵活配置当前使用数据库的名称 可以覆盖此方法
     * 优先级大小如下:  @XXCache(dbName) > overrideMethod(getDBName) > cachekey.properties(xxDBName)
     * 如果缓存注解中设置的有dbName 则以注解中的DBName设置为准，如果注解中没有设置，则看是否覆盖了此方法，如果也没有覆盖此方法则以
     * 配置文件的全局配置为准
     * @return
     */
    default String getDBName(){
         return "";
     }
}
