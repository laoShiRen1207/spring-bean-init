package com.laoshiren.app.handlermapping;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @Author laoshiren
 * @Date 9:41 2023/2/6
 */
public class RequestMappingHandlerMappingMain {

    public static void main(String[] args) throws Exception {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig1.class);
        // 用于解析 @Request @GetMapping 派生注解 并存储控制器方法映射关系
        RequestMappingHandlerMapping hm = context.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = hm.getHandlerMethods();
        handlerMethods.forEach((k,v)->{
            System.out.println(k+" = "+v);
        });

        // 根据请求获取控制器方法
        MockHttpServletRequest request = new MockHttpServletRequest("GET","/get");
        // 返回控制器方法 处理器执行链，（控制器方法，拦截器）
        HandlerExecutionChain handlerChain = hm.getHandler(request);
        System.out.println(handlerChain);
    }
}

@Configuration
@ComponentScan(basePackages = {"com.laoshiren.app.controller"})
class WebConfig1 {

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
}