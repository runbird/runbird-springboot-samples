package com.scy.service.impl;

import com.scy.api.annoation.ResourceLimit;
import com.scy.service.LimiterService;
import org.springframework.stereotype.Service;

/**
 * 类名： LimiterServiceImpl <br>
 * 描述：限流demo <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Service
class LimiterServiceImpl implements LimiterService {

    private static String MAX_THREHOLD_KEY = "1";

    @Override
    @ResourceLimit(fallbackClass = LimiterServiceImpl.class, resourceId = "limit")
    public String limit(String username) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello :" + username;
    }

    @Override
    public String getResourceLimitById(String beanId) {
    
    }
}
