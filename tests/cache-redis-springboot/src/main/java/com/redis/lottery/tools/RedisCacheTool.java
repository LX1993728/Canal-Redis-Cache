package com.redis.lottery.tools;

import com.redis.lottery.constants.CacheKeyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class RedisCacheTool {

    // Cache:DB=DB1:TS=[T_BOOK,T_COURSE,T_USER]:UID=userId1111:M=com.redis.cache.TestControllertestMap3:81512a6d9e9da02764b9b5d27d14e2a2
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CacheKeyProperties cacheKeyProperties;

    /**
     * 根据完整DBName 获取与某个数据库对应的缓存keys
     * @param dbName
     * @return
     */
    public Set<String> getKeysByDBName(String dbName){
        String cp = cacheKeyProperties.getPrefix();
        StringBuffer buffer = new StringBuffer(cp.trim());
        buffer.append(":DB=")
                .append(dbName.trim().toUpperCase())
                .append(":*");
        return redisTemplate.keys(buffer.toString());
    }

    /**
     * 根据UID查询 指定用户的全部缓存keys
     * @param uid
     * @return
     */
    public Set<String> getKeysByUID(String uid){
        String cp = cacheKeyProperties.getPrefix();
        StringBuffer buffer = new StringBuffer(cp.trim());
        buffer.append(":DB=*:TS=*:UID=")
                .append(uid.trim()) // userId 不需要大写
                .append(":*");
        return redisTemplate.keys(buffer.toString());
    }

    /**
     * 完整匹配 查询与某个库对应且查询操作包含指定表的keys
     * @param dbName
     * @param table
     * @return
     */
    public Set<String> getKeysByTableInDB(String dbName, String table){
        String cp = cacheKeyProperties.getPrefix();
        StringBuffer buffer1 = new StringBuffer(cp.trim());
        // redis  keys pattern 特殊符号: [] 、* 、? 需要转义
        buffer1.append(":DB=")
                .append(dbName.trim().toUpperCase())
                .append(":TS=\\[*,")
                .append(table.trim().toUpperCase())
                .append(",*\\]:UID=*");
        StringBuffer buffer2 = new StringBuffer(cp.trim());
        buffer2.append(":DB=")
                .append(dbName.trim().toUpperCase())
                .append(":TS=\\[")
                .append(table.trim().toUpperCase())
                .append(",*\\]:UID=*");
        StringBuffer buffer3 = new StringBuffer(cp.trim());
        buffer3.append(":DB=")
                .append(dbName.trim().toUpperCase())
                .append(":TS=\\[*,")
                .append(table.trim().toUpperCase())
                .append("\\]:UID=*");
        final Set<String> keys1 = redisTemplate.keys(buffer1.toString());
        final Set<String> keys2 = redisTemplate.keys(buffer2.toString());
        final Set<String> keys3 = redisTemplate.keys(buffer3.toString());
        final Set<String> resultKeys = new HashSet<>();
        resultKeys.addAll(keys1);
        resultKeys.addAll(keys2);
        resultKeys.addAll(keys3);
        return resultKeys;
    }

    /**
     * 根据完全方法名查找keys
     * @param methodName
     * @return
     */
    public Set<String> getKeysByMethodName(String methodName){
        String cp = cacheKeyProperties.getPrefix();
        StringBuffer buffer = new StringBuffer(cp.trim());
        buffer.append(":DB=*:TS=[*]:M=")
                .append(methodName)
                .append(":*");
        return redisTemplate.keys(buffer.toString());
    }


}
