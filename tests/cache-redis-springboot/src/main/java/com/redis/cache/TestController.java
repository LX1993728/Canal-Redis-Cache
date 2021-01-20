package com.redis.cache;

import com.redis.cache.annotations.ControlCache;
import com.redis.cache.tools.RedisCacheTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class TestController {
    @Autowired
    private RedisCacheTool redisCacheTool;

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
    @ControlCache(tables = {"t_user","t_book","t_course"}, isPersonal = true)
    public Map<String, Object> testMap3(@RequestParam("id")Long id){
        String name = "wangwu" + id;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        resultMap.put("name", name);
        log.info("---- id={} ----", id);
        return  resultMap;
    }

    @GetMapping("/test4")
    @ControlCache(tables = {"t_aaa","t_user","t_zzz"}, isPersonal = true)
    public Map<String, Object> testMap4(@RequestParam("id")Long id){
        String name = "zhaoliu" + id;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        resultMap.put("name", name);
        log.info("---- id={} ----", id);
        return  resultMap;
    }

    @GetMapping("/test5")
    public Object testRedis(){
        Set<String> keysByDBName = redisCacheTool.getKeysByDBName("db1");
        Set<String> keysByTableInDB = redisCacheTool.getKeysByTableInDB("db1", "t_user");
        final Set<String> keysByUID = redisCacheTool.getKeysByUID("userId1");
        Map<String, Set<String>> result = new HashMap<>();
        result.put("keysByDBName", keysByDBName);
        result.put("keysByTableInDB", keysByTableInDB);
        result.put("keysByUID", keysByUID);
        return result;
    }
}
