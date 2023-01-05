package com.laoshiren.app;

import com.laoshiren.bean.Cat;
import com.laoshiren.bean.Dog;
import com.laoshiren.bean.Mouse;
import com.laoshiren.config.SpringConfig4;
import com.laoshiren.config.SpringConfig5;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @date: 2023/1/4 10:43
 * @author: lasohiren
 */
public class App5 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext  ctx = new AnnotationConfigApplicationContext(SpringConfig5.class);
        ctx.register(Dog.class);
        ctx.register(Mouse.class);
        ctx.registerBean("cat", Cat.class,1);
        ctx.registerBean("cat", Cat.class,2);

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println(ctx.getBean("cat"));
    }

}
