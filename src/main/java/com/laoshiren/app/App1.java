package com.laoshiren.app;

import com.laoshiren.bean.Dog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @date: 2023/1/4 10:43
 * @author: lasohiren
 */
public class App1 {

    public static void main(String[] args) {
        ApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
        Object cat = ctx.getBean("cat");
        System.out.println(cat);
        Dog bean = ctx.getBean(Dog.class);
        System.out.println(bean);

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
            // cat
            // com.laoshiren.bean.Dog#0 全路径的类名
        }

    }

}
