package com.jedis.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JedisUtils {
    private static JedisPool jedisPool;

    static {
        jedisPool();
    }

    private static JedisPool jedisPool() {
        if (jedisPool == null || jedisPool.isClosed()) {
            synchronized (JedisUtils.class) {
                if (jedisPool == null || jedisPool.isClosed()) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxIdle(8);
                    config.setMaxWaitMillis(8);
                    config.setMaxTotal(-1);
                    config.setMinIdle(0);
                    jedisPool = new JedisPool(config, "127.0.0.1", 6379, 0, null);
                }
            }
        }
        return jedisPool;
    }

    /**
     * 获取JedisPool连接池实例
     *
     * @return JedisPool连接池实例（通过Spring生成）
     */
    public static JedisPool getJedisPool() {
        return jedisPool();
    }

    /**
     * 获取Jedis实例
     *
     * @return Jedis实例
     */
    public static Jedis getJedis() {
        return jedisPool().getResource();
    }

    /**
     * Redis设置键值对
     *
     * @param key   键
     * @param value 值
     * @return 值
     */
    public static String set(String key, String value) {
        return action(jedis -> jedis.set(key, value));
    }

    /**
     * Redis获取键对应的值
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return action(jedis -> jedis.get(key));
    }

    /**
     * Redis是否存在当前键
     *
     * @param key 查询的键
     * @return 是否存在
     */
    public static Boolean exists(String key) {
        return action(jedis -> jedis.exists(key));
    }

    /**
     * 设置Key的过期时间，单位以秒计
     *
     * @param key     键
     * @param seconds 秒数
     * @return 1为设置成功，0为设置失败（Jedis返回的就是Long，不知道为嘛要用Long）
     */
    public static Long expire(String key, int seconds) {
        return action(jedis -> jedis.expire(key, seconds));
    }

    /**
     * 返回key的过期时间
     *
     * @param key 键
     * @return 返回过期时长（以秒为单位） 若不存在该key或该key不存在过期时间则返回-1
     */
    public static Long ttl(String key) {
        return action(jedis -> jedis.ttl(key));
    }

    /**
     * 将key对应的值+1
     * （由于在Redis中都是字符串，所以Redis会将字符串转换为最大64bit的有符号整数类型后再增加）
     * （如果key不存在，则会将key对应的值设置为0后再执行增加操作）
     * （如果value的类型错误，则会异常报错！）
     *
     * @param key 键
     * @return 返回增加的结果，
     */
    public static Long incr(String key) {
        return action(jedis -> jedis.incr(key));
    }

    /**
     * Redis设置Hash
     *
     * @param key   键
     * @param value 值（一个Map）
     * @return 存在则更新并返回0，不存在则新建并返回1
     */
    @Deprecated // 直接设置Map 需要使用hmset
    public static Long hSet(String key, Map<String, String> value) {
        return action(jedis -> jedis.hset(key, value));
    }

    /**
     * Redis设置Hash
     *
     * @param key   键
     * @param value 值（一个Map）
     * @return 存在则更新并返回0，不存在则新建并返回1
     */
    public static String hmSet(String key, Map<String, String> value) {
        return action(jedis -> jedis.hmset(key, value));
    }

    /**
     * Redis获取Hash
     *
     * @param key 键
     * @return hashMap
     */
    public static Map<String, String> hGet(String key) {
        return action(jedis -> jedis.hgetAll(key));
    }

    /**
     * Redis设置hashMap
     *
     * @param key   map对应的键
     * @param field map中键
     * @param value map中键对应的值
     * @return 如果存在此map且存在此field，则更新数据并返回0，否则创建数据并返回1
     */
    public static Long hSet(String key, String field, String value) {
        return action(jedis -> jedis.hset(key, field, value));
    }

    /**
     * Redis获取Hash
     *
     * @param key   键
     * @param field map中的键
     * @return map中键对应的值
     */
    public static String hGet(String key, String field) {
        return action(jedis -> jedis.hget(key, field));
    }

    /**
     * Redis删除Hash
     *
     * @param key   键
     * @param field map中可变数量的键
     * @return 如果field在map中存在则删除并返回1，否则不做任何操作返回0
     */
    public static Long hDel(String key, String... field) {
        return action(jedis -> jedis.hdel(key, field));
    }

    /**
     * Redis判断是否存在
     *
     * @param key   键
     * @param field map中的键
     * @return 判断key对应的map中是否存在field的键
     */
    public static Boolean hExists(String key, String field) {
        return action(jedis -> jedis.hexists(key, field));
    }

    /**
     * Redis获取hash对应的val
     *
     * @param key 键
     * @return val的列表
     */
    public static List<String> hVals(String key) {
        return action(jedis -> jedis.hvals(key));
    }

    /**
     * Redis删除key对应的数据
     *
     * @param key 键
     * @return 存在就删除且返回1，不存在不做任何操作返回0
     */
    public static Long del(String... key) {
        return action(jedis -> jedis.del(key));
    }

    /**
     * 封装一部分重复操作，使jedis操作更简便
     */
    public static <T> T action(RedisAction<T> action) {
        Jedis jedis = jedisPool.getResource();
        config(jedis);
        T v = action.action(jedis);
        jedis.close();
        return v;
    }

    public static void voidAction(RedisVAction action) {
        Jedis jedis = jedisPool.getResource();
        config(jedis);
        action.action(jedis);
        jedis.close();
    }

    public interface RedisAction<T> {
        T action(Jedis jedis);
    }

    public interface RedisVAction {
        void action(Jedis jedis);
    }

    private static void config(Jedis jedis) {
        String parameter = "notify-keyspace-events";
        List<String> notify = jedis.configGet(parameter);
        if (notify.get(1).equals("")) {
            jedis.configSet(parameter, "Ex"); //过期事件
        }
    }

    //-----------------  Map <--> bean <--> Hash 之间的转换 --------------------------

    /**
     * 对象转Map
     * @param obj
     * @return
     */
    public static Map<String, Object> obj2OBJMap(Object obj) {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return JSON.parseObject(JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat));
    }

    public static Map<String, String> obj2STRMap(Object obj) {
        assert obj != null;
        Map<String, Object> soMap = obj2OBJMap(obj);
        Map<String, String> resultMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : soMap.entrySet()){
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            if ((fieldValue instanceof String ||
                    fieldValue instanceof Number ||
                    fieldValue instanceof Boolean)){
                resultMap.put(fieldName, fieldValue.toString());
            }
        }
        return resultMap;
    }

    /**
     * 将对象转为Hash 存储到redis中
     * @param obj
     * @param key
     * @return
     */
    public static Map<String, String> hmsetResetObj(Object obj, String key){
        assert StringUtils.isNotBlank(key);
        final Map<String, String> strMap = obj2STRMap(obj);
        if (!strMap.isEmpty()){
            JedisUtils.action(jedis -> jedis.hmset(key, strMap));
        }
        return strMap;
    }

    public static Map<String, String> hmsetUpdateObj(Object obj, String key){
        assert StringUtils.isNotBlank(key);
        final Map<String, String> strMap = obj2STRMap(obj);
        if (!strMap.isEmpty()){
            for (Map.Entry<String, String> entry : strMap.entrySet()){
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue();
                JedisUtils.action(jedis -> jedis.hset(key, fieldName, fieldValue));
            }
        }
        return strMap;
    }
    public static Long hincrByObjField(Class clazz, String key, String fieldName, long incrValue, long maxValue ){
        assert clazz != null;
        assert  StringUtils.isNotBlank(key) && StringUtils.isNotBlank(fieldName);
        boolean  isFieldNameValied = false;
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            isFieldNameValied = true;
        } catch (NoSuchFieldException ignored) {
        }
        assert isFieldNameValied;

        Long operateValue = JedisUtils.action(jedis -> jedis.hincrBy(key, fieldName, incrValue));
        if (operateValue > maxValue){
            operateValue = JedisUtils.action(jedis -> jedis.hset(key, fieldName, String.valueOf(maxValue)));
        }

        return operateValue;
    }

    public static Long hdecrByObjField(Class clazz, String key, String fieldName, long decrValue, long minValue ){
        assert clazz != null;
        assert  StringUtils.isNotBlank(key) && StringUtils.isNotBlank(fieldName);
        boolean  isFieldNameValied = false;
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            isFieldNameValied = true;
        } catch (NoSuchFieldException ignored) {
        }
        assert isFieldNameValied;

        decrValue = -Math.abs(decrValue);
        long finalDecrValue = decrValue;
        Long operateValue = JedisUtils.action(jedis -> jedis.hincrBy(key, fieldName, finalDecrValue));
        if (operateValue < minValue){
            operateValue = JedisUtils.action(jedis -> jedis.hset(key, fieldName, String.valueOf(finalDecrValue)));
        }
        return operateValue;
    }

}
