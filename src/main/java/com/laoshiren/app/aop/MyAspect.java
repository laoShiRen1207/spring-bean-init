package com.laoshiren.app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @date: 2023/1/23 00:08
 * @author: lasohiren
 */
@Slf4j
@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.laoshiren.app.service.impl.MyService.foo())")
    public void before(){
        log.info(" myAspect before ");
    }

}
