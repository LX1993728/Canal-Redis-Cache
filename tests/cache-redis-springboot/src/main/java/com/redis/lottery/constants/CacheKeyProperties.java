package com.redis.lottery.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cache.key")
@PropertySource("classpath:cacheKey.properties")
public class CacheKeyProperties {
    /**
     * 拼接key时指定 统一的前缀，用来区分 是否是缓存
     */
    private String prefix = "CACHE";
    /**
     * 拼接key时指定操作对应的数据库名， 默认为空
     */
    private String dbname = getEmpty();
    /**
     * 拼接key时指定每项标识之间的间隔符
     */
    private String delimiter = ":";
    /**
     * 拼接key时指定每项标识为空或不存在时的替代符
     */
    private String empty = "NULL";

}
