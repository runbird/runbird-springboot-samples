package com.scy.common.config;

import com.scy.api.request.StringToLocalDateTimeConvert;
import com.scy.api.response.CommonResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * 类名： UnifiedReturnConfig <br>
 * 描述： HttpMessageConverter转换原理解析  <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@EnableWebMvc
@Configuration
public class UnifiedReturnConfig implements WebMvcConfigurer {

    //@RestControllerAdvice（"com.com.com.scy.api.com.com.scy.controller"）就无效?
    @RestControllerAdvice
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {

        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            if (body instanceof CommonResult) {
                return body;
            }
            return new CommonResult<>(body);
        }
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConvert());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new com.scy.common.config.LoginUserArgumentResolver());
    }
}
//    private List<HandlerMethodArgumentResolver> getDefaultArgumentResolvers() {
//        List<HandlerMethodArgumentResolver> resolvers = new ArrayList();
//        resolvers.add(new RequestParamMethodArgumentResolver(this.getBeanFactory(), false));
//        //其他内置 resolver
//
//        resolvers.add(new RequestResponseBodyMethodProcessor(this.getMessageConverters(), this.requestResponseBodyAdvice));
//    ...
//    ...
//
//        if (this.getCustomArgumentResolvers() != null) {
//            resolvers.addAll(this.getCustomArgumentResolvers());
//        }
//
//    ...
//    ...
//        return resolvers;
//    }