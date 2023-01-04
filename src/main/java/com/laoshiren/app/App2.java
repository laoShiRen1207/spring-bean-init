package com.laoshiren.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @date: 2023/1/4 10:43
 * @author: lasohiren
 */
public class App2 {

    public static void main(String[] args) {
        ApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

}
