package com.scy.limit;

import java.lang.annotation.*;

/**
 * @author JimLock
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    /**
     * 名字
     */
    String name() default "";
    /**
     * key
     */
    String key() default "";
    /**
     * key的前缀
     */
    String prefix() default "";
    /**
     * 给定的时间范围 单位(秒)
     */
    int period();
    /**
     * 一定时间内最多访问次数
     */
    int count();
    /**
     * 限流的类型(用户自定义key 或者 请求ip)
     */
    LimitType limitType() default LimitType.CUSTOMER;
}
