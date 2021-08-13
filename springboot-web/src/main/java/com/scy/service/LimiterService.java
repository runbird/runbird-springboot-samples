package com.scy.service;

/**
 * 类名： LimiterService <br>
 * 描述：TODO <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface LimiterService {

    String limit(String username);

    String getResourceLimitById(String beanId);
}
