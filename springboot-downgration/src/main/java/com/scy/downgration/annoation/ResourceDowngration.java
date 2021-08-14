package com.scy.downgration.annoation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceDowngration {

    /**
     * 最大阈值
     **/
    int maxThreshold() default 1;

    /**
     * 限流回调
     **/
    Class<?> fallbackClass();

    /**
     * 回调方法
     **/
    String fallbackMethod() default "";

    /**
     * 方法资源Id,id必须唯一
     **/
    String resourceId();
}
