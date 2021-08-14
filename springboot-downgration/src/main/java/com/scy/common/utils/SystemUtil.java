package com.scy.common.utils;

import com.scy.common.config.EnvConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @desc:
 * @program: springboot-configuration
 * @author: Suocaiyuan
 * @date: 2021-08-14 14:39
 **/
@Slf4j
public class SystemUtil {

    public boolean isProEnv() {
        var config = SpringContextUtil.getBean(EnvConfig.class);
        var env = config.getProfile();
        log.info("<------------- enviroment: {}", env);
        return "prod".equalsIgnoreCase(env);
    }
}
