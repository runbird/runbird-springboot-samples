package com.scy.validate;

import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * 类名： TokenServiceImpl <br>
 * 描述：TODO <br>
 * 创建日期： 2020/4/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class TokenServiceImpl implements TokenService {
    @Override
    public ServerResponse createToken() {
        return null;
    }

    @Override
    public void checkToken(HttpServletRequest request) {

    }
}
