package com.zoo.ninestar.utils;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PropertyResourceBundle;
import java.util.Set;

/**
 * @author weizhangbj8024
 * Jun 6, 2012
 */
public class Loader {

    private static Logger logger = LoggerFactory.getLogger(Loader.class);

    private ApplicationContext ctx = null;
    private PropertyResourceBundle bundle;

    private PropertyResourceBundle tradeBundle;

    private PropertyResourceBundle messageBundle;
    private PropertyResourceBundle activityBoundle;
    private Set<PropertyResourceBundle> properties = new HashSet<PropertyResourceBundle>();

    private static Loader loader = new Loader();

    private Loader() {

        //加载spring配置文件
        ctx = ContextLoader.getCurrentWebApplicationContext();

        logger.info("加载spring配置文件");

        try {
            InputStream in = Loader.class.getResourceAsStream("/ApplicationResources.properties");
            InputStreamReader r;
            r = new InputStreamReader(in, "UTF-8");
            bundle = new PropertyResourceBundle(r);
            r.close();

        } catch (Exception e) {
            logger.error("Exception:" + e.getMessage(), e);
        }

        try {
            InputStream in = Loader.class.getResourceAsStream("/MessageResources.properties");
            InputStreamReader r;
            r = new InputStreamReader(in, "UTF-8");
            messageBundle = new PropertyResourceBundle(r);
            r.close();

        } catch (Exception e) {
            logger.error("Exception:" + e.getMessage(), e);
        }

        try {
            InputStream in = Loader.class.getResourceAsStream("/trade-show.properties");
            InputStreamReader r;
            r = new InputStreamReader(in, "UTF-8");
            tradeBundle = new PropertyResourceBundle(r);
            r.close();

        } catch (Exception e) {
            logger.error("Exception:" + e.getMessage(), e);
        }
        try {
            InputStream in = Loader.class.getResourceAsStream("/activity.properties");
            InputStreamReader r;
            r = new InputStreamReader(in, "UTF-8");
            activityBoundle = new PropertyResourceBundle(r);
            r.close();

        } catch (Exception e) {
            logger.error("Exception:" + e.getMessage(), e);
        }
        properties.add(bundle);
        properties.add(messageBundle);
        properties.add(tradeBundle);
        properties.add(activityBoundle);
    }

    public final static Loader getInstance() {
        return loader;
    }

    public Object getObject(String id) {

        return ctx.getBean(id);
    }

    public String getProps(String key) {
        return bundle.getString(key);
    }

    public String getMessage(String key) {
        return messageBundle.getString(key);
    }

    public String getTradeProps(String key) {
        return tradeBundle.getString(key);
    }

    public String getAllProps(String key) {
        String value = null;
        for (PropertyResourceBundle property : properties) {
            try {
                value = property.getString(key);
            } catch (Exception e) {
            }
            if (StringUtils.isNotBlank(value)) {
                break;
            }
        }
        return value;
    }

}
