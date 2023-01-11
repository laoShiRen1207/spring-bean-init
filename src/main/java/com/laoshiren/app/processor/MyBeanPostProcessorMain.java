package com.laoshiren.app.processor;

import com.laoshiren.app.bean.Bean4Properties;
import com.laoshiren.app.bean.Component1;
import com.laoshiren.app.bean.Component2;
import com.laoshiren.app.bean.Component3;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author laoshiren
 * @Date 13:12 2023/1/11
 */
public class MyBeanPostProcessorMain {

    public static void main(String[] args) {
        // GenericApplicationContext 是一个【干净】的容器， 没有添加额外的BeanFactory后置处理器 Bean后置处理器
        GenericApplicationContext ctx = new GenericApplicationContext();
        // 注册Bean
        ctx.registerBean("component1", Component1.class);
        ctx.registerBean("component2", Component2.class);
        ctx.registerBean("component3", Component3.class);
        ctx.registerBean("bean4Properties", Bean4Properties.class);
        // 默认的解析器不能解析@Value 值注入
        ctx.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // 注册AutowiredAnnotationBeanPostProcessor
        // 解析@Autowired @Value 注解
        ctx.registerBean(AutowiredAnnotationBeanPostProcessor.class);

        // 解析@Resource @PostConstruct @PreDestroy
        ctx.registerBean(CommonAnnotationBeanPostProcessor.class);

        // Springboot中的后置处理器 用于解析@ConfigurationProperties
        ConfigurationPropertiesBindingPostProcessor.register(ctx.getDefaultListableBeanFactory());

        // 初始化容器
        // 执行BeanFactory 后置处理器
        ctx.refresh();

        Bean4Properties bean4Properties = ctx.getBean(Bean4Properties.class);


        ctx.close();

    }

}
