package com.redis.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CacheConfig(cacheNames = "Test",keyGenerator = "redisKeyGenerator")
@RestController
public class TestController {
    @GetMapping("/test1")
    @Cacheable(value = "Test", key = "#id", keyGenerator = "redisKeyGenerator")
    public Map<String, Object> testMap(@RequestParam("id")Long id){
        String name = "zhansgan" + id;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        resultMap.put("name", name);
        log.info("---- id={} ----", id);
        return  resultMap;
    }
}
