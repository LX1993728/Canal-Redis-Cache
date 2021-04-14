package com.redis.lottery.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.redis.lottery.annotations.ControlCache;
import com.redis.lottery.annotations.ServiceCache;
import com.redis.lottery.constants.CacheKeyProperties;
import com.redis.lottery.service.PersonalCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * key的规则如下：
 *
 * ① service类型缓存: Cache:DB=${DbName}:TS=[tab1,tab2 ...]:UID=用户标识/NULL:M=类名.方法名:MD5(参数数组的Json字符串)
 * ① Controller类型缓存: Cache:DB=${DbName}:TS=[tab1,tab2 ...]:UID=用户标识/NULL:M=类名.方法名:MD5(request.getParameterMap对应的Json字符串)
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
        // 连接标识的间隔符
        String delimit = cacheKeyProperties.getDelimiter();
        if (method.isAnnotationPresent(ControlCache.class)){
            // 获取请求参数标识
            HttpServletRequest request = getRequest();
            Map<String, String[]> parameterMap = request == null ? null : request.getParameterMap();
            if (parameterMap != null){
                // 对于控制器而言，参数标识只从请求参数上去获取并处理
                paramStr = SecureUtil.md5(JSONUtil.toJsonStr(parameterMap));
            }
            ControlCache controlCache = method.getAnnotation(ControlCache.class);
            // 获取dbName标识
            dbNameStr = getDBNameStr(controlCache.dbname());
            // 获取table标识
            tablesStr = getTablesStr(controlCache.tables());
            // 获取用户标识
            if (controlCache.isPersonal()){
                personalStr = personalCacheService.getPersonalUserId();
            }
        }else if (method.isAnnotationPresent(ServiceCache.class)){
            // 获取请参数标识
            paramStr = JSONUtil.toJsonStr(params);
            ServiceCache serviceCache = method.getAnnotation(ServiceCache.class);
            // 获取dbName标识
            dbNameStr = getDBNameStr(serviceCache.dbname());
            // 获取table标识
            tablesStr = getTablesStr(serviceCache.tables());
            // 获取用户标识
            if (serviceCache.isPersonal()){
                personalStr = personalCacheService.getPersonalUserId();
            }
        }else {
            // 如果既不是控制器缓存类型 也不是service缓存类型，其它的暂不考虑
            throw new RuntimeException("请使用封装的缓存注解[Spring原生注解已禁用]......");
        }


        StringBuilder sb = new StringBuilder();
        sb.append(cacheKeyProperties.getPrefix()) // 缓存前缀
                .append(delimit)
                .append("DB=").append(dbNameStr) // dbName
                .append(delimit)
                .append("TS=").append(tablesStr) // 表标识
                .append(delimit)
                .append("UID=").append(personalStr) // 用户标识
                .append(delimit)
                .append("M=").append(target.getClass().getName())
                .append(method.getName())
                .append(delimit)
                .append(paramStr);

        return sb.toString();
    }

    private String getDBNameStr(String annotationDBName){
        if (!StringUtils.isEmpty(annotationDBName)){
            return annotationDBName.toUpperCase();
        }

        if (!StringUtils.isEmpty(personalCacheService.getDBName())){
            return personalCacheService.getDBName().toUpperCase();
        }

        return cacheKeyProperties.getDbname().toUpperCase();
    }

    private  String getTablesStr(String[] tables){
        if (tables == null || tables.length == 0){
            return cacheKeyProperties.getEmpty();
        }
        for (int i = 0; i < tables.length ; i++){
            tables[i] = tables[i].trim().toUpperCase();
        }
        Arrays.sort(tables);

        return Arrays.toString(tables).replaceAll("\\s*","");
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
