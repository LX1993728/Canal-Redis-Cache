package com.redis.cache.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cache")
@PropertySource("classpath:/cache.properties")
public class CacheProperties {
    private String dbname;
    private String delimiter;
    private String prefix;
}
