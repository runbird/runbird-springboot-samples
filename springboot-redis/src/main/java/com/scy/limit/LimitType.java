package com.scy.limit;

/**
 * 限流枚举
 */
public enum LimitType {
    /**
     * 自定义key
     */
    CUSTOMER,
    /**
     * 请求方IP
     */
    IP;
}
