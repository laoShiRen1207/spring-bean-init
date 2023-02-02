package com.laoshiren.app.aspect;

import com.laoshiren.app.service.IService;
import com.laoshiren.app.service.impl.MyService;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Author laoshiren
 * @Date 14:21 2023/2/2
 */
public class AspectMain {

    public static void main(String[] args) {
        IService iService = new MyService();
        // 准备切点
        // Pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.laoshiren.*..*.foo())");
        // 准备通知
        MethodInterceptor advice = (invocation)->{
            System.out.println("before");
            Object proceed = invocation.proceed();
            System.out.println("after");
            return proceed;
        };
        // 准备切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,advice);
        // 创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(iService);
        proxyFactory.setInterfaces(iService.getClass().getInterfaces());
        proxyFactory.addAdvisor(advisor);
        IService proxy = (IService)proxyFactory.getProxy();
        System.out.println(proxy.getClass().getName());
        proxy.foo();

    }

}
