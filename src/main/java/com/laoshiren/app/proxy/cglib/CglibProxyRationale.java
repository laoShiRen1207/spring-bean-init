package com.laoshiren.app.proxy.cglib;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Author laoshiren
 * @Date 14:28 2023/2/1
 */
public class CglibProxyRationale {

    public static void main(String[] args) {
        Target target = new Target();
        CglibProxy proxy = new CglibProxy((o, method, objects, methodProxy) -> {
            System.out.println("info - 前置");
            Object invoke
//                    = method.invoke(target, objects);
            = methodProxy.invoke(target, objects);
            System.out.println("info - 后置");
            return invoke;
        });

        proxy.save();
        proxy.save(1);
    }

}

@Slf4j
class Target {

    public void save() {
        log.info("save()");
    }

    public void save(int num) {
        log.info("save(int num)");
    }

    public void save(long num) {
        log.info("save(long num)");
    }
}

// 生成的是子类继承父类的代理
@AllArgsConstructor
class CglibProxy extends Target {
    // 通过MethodInterceptor 去进行增强
    private MethodInterceptor methodInterceptor;

    static Method save0;
    static Method save1;
    static Method save2;
    // 静态代码块先设置方法
    private static void setMethod(){
        try {
            save0 = Target.class.getDeclaredMethod("save");
            save1 = Target.class.getDeclaredMethod("save", int.class);
            save2 = Target.class.getDeclaredMethod("save", long.class);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }


    static MethodProxy saveSuper0Proxy;
    static MethodProxy saveSuper1Proxy;
    static MethodProxy saveSuper2Proxy;

    private static void setMethodProxy(){
        saveSuper0Proxy = MethodProxy.create(Target.class, CglibProxy.class, "()V","save","saveSuper");
        saveSuper1Proxy = MethodProxy.create(Target.class, CglibProxy.class, "(I)V","save","saveSuper");
        saveSuper2Proxy = MethodProxy.create(Target.class, CglibProxy.class, "(J)V","save","saveSuper");
    }

    static {
        setMethod();
        setMethodProxy();
    }

    void saveSuper(){
        super.save();
    }

    void saveSuper(int num){
        super.save(num);
    }

    void saveSuper(long num){
        super.save(num);
    }


    @Override
    public void save() {
        try {
            methodInterceptor.intercept(
                    this, // 代理类对象
                    save0,   // 代理方法
                    new Object[0],  // 参数
                    saveSuper0Proxy           // 方法代理
            );
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(int num) {
        try {
            methodInterceptor.intercept(this, save1, new Object[]{num}, saveSuper1Proxy);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(long num) {
        try {
            methodInterceptor.intercept(this, save2, new Object[]{num}, saveSuper2Proxy);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}