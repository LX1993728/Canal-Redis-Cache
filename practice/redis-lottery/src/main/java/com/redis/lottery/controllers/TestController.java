package com.redis.lottery.controllers;

import com.redis.lottery.constants.ZnqKeyConfig;
import com.redis.lottery.domains.ZnqPrize;
import com.redis.lottery.service.IZnqService;
import com.redis.lottery.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class TestController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private IZnqService znqService;

    @Autowired
    private JedisUtils jedisUtils;

    @GetMapping("/prizes")
    public List<ZnqPrize>  getPrizes(){
        final List<ZnqPrize> znqPrizes = entityManager.createQuery(
                "SELECT zp FROM ZnqPrize zp WHERE zp.starDiamonds <> -2 ORDER BY zp.probability ASC",
                ZnqPrize.class).getResultList();
        return znqPrizes;
    }
    @GetMapping("/monitorPrizes")
    public List<ZnqPrize>  getMonitorPrizes(){
        return znqService.getAndMonitorPrizes();
    }

    @GetMapping("/testIncre")
    public Object testIncre(){
        String key= "INCREMENT_TEST";
        String key2= "SETRANDOM_TEST";
        final Long incr = jedisUtils.incr(key);
        if (!jedisUtils.exists(key2)){
            String[] names = new String[]{"张三", "李四", "王五", "赵六", "送七", "AAA", "BBB", "CCC", "DDD"};
            jedisUtils.action(jedis -> jedis.sadd(key2, names));
        }
        final Long aLong = jedisUtils.del("XXXXXXXXXXXXXXXX");
        final Long yyyyyyyyyy = jedisUtils.action(jedis -> jedis.scard("YYYYYYYYYY"));
        final Boolean sisMember = jedisUtils.action(jedis -> jedis.sismember("YYYYYYYYYY", "aaa"));
        log.info("++++++{}+++++++", aLong);
        final List<String> randomVals = jedisUtils.action(jedis -> jedis.srandmember(key2, 2));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(key, incr);
        resultMap.put(key + "_Str", jedisUtils.get(key));
        resultMap.put(key2, randomVals);
        resultMap.put("notExistsScardCount", yyyyyyyyyy);
        resultMap.put("sisMember", sisMember);
        return resultMap;
    }
    // 测试Jedis hincrBy 是否可以使用负值递减 结论:可以
    @GetMapping("/testHdecry")
    public Object testHdecry(@RequestParam(name = "crement", defaultValue = "1")Long crement,
                             @RequestParam(name = "prizeId", defaultValue = "2")Long prizeId){
        final String prizeInfoKey = ZnqKeyConfig.getPrizeInfoKey(Long.toString(prizeId));
        final Long issued = jedisUtils.action(jedis -> jedis.hincrBy(prizeInfoKey, "issued", crement));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("prizeId", prizeId);
        resultMap.put("issued", issued);
        resultMap.put("crement", crement);
        return resultMap;
    }


}
