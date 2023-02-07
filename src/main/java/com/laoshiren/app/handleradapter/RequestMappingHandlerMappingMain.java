package com.laoshiren.app.handleradapter;

import com.laoshiren.app.handleradapter.argument.TokenArgumentResolver;
import com.laoshiren.app.handleradapter.returnvalue.YamlHandlerMethodReturnValueHandler;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author laoshiren
 * @Date 9:41 2023/2/6
 */
public class RequestMappingHandlerMappingMain {

    public static void main(String[] args) throws Exception {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig2.class);

        RequestMappingHandlerMapping hm = context.getBean(RequestMappingHandlerMapping.class);
        // 根据请求获取控制器方法
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/get");
        // 返回控制器方法 处理器执行链，（控制器方法，拦截器）
//        HandlerExecutionChain handlerChain = hm.getHandler(request);
        MyRequestMappingHandlerAdapter adapter = context.getBean(MyRequestMappingHandlerAdapter.class);

//        ModelAndView modelAndView =
//                adapter.invokeHandlerMethod(
//                        // 请求
//                        request,
//                        // 响应
//                        new MockHttpServletResponse(),
//                        // 对应的handlerMethod
//                        (HandlerMethod) handlerChain.getHandler());
//        System.out.println(modelAndView);
        // 尝试一下Post
//        MockHttpServletRequest postRequest = new MockHttpServletRequest("POST", "/post");
//        postRequest.setParameter("name", "laoshiren");
//        HandlerExecutionChain handlerChainPost = hm.getHandler(postRequest);
//
//        ModelAndView modelAndViewPost =
//                adapter.invokeHandlerMethod(
//                        // 请求
//                        postRequest,
//                        // 响应
//                        new MockHttpServletResponse(),
//                        // 对应的handlerMethod
//                        (HandlerMethod) handlerChainPost.getHandler());
        // 参数解析器 ，RequestBody Cookie 等
        List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();
//        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
//            System.out.println(argumentResolver.getClass().getName());
//        }

//        MockHttpServletRequest tokenRequest = new MockHttpServletRequest("GET", "/token");
//        tokenRequest.addHeader("token","laoshiren");
//        HandlerExecutionChain handlerChainPost = hm.getHandler(tokenRequest);
//
//        ModelAndView modelAndViewToken =
//                adapter.invokeHandlerMethod(
//                        // 请求
//                        tokenRequest,
//                        // 响应
//                        new MockHttpServletResponse(),
//                        // 对应的handlerMethod
//                        (HandlerMethod) handlerChainPost.getHandler());

        System.out.println("-----");
        // 返回值解析器
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
//        for (HandlerMethodReturnValueHandler returnValueHandler : returnValueHandlers) {
//            System.out.println(returnValueHandler.getClass().getName());
//        }

        MockHttpServletRequest yamlRequest = new MockHttpServletRequest("GET", "/yaml");
        HandlerExecutionChain handlerChainYaml = hm.getHandler(yamlRequest);
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        ModelAndView modelAndViewYaml =
                adapter.invokeHandlerMethod(
                        // 请求
                        yamlRequest,
                        // 响应
                        mockHttpServletResponse,
                        // 对应的handlerMethod
                        (HandlerMethod) handlerChainYaml.getHandler());
    }
}

@Configuration
@ComponentScan(basePackages = {"com.laoshiren.app.controller"})
class WebConfig2 {

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(
            DispatcherServlet dispatcherServlet) {
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        // tomcat启动时 就初始化，如果使用多个Servlet 那数字就代表优先级
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    // 加入RequestMappingHandlerMapping
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        MyRequestMappingHandlerAdapter myRequestMappingHandlerAdapter = new MyRequestMappingHandlerAdapter();
        // 请求参数
        TokenArgumentResolver argumentResolver = new TokenArgumentResolver();
        List<HandlerMethodArgumentResolver> arrayList = new ArrayList<>();
        arrayList.add(argumentResolver);
        myRequestMappingHandlerAdapter.setCustomArgumentResolvers(arrayList);
        // 响应参数
        YamlHandlerMethodReturnValueHandler returnValueHandler = new YamlHandlerMethodReturnValueHandler();
        List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
        returnValueHandlers.add(returnValueHandler);
        myRequestMappingHandlerAdapter.setCustomReturnValueHandlers(returnValueHandlers);
        return myRequestMappingHandlerAdapter;
    }

}

class MyRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

    @Override
    public ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        return super.invokeHandlerMethod(request, response, handlerMethod);
    }
}