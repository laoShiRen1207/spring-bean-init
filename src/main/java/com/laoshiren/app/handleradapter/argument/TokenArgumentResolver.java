package com.laoshiren.app.handleradapter.argument;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Author laoshiren
 * @Date 13:11 2023/2/6
 */
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    // 是否支持某个参数
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 参数上是否存在@Token 注解
        return parameter.hasParameterAnnotation(Token.class);
    }

    // 具体解析参数
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  // request Response 封装
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("token");
        return token;
    }
}
