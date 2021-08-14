package com.scy.api.service.impl;

import com.scy.api.service.LimiterService;
import com.scy.downgration.annoation.ResourceDowngration;
import org.springframework.stereotype.Service;

@Service
public class LimiterServiceImpl implements LimiterService {


    @Override
    @ResourceDowngration(fallbackClass = LimitFallBackServiceImpl.class, resourceId = "limit01")
    public String limit(String username) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "LIMIT " + username;
    }
}
