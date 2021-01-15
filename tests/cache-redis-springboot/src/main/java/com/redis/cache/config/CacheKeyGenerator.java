package com.redis.cache.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.redis.cache.annotations.ControlCache;
import com.redis.cache.annotations.ServiceCache;
import com.redis.cache.constants.CacheKeyProperties;
import com.redis.cache.service.PersonalCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * key的规则如下：
 * ① service类型缓存: Cache:${DbName}:[tab1,tab2 ...]:用户标识/NULL:类名.方法名:MD5(参数数组的Json字符串)
 * ① Controller类型缓存: Cache:${DbName}:[tab1,tab2 ...]:用户标识/NULL:类名.方法名:MD5(request.getParameterMap对应的Json字符串)
 */

@Slf4j
public class CacheKeyGenerator implements KeyGenerator {

    private CacheKeyProperties cacheKeyProperties;
    private PersonalCacheService personalCacheService;

    public CacheKeyGenerator() {
    }

    public CacheKeyGenerator(CacheKeyProperties cacheKeyProperties, PersonalCacheService personalCacheService) {
        this.cacheKeyProperties = cacheKeyProperties;
        this.personalCacheService = personalCacheService;
    }

    @Override
    public Object generate(Object target, Method method, Object... params) {

        // 需要拼接的请求参数标识
        String paramStr = cacheKeyProperties.getEmpty();
        // 需要拼接的dbName标识
        String dbNameStr = cacheKeyProperties.getDbname();
        // 需要拼接表名的标识
        String tablesStr = cacheKeyProperties.getEmpty();
        // 需要拼接的用户标识
        String personalStr = cacheKeyProperties.getEmpty();

        if (method.isAnnotationPresent(ControlCache.class)){
            HttpServletRequest request = getRequest();
            Map<String, String[]> parameterMap = request == null ? null : request.getParameterMap();
            if (parameterMap != null){
                // 对于控制器而言，参数标识只从请求参数上去获取并处理
                paramStr = SecureUtil.md5(JSONUtil.toJsonStr(parameterMap));
            }
        }else if (method.isAnnotationPresent(ServiceCache.class)){
            paramStr = JSONUtil.toJsonStr(params);
        }else {
            // 如果既不是控制器缓存类型 也不是service缓存类型，其它的暂不考虑
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cacheKeyProperties.getPrefix());
        sb.append(target.getClass().getName());
        sb.append(method.getName());
        sb.append(":");
        sb.append(paramStr);

        return sb.toString();
    }

    private  String getTablesStr(String[] tables){
        if (tables == null && tables.length == 0){
            return cacheKeyProperties.getEmpty();
        }
        for (int i = 0; i < tables.length ; i++){
            tables[i] = tables[i].trim().toUpperCase();
        }
        Arrays.sort(tables);

        return Arrays.toString(tables);
    }

    private HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null){
            log.error("Not exists request ......");
            return null;
        }
        ServletRequestAttributes servletReqAttrs = (ServletRequestAttributes) requestAttributes;
        return servletReqAttrs.getRequest();
    }

    public static void main(String[] args){
        String[]  t = new String[]{"t_A","t_ccc"};
        log.info(new CacheKeyGenerator().getTablesStr(t));
    }
}
