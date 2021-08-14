package com.scy.api.service.impl;

import com.scy.api.service.LimiterService;
import org.springframework.stereotype.Service;

@Service
public class LimitFallBackServiceImpl implements LimiterService {
    @Override
    public String limit(String username) {
        return "fallback " + username;
    }
}
