package com.redis.cache.config;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.redis.cache.constants.CacheProperties;
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
 * ① service类型缓存: Cache:${DbName}:[tab1,tab2 ...]:类名.方法名:参数数组的Json字符串
 * ① Controller类型缓存: Cache:${DbName}:[tab1,tab2 ...]:类名.方法名: request.getParameterMap对应的Json字符串
 */


@Slf4j
public class CacheKeyGenerator implements KeyGenerator {

    private CacheProperties cacheProperties;

    public CacheKeyGenerator(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Override
    public Object generate(Object target, Method method, Object... params) {
        HttpServletRequest request = getRequest();
        if (request == null){
            log.info("Not found request ......");
        }else {
            Map<String, String[]> parameterMap = request.getParameterMap();
            log.info("=======request  parameter : {} ==========\n", parameterMap.toString());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(method.getName());
        sb.append(":");
        sb.append(Arrays.toString(DigestUtil.md5(JSONUtil.toJsonStr(params))));
        return sb.toString();
    }

    /**
     * @param target
     * @param method
     * @return
     */
    private String getKeyPrefix(Object target, Method method){
        return null;
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
}
