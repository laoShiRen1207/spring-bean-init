package com.laoshiren.app.processor.beanfactory;

import com.laoshiren.app.bean.Config;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @date: 2023/1/19 03:08
 * @author: lasohiren
 */
public class MockAnnotationConfigurationBeanFactoryPostProcessor {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config",Config.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        MetadataReader metadataReader =
                metadataReaderFactory.getMetadataReader(new ClassPathResource("com/laoshiren/app/bean/Config.class"));
        //
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取被注解标注的方法
        Set<MethodMetadata> annotatedMethods = annotationMetadata.getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata annotatedMethod : annotatedMethods) {
            System.out.println(annotatedMethod);
            // 创建BeanDefinition
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
            // 没搞懂如何拿到 config
            beanDefinitionBuilder.setFactoryMethodOnBean(annotatedMethod.getMethodName(), "config")
                    // 有参数的方法需要指定装配模式 默认是No，
                    .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            // 初始化方法
            String initMethod = annotatedMethod.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
            if (StringUtils.hasText(initMethod)) {
                beanDefinitionBuilder.setInitMethodName(initMethod);
            }
            // 同样还有销毁的方法
            // beanDefinitionBuilder.setDestroyMethodName("");
            AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            // 方法名字作为bean Name
            beanFactory.registerBeanDefinition(annotatedMethod.getMethodName(), beanDefinition);
        }
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        context.close();
    }

}
