package com.laoshiren.app.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @date: 2023/1/21 22:05
 * @author: lasohiren
 */
@Configuration
public class AutowireNotWorkConfig {

    @Autowired
    public void setApplication(ApplicationContext applicationContext){
        System.out.println("application" + applicationContext.getClass());
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }

    @Bean
    public BeanFactoryPostProcessor postProcessor1(){
        return postProcessor1->{
            System.out.println("hello ");
        };
    }

}
