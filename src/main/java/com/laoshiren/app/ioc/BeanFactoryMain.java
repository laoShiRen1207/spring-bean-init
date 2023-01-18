package com.laoshiren.app.ioc;

import com.laoshiren.app.bean.Bean1;
import com.laoshiren.app.bean.Config;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author laoshiren
 * @Date 15:25 2023/1/10
 */
public class BeanFactoryMain {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // bean 定义（class  scope ）BeanDefinition
        AbstractBeanDefinition configBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(Config.class)
                .getBeanDefinition();
        // 注册bean
        beanFactory.registerBeanDefinition("config", configBeanDefinition);

        // 只有自己注册的config 没有其他的Bean1 和 Bean2 是因为没有后置处理器处理 解析Bean1 Bean2
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println("\t"+beanDefinitionName);
        }
        System.out.println("------------------------------");
        // 给 beanFactory 添加一些常用的后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 只有自己注册的config 没有其他的Bean1 和 Bean2 是因为没有后置处理器处理 解析Bean1 Bean2
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println("\t"+beanDefinitionName);
        }

        // 获取Bean 后置处理器 补充了Bean
        Map<String, BeanFactoryPostProcessor> beansOfType =
                beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        // 执行后置处理器方法
        beansOfType
                .forEach((key, value) -> value.postProcessBeanFactory(beanFactory));
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println("\t"+beanDefinitionName);
        }
        System.out.println("------------------------------");
        // Bean 后置处理器， 对bean 的生命周期各个阶段提供扩展 例如@Autowired
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        // 添加后置处理器 getBean 的时候就会执行后置处理器
        beanPostProcessorMap.values()
                .forEach(beanFactory::addBeanPostProcessor);
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println("\t"+beanDefinitionName);
        }
        // 创建所有的单例Bean
        beanFactory.preInstantiateSingletons();
        // 用到了才创建
        Bean1 bean = beanFactory.getBean(Bean1.class);
        System.out.println(bean);

        /*
        BeanFactory
            1. 不会调用BeanFactory 的后置处理器
            2. 不会主动添加Bean后置处理器
            3. 不会主动初始化单例
            4. 不会解析BeanFactory 还不会解析#{} 和 ${}
            Bean后置处理器有排序
         */

    }
}
