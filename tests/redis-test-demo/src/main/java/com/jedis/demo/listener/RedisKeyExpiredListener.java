package com.jedis.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.JedisPubSub;

/**
 * @see  RedisKeyExpiredListener  监听订阅类
 * @author liuxun
 */
@Slf4j
public class RedisKeyExpiredListener extends JedisPubSub {
    /**
     *
     * @Title: onMessage
     * @Description: 取得订阅的消息后的处理
     * @param channel
     *            频道
     * @param message
     *            消息内容
     *
     * @author
     * @date
     */
    @Override
    public void onMessage(String channel, String message) {
        log.info("channel{" + channel + "}message{" + message + "}");
    }

    /**
     *
     * @Title: onPMessage
     * @Description: 取得按表达式的方式订阅的消息后的处理
     *
     * @author
     * @date
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        log.info("Redis订阅监听超时通知开始pattern{" + pattern + "}channel{" + channel + "}message{" + message + "}");
        long starTime = System.currentTimeMillis();
        if (StringUtils.isBlank(message)) {
            log.info("Redis订阅监听超时通知，message为空");
            return;
        }
        long endTime = System.currentTimeMillis();
        log.info("Redis订阅监听超时通知完成pattern{" + pattern + "}channel{" + channel + "}message{" + message + "}共耗时{"
                + (endTime - starTime) / 1000 + "}秒");
    }
}
