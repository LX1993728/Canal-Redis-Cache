package com.redis.lottery.annotations;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Cacheable(cacheNames={"Service"})
public @interface ServiceCache {
    /**
     * 对应数据库的名称，若不设置则以全局配置{cache.properties中内容为准}
     * @return
     */
    String dbname() default "";

    @AliasFor(value = "tables")
    String[] value() default {};

    /**
     * 会影响到查询内容的表名，一般指SQL查询,会涉及的表
     * @return
     */
    @AliasFor(value = "value")
    String[] tables() default {};

    /**
     * 表示查询内容是否依赖于当前登录用户信息(userId等)， 如果是则为true，否则为false。
     * false 意味着是公共的多用户缓存， true表示每个登录用户自己的缓存。
     * @return
     */
    boolean isPersonal() default false;
}
