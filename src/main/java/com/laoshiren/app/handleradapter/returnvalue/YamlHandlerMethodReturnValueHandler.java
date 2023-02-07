package com.laoshiren.app.handleradapter.returnvalue;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author laoshiren
 * @Date 13:25 2023/2/6
 */
public class YamlHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    private static final Yaml YAML = new Yaml();
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.hasParameterAnnotation(Yml.class);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        String dump = YAML.dump(returnValue);
        System.out.println(">>>>>>>>>>>>>>>>dump - "+ dump);
        // 获取响应
        HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        // 设置编码
        nativeResponse.setContentType("text/plain;charset=utf-8");
        // 输出
        nativeResponse.getWriter().print(dump);
        // 设置请求已经处理完毕
        mavContainer.setRequestHandled(true);
    }
}
