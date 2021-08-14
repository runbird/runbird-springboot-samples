package com.scy.common.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @program: springboot-configuration
 * @author: Suocaiyuan
 * @date: 2021-08-14 14:42
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "env")
@NoArgsConstructor
@AllArgsConstructor
public class EnvConfig {

    //区分环境
    private String profile;
}
