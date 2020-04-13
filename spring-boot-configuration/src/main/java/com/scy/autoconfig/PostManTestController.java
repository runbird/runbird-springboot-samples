package com.scy.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名： PostManTestController <br>
 * 描述：TODO <br>
 * 创建日期： 2019/8/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@RestController
public class PostManTestController {

    @PostMapping(value = "/api/test")
    public String queryPostMan(@RequestBody String str) {
        log.info("info: {}",str);
        return str;
    }
}
