package com.laoshiren.app.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Slf4j
public class DispatchServletInit {

    public static void main(String[] args) {
        // 支持内嵌web 容器
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

    }

}

@Configuration
@ComponentScan
class WebConfig {
    // 内嵌web容器的配置类 有3项是必须配置的
    // 1. 内嵌web 容器工厂
    // 2. DispatchServlet
    // 3. 注册Bean 将DispatchServlet 注册到web 容器中
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(
            DispatcherServlet dispatcherServlet){
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        // tomcat启动时 就初始化，如果使用多个Servlet 那数字就代表优先级
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

}