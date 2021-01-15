package com.redis.cache;

import com.redis.cache.annotations.ControlCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test1")
    @Cacheable(cacheNames = {"aaa"})
    public Map<String, Object> testMap(@RequestParam("id")Long id){
        String name = "zhansgan" + id;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        resultMap.put("name", name);
        log.info("---- id={} ----", id);
        return  resultMap;
    }

    @GetMapping("/test2")
    @ControlCache
    public Map<String, Object> testMap2(@RequestParam("id")Long id){
        String name = "lisi" + id;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        resultMap.put("name", name);
        log.info("---- id={} ----", id);
        return  resultMap;
    }

    @GetMapping("/test3")
    public Object testRedis(){
//        redisTemplate.keys()
        return null;
    }
}
