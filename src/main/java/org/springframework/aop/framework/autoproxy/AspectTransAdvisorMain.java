package org.springframework.aop.framework.autoproxy;

import org.aspectj.lang.annotation.*;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.aop.aspectj.SimpleAspectInstanceFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AspectTransAdvisorMain {

    public static void main(String[] args) {
        List<Advisor> list = new ArrayList<>();
        // Aspect的实例工场
        AspectInstanceFactory factory = new SimpleAspectInstanceFactory(Aspect1.class);
        // 获取Aspect切面类定义的所有方法
        Method[] declaredMethods = Aspect1.class.getDeclaredMethods();
        // 遍历数组准备转换成低级切面
        for (Method declaredMethod : declaredMethods) {
            // 查看方法上是否标注指定注解
            boolean beforeAnnotationPresent = declaredMethod.isAnnotationPresent(Before.class);
            if (beforeAnnotationPresent) {
                // 获取注解
                Before methodBeforeAnnotation = declaredMethod.getAnnotation(Before.class);
                String expression = methodBeforeAnnotation.value();
                // 创建切点对象
                AspectJExpressionPointcut expressionPointcut = new AspectJExpressionPointcut();
                expressionPointcut.setExpression(expression);
                // 前置通知底层通知类 AspectJMethodBeforeAdvice
                AspectJMethodBeforeAdvice beforeAdvice = new AspectJMethodBeforeAdvice(
                        // 需要前置通知的方法
                        declaredMethod,
                        // 切点
                        expressionPointcut,
                        // 切面的实例工程
                        factory
                );
                // 切面
                Advisor advisor = new DefaultPointcutAdvisor(expressionPointcut,beforeAdvice);
                list.add(advisor);
            }
        }
        System.out.println(list.size());
    }


    @Aspect
    static class Aspect1 {

        @Before("execution(* foo())")
        public void before() {
            System.out.println("aspect1 before...");
        }


        @After("execution(* foo())")
        public void after() {
            System.out.println("aspect1 after...");
        }

    }
}


