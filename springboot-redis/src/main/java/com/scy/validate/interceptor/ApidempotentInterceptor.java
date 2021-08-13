package com.scy.validate.interceptor;

import com.scy.validate.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类名： ApidempotentInterceptor <br>
 * 描述：TODO <br>
 * 创建日期： 2020/4/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class ApidempotentInterceptor {
    @Autowired
    private TokenService tokenService;
}
