package com.laoshiren.app.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author laoshiren
 * @Date 14:08 2023/2/2
 */
@Aspect
@Component("acMyAspect")
public class MyAspect {


    @Before("execution(* foo())")
    public void before(){
        System.out.println("before");
    }

    @Before("execution(* foo())")
    public void before2(){
        System.out.println("before2");
    }

    @After("execution(* foo())")
    public void after(){
        System.out.println("after");
    }

}
