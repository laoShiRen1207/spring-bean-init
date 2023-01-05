package com.laoshiren.app;

import com.laoshiren.config.SpringConfig4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @date: 2023/1/4 10:43
 * @author: lasohiren
 */
public class App4 {

    public static void main(String[] args) {
        ApplicationContext  ctx = new AnnotationConfigApplicationContext(SpringConfig4.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
