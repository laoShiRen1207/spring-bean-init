package org.springframework.aop.framework.autoproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/**
 * @Author laoshiren
 * @Date 13:05 2023/2/3
 */
public class AdvisorMain {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(Aspect1.class);
        context.registerBean(Config.class);
        // 解析@Bean
        context.registerBean(ConfigurationClassPostProcessor.class);
        // Bean 处理器 将Aspect 高级切面转换低级切面 ，根据切面创建代理对象 （ProxyFactory）
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);
        context.refresh();
        // 获取这个processor
        AnnotationAwareAspectJAutoProxyCreator proxyCreator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
        // 根据目标获取有资格的切面 看看那些advisor 可以应用于目标类（低级切面）
        // 高级切面会转换成低级切面
        List<Advisor> targetAdvisor = proxyCreator.findEligibleAdvisors(Target1.class, "target");
        // for (Advisor advisor : targetAdvisor) {
        //     System.out.println(advisor.getClass().getName());
        // }
        // 代理对象
        Object o = proxyCreator.wrapIfNecessary(new Target1(), "target1", "target1");
        // 原始对象
        Object o1 = proxyCreator.wrapIfNecessary(new Target2(), "target2", "target2");
        Target1 target1 = (Target1)o;
        target1.foo();
    }

}

class Target1 {
    public void foo() {
        System.out.println("target1 foo");
    }
}


class Target2 {
    public void bar() {
        System.out.println("target1 bar");
    }
}

// 高级切面类
@Aspect
class Aspect1 {

    @Before("execution(* foo())")
    public void before() {
        System.out.println("aspect1 before...");
    }


    @After("execution(* foo())")
    public void after() {
        System.out.println("aspect1 after...");
    }

}

@Configuration
class Config {
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
            Object proceed = invocation.proceed();
            System.out.println("advice after...");
            return proceed;
        };
    }
}