package com.laoshiren.app.interfaces.initializing;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @date: 2023/1/21 21:29
 * @author: lasohiren
 */
public class InitializingRealBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化 ");
    }

    @PostConstruct
    public void init(){
        System.out.println("注解的方式");
    }

}
