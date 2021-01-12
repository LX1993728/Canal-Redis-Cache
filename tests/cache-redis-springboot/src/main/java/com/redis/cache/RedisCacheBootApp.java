package com.redis.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisCacheBootApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheBootApp.class, args);
    }

}
