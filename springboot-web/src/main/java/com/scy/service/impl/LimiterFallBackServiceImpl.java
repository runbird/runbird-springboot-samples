package com.scy.service.impl;

import com.scy.service.LimiterFallBackService;

/**
 * 类名： LimiterFallBackServiceImpl <br>
 * 描述：限流降级服务 <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class LimiterFallBackServiceImpl implements LimiterFallBackService {
    @Override
    public String fallback(String username) {
        return "fallback: " + username;
    }
}
