package com.scy.api.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名： ResourceLimit <br>
 * 描述：限流注解，同一时间限制并发数 <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceLimit {
    /** 阈值 **/
    int maxThreshold() default 1;

    /** 限流回调实现类**/
    Class<?> fallbackClass();

    /** 限流回调方法名称 **/
    String fallbackMethod() default "";

    /** 方法资源ID，一个方法代表一个资源ID**/
    String resourceId();
}
