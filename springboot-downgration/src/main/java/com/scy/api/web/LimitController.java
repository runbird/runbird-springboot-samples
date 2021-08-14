package com.scy.api.web;

import com.scy.api.service.LimiterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class LimitController {

    // @Autowired
    @Resource(name = "limiterServiceImpl")
    private LimiterService limiterService;

    @GetMapping(value = "/hello/{username}")
    public String hello(@PathVariable("username") String username) {
        return limiterService.limit(username);
    }
}
