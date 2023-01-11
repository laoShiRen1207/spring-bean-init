package com.laoshiren.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * @Author laoshiren
 * @Date 10:17 2023/1/10
 */
@SpringBootApplication
@Slf4j
public class App {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        App bean = ctx.getBean(App.class);
        // beanFactory
        // IOC
        ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();
        // 属性
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        Object o = singletonObjects.get(beanFactory);
        Map<String, Object> map = (Map<String, Object>) o;
        map.entrySet().stream()
                .filter(it -> StringUtils.startsWithIgnoreCase( it.getKey(),"com"))
                .forEach(e -> System.out.println(e.getKey() + "----" + e.getValue()));

        // ApplicationContext

        // MessageSource 国际化
        System.out.println(ctx.getMessage("user.login.password", null, Locale.CHINA));
        System.out.println(ctx.getMessage("user.login.password", null, Locale.US));

        // ResourceLoader
        // 外部文件 file:
        // classpath:
        // 其他jar包 classpath*:
        Resource resource = ctx.getResource("classpath:application.yml");
        System.out.println(resource);
        // EnvironmentCapable
        ConfigurableEnvironment environment = ctx.getEnvironment();
        System.out.println(environment.getProperty("java_home"));
        // ApplicationEventPublisher 事件
        // 不解释用的太多了

    }
}
