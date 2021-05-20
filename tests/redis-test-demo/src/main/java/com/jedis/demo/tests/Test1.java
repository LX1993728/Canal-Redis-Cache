package com.jedis.demo.tests;

import com.jedis.demo.listener.RedisKeyExpiredListener;
import com.jedis.demo.utils.JedisUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


public class Test1 {

    @Test
    public void test1() throws InterruptedException {
        RedisKeyExpiredListener listener=new RedisKeyExpiredListener();
        new Thread(()->{
            JedisUtils.voidAction(jedis -> jedis.subscribe(listener,"__keyevent@__:expired"));
        });
        Thread.sleep(3000);
        JedisUtils.set("a", "aaaaaaaa");
        JedisUtils.expire("a", 2);
        TimeUnit.HOURS.sleep(100);

    }
}
