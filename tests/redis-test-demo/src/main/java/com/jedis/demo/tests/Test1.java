package com.jedis.demo.tests;

import com.jedis.demo.listener.RedisKeyExpiredListener;
import com.jedis.demo.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test1 {

    @Test
    public void test1() throws InterruptedException {
       test();
    }

    @Test
    public void test2() throws InterruptedException {
       test();
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
