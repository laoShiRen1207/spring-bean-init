package org.springframework.aop.framework.autoproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Author laoshiren
 * @Date 14:03 2023/2/3
 */
public class AdvisorCreateTimeMain {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // beanFactoryPostProcessor
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(Config.class);
        context.refresh();

        Bean1 bean = context.getBean(Bean1.class);
        bean.foo();


        context.close();
    }



    @Configuration
    static class Config {

        @Bean
        public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator(){
            return new AnnotationAwareAspectJAutoProxyCreator();
        }

        @Bean
        public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
            return new AutowiredAnnotationBeanPostProcessor();
        }
        @Bean
        public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor(){
            return new CommonAnnotationBeanPostProcessor();
        }

        // 低级切面类
        @Bean
        public Advisor advisor3(MethodInterceptor advice3) {
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut, advice3);
        }

        @Bean
        public MethodInterceptor advice3() {
            return invocation -> {
                System.out.println("advice before...");
                return invocation.proceed();
            };
        }

        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }

        @Bean
        public Bean2 bean2(){
            return new Bean2();
        }
    }
}

class Bean1 {
    public void foo(){
        System.out.println("bean1 foo");
    }

    public Bean1(){
        System.out.println("bean  construct");
    }

    @PostConstruct
    public void init(){
        System.out.println("bean Init");
    }
}


class Bean2 {

    @Autowired
    public void setBean1(Bean1 bean1){
        System.out.println("setBean1");
    }

    public Bean2(){
        System.out.println("bean2  construct");
    }

    @PostConstruct
    public void init(){
        System.out.println("bean2 Init");
    }
}