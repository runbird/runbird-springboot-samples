package com.scy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 类名： BindingController <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@RestController
@RequestMapping("/binding")
public class BindingController {

    @GetMapping("/{date}")
    public String bindingString(@PathVariable LocalDateTime localDateTime) {
        log.info(localDateTime.toString());
        return localDateTime.toString();
    }

}
