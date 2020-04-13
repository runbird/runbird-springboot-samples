package com.scy.common.config;

import com.scy.common.api.annoation.LoginUser;
import com.scy.common.vo.LoginUserVO;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 类名： LoginUserArgumentResolver <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //  return false;
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //  return null;
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        LoginUserVO loginUser = new LoginUserVO();
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            loginUser.setToken(token);
        }
        loginUser.setUserId(request.getHeader("userId"));
        loginUser.setUsername(request.getHeader("usernmae"));
        return loginUser;
    }
}
