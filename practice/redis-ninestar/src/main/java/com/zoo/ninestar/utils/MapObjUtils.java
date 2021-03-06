package com.zoo.ninestar.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class MapObjUtils{
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
     * 由Map<String, String> 转 Object
     * @param clazz
     * @param strMap
     * @param <T>
     * @return
     */
    public static <T> T strMap2Obj(Class<T> clazz, Map<String, String> strMap){
        assert clazz != null && strMap !=null;
        try {
            final T instance = clazz.newInstance();
            for (Map.Entry<String, String> entry : strMap.entrySet()){
                final String key = entry.getKey();
                final String value = entry.getValue();
                try {
                    final Field field = clazz.getDeclaredField(key);
                    field.setAccessible(true); // 使私有字段也可以访问
                    final Class<?> fieldType = field.getType();
                    field.set(instance, transformValue(fieldType, value));
                }catch (Exception e){
                    if (e instanceof NoSuchFieldException){
                        log.warn("class {} not contains field {}", clazz.getSimpleName(), key);
                    }else {
                        log.error(e.getMessage(), e);
                    }
                }
            }
            return instance;
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将value转换为对应的class类型 fieldType
     *
     * @param fieldType class类型
     * @param value     需要转换的值
     * @return Object
     * @throws Exception 反射获取类 Class.forName 可能会导致异常
     */
    private static Object transformValue(Class fieldType, String value) throws Exception {
        if (StringUtils.isEmpty(value)) return null;
        if (fieldType == String.class) {
            return String.valueOf(value);
        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
            return Boolean.valueOf(value);
        } else if (fieldType == Byte.class || fieldType == byte.class) {
            return Byte.valueOf(value);
        } else if (fieldType == Double.class || fieldType == double.class) {
            return Double.valueOf(value);
        } else if (fieldType == Float.class || fieldType == float.class) {
            return Float.valueOf(value);
        } else if (fieldType == Integer.class || fieldType == int.class) {
            return Integer.valueOf(value);
        } else if (fieldType == Long.class || fieldType == long.class) {
            return Long.valueOf(value);
        } else if (fieldType == Short.class || fieldType == short.class) {
            return Short.valueOf(value);
        } else if (fieldType == Character.class || fieldType == char.class) {
            return value.charAt(0);
        } else if (fieldType == BigDecimal.class) {
            return new BigDecimal(value);
        } else if (fieldType == BigInteger.class) {
            return new BigInteger(value);
        } else if (fieldType == Date.class) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(value);
        } else if (fieldType == List.class) {
            return Arrays.asList(value.split(","));
        } else if (fieldType == Set.class) {
            return new HashSet<>(Arrays.asList(value.split(",")));
        } else if (fieldType.isEnum()) { // 枚举类型
            Class<?> cl = Class.forName(fieldType.getName());
            Field field = cl.getDeclaredField(value);
            return field.get(cl);
        } else if (fieldType == Pattern.class) {
            return Pattern.compile(value);
        } else {
            return value;
        }
    }

    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取要更新实体字段的HQL
     * @param id
     * @param clazz
     * @param paramMap
     * @param <T>
     * @return
     * @throws Exception
     */
    public static  <T> String getUpdateEntityHql(Long id, Class<T> clazz, Map<String, Object> paramMap) throws Exception{
        if (paramMap == null || paramMap.isEmpty()){
            return null;
        }
        String entityName = clazz.getSimpleName();
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("update %s set ", entityName));
        boolean hasSet = false;
        for (Map.Entry<String,Object> entry : paramMap.entrySet()){
            String key = entry.getKey();
            Object objVal = entry.getValue();
            String value = null;
            if (objVal instanceof String){
                value = String.format("'%s'", objVal);
            }else if (objVal instanceof Integer){
                value = Integer.toString((Integer) objVal);
            }else if (objVal instanceof Double){
                value = Double.toString((Double) objVal);
            }else if (objVal instanceof Date){
                value = String.format("'%s'",  new SimpleDateFormat(dateFormat).format((Date) objVal));
            }else {
                log.error("暂不支持的field类型={}", objVal.getClass().getSimpleName());
            }
            if (StringUtils.isNotBlank(value)){
                builder.append(String.format(" %s %s=%s ", hasSet?",":"", key, value));
            }
            if (!hasSet){
                hasSet = true;
            }
        }
        builder.append(String.format(" where id=%s", id));
        String hqlStr = builder.toString();
        log.info("更新表 hql={}", hqlStr);
        return hqlStr;
    }

    /**
     * 转换model
     * */
    public static <T>  T ConvertModel(Class<T> clazz, Object obj)
    {
        Field[] fields=obj.getClass().getDeclaredFields();
        for (Field field:fields)
        {
            field.setAccessible(true);
            Object val= null;
            try {
                val = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if(val==null)
            {
                try {
                    if(field.getType().getName().equals("java.lang.String")) {
                        field.set(obj, "");
                    }
                    if(field.getType().getName().equals("java.lang.Integer")) {
                        field.set(obj, -1);
                    }
                    if(field.getType().getName().equals("java.lang.Long")) {
                        field.set(obj, -1L);
                    }
                    if(field.getType().getName().equals("java.lang.Double")) {
                        field.set(obj, -1D);
                    }
                    if(field.getType().getName().equals("java.lang.Float")) {
                        field.set(obj, -1F);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return (T) obj;
    }

}
