package com.redis.cache.config;

import com.redis.cache.constants.CacheKeyProperties;
import com.redis.cache.service.PersonalCacheService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.Duration;

/**
 * 实现Redis+Spring Cache的配置
 */
@Component
@EnableConfigurationProperties(value = {CacheKeyProperties.class})
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private CacheKeyProperties cacheKeyProperties;

    @Autowired
    WebApplicationContext webApplicationContext;

    // ========================== Redis Cache相关的配置 =============================

    /**
     * 配置缓存管理器
     * @param factory Redis 线程安全连接工厂
     * @return 缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 生成两套默认配置，通过 Config 对象即可对缓存进行自定义配置
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                // 设置过期时间 10 分钟
                .entryTtl(Duration.ofMinutes(10))
                // 设置缓存前缀
                // .prefixKeysWith("cache:")
                .disableKeyPrefix()
                // 禁止缓存 null 值
                .disableCachingNullValues()
                // 设置 key 序列化
                .serializeKeysWith(keyPair())
                // 设置 value 序列化
                .serializeValuesWith(valuePair());
        // 返回 Redis 缓存管理器
        return RedisCacheManager.builder(factory)
                .cacheDefaults(cacheConfig).build();
                  //.withCacheConfiguration("user", cacheConfig).build();
    }

    /**
     * 配置键序列化
     * @return StringRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<String> keyPair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
    }

    /**
     * 配置值序列化，使用 GenericJackson2JsonRedisSerializer 替换默认序列化
     * @return GenericJackson2JsonRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<Object> valuePair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
    }

    /**
     * 重写生产key的策略
     * @apiNote 注意: 只适用于@Cacheable注解
     * @return
     */
    @Bean
    @Override
    @Primary
    public KeyGenerator keyGenerator() {
        PersonalCacheService pService = null;
        try {
           pService =  webApplicationContext.getBean(PersonalCacheService.class);
        }catch (Exception e){
            throw new NoSuchBeanDefinitionException("请实现PersonalCacheService接口...");
        }
        return new CacheKeyGenerator(cacheKeyProperties, pService);
    }

    // ========================== Redis Template相关的配置 =============================
    /**
     * retemplate相关配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        // 值采用json序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }

}
