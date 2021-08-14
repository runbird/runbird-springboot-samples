package com.scy.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @desc: Spring
 * @program: springboot-configuration
 * @author: Suocaiyuan
 * @date: 2021-08-14 14:15
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static void setContext(ApplicationContext applicationContext) {
        if (applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public static boolean containBean(String name) {
        return applicationContext.containsBean(name);
    }


    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
