package com.laoshiren.app.proxy.cglib;

import com.laoshiren.app.service.impl.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author laoshiren
 * @Date 13:46 2023/1/31
 */
@Slf4j
public class CglibProxyDemo {

    public static void main(String[] args) {
        MyService myService = new MyService();

        MethodInterceptor callback = (o, method, objectsArgs, methodProxy) -> {
            log.info("前置增强");
            Object invoke= null;
            // 方法反射的调用
            // invoke = method.invoke(myService, objects);
            // 避免反射调用 需要目标
            // invoke = methodProxy.invoke(myService, objectsArgs);
            // 内部没有用反射 需要代理
            invoke = methodProxy.invokeSuper(o, objectsArgs);
            log.info("后置增强");
            return invoke;
        };
        MyService proxy = (MyService)Enhancer.create(MyService.class, callback);
        proxy.foo();
    }

}
