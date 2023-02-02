package com.laoshiren.app.proxy.jdk;

import com.laoshiren.app.service.IService;
import com.laoshiren.app.service.impl.MyService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author laoshiren
 * @Date 13:21 2023/1/31
 */
@Slf4j
public class JdkProxyDemo {

    public static void main(String[] args) {
        IService service = new MyService();
        // 用来加载运行时动态生成的字节码
        ClassLoader classLoader = JdkProxyDemo.class.getClassLoader();
        // 需要代理的接口
        Class[] proxyClass = new Class[]{IService.class};
        // 增强的方法
        InvocationHandler invocationHandler = (objectProxy, method, methodArgs) -> {
            log.info("代理前置处理");
            Object invoke = method.invoke(service, methodArgs);
            log.info("代理后置处理");
            return invoke;
        };
        IService proxy = (IService) Proxy.newProxyInstance(classLoader, proxyClass, invocationHandler);
        proxy.foo();
    }

}
