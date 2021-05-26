package com.jedis.demo.tests;

import com.jedis.demo.domains.User;
import com.jedis.demo.listener.RedisKeyExpiredListener;
import com.jedis.demo.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisTest1 {

    @Test
    public void test1() throws InterruptedException {
       test();
    }

    @Test
    public void test2() throws InterruptedException {
       test();
    }

    @Test
    public void test3(){
        final User user = new User(12, "张三");
        // JedisUtils.hmsetResetObj(user, "user_1");
        JedisUtils.hincrByObjField(User.class,"user_1", "age",10000, 100);
    }


    private void test() throws InterruptedException {
        RedisKeyExpiredListener listener=new RedisKeyExpiredListener();
        new Thread(()->{
            JedisUtils.voidAction(jedis -> jedis.subscribe(listener,"__keyevent@0__:expired", "channel1"));
        }).start();
        Thread.sleep(3000);
        JedisUtils.set("a", "aaaaaaaa");
        JedisUtils.expire("a", 1);
        final Long aLong = JedisUtils.action(jedis -> jedis.publish("channel1", "发布消息测试"));
        log.info("测试发布数量={}", aLong);
        TimeUnit.HOURS.sleep(100);
    }
}
