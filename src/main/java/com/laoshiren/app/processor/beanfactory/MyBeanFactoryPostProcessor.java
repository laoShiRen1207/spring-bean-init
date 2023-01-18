package com.laoshiren.app.processor.beanfactory;

import com.laoshiren.app.bean.Config;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author laoshiren
 * @Date 16:16 2023/1/11
 */
public class MyBeanFactoryPostProcessor {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();
        ctx.registerBean("config", Config.class);
        // 需要添加BeanFactory后置处理器
        ctx.registerBean(ConfigurationClassPostProcessor.class);
        // 扫描@ComponentScan @Bean @Import @ImportResource
        ctx.refresh();
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println("\t" + beanDefinitionName);
        }
        ctx.close();

    }

}
