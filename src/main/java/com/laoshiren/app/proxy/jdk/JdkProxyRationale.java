package com.laoshiren.app.proxy.jdk;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Author laoshiren
 * @Date 14:07 2023/1/31
 */
public class JdkProxyRationale {

    interface Foo {
        void foo();

        int bar();
    }

    @Slf4j
    static class Target implements Foo {
        @Override
        public void foo() {
            log.info("foo");
        }

        @Override
        public int bar() {
            log.info("bar");
            return 10;
        }
    }

    interface InvocationHandler {
        Object invoke(Method method, Object[] args) throws Throwable;
    }

    @AllArgsConstructor
    static class $Proxy0 implements Foo{
        private InvocationHandler handler;

        @Override
        public void foo() {
            try {
                Method foo = Foo.class.getMethod("foo");
                handler.invoke(foo, new Object[0] );
            }  catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable e) {
                throw new UndeclaredThrowableException(e);
            }
        }

        @Override
        public int bar() {
            try {
                Method bar =  Foo.class.getMethod("bar");
                return (int) handler.invoke(bar, new Object[0] );
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable e) {
                throw new UndeclaredThrowableException(e);
            }
        }
    }

    public static void main(String[] args) {
        Foo foo = new $Proxy0((method, methodArgs)-> {
            System.out.println("before");
            Object invoke = method.invoke(new Target(), args);
            System.out.println("after");
            return invoke;
        });
        foo.foo();
    }

}
