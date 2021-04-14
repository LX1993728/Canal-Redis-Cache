package com.redis.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisLotteryBootApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisLotteryBootApp.class, args);
    }

}
