package com.laoshiren.app.processor.beanfactory;

import com.laoshiren.app.bean.Config;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @date: 2023/1/18 22:45
 * @author: lasohiren
 */
public class MockAnnotationBeanFactoryPostProcessor {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // 查找是否存在 ComponentScan 注解
        ComponentScan componentScan =
                AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);

        DefaultListableBeanFactory defaultListableBeanFactory = context.getDefaultListableBeanFactory();

        if (componentScan!=null) {
            for (String basePackage: componentScan.basePackages()) {
                System.out.println(basePackage);
                String filePath = "classpath:*"+basePackage.replace(".","/")+"/**/*.class";
                System.out.println(filePath);
                Resource[] resources = context.getResources(filePath);
                // 没有context 的情况
                resources = new PathMatchingResourcePatternResolver().getResources(filePath);
                // 读取类的元信息的工厂
                CachingMetadataReaderFactory metadataReaderFactory =new CachingMetadataReaderFactory();
                // bean Name 生产
                AnnotationBeanNameGenerator beanNameGenerator =  new AnnotationBeanNameGenerator();
                for (Resource resource : resources) {
                    System.out.println(resource);
                    // 读取类的元信息
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    // 类的元信息
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    // 注解元信息
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    // 无法获取到他的派生注解 比如@Controller @Service
                    boolean hasComponentAnnotation = annotationMetadata.hasAnnotation(Component.class.getName());
                    // 获取派生注解
                    boolean hasMetaComponentAnnotation = annotationMetadata.hasMetaAnnotation(Component.class.getName());

                    if (hasComponentAnnotation || hasMetaComponentAnnotation) {
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                                .genericBeanDefinition(classMetadata.getClassName())
                                .getBeanDefinition();
                        // Bean name
                        String beanName =
                                beanNameGenerator.generateBeanName(beanDefinition, defaultListableBeanFactory);
                        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinition);
                    }


                }

            }
        }

        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        context.close();
    }


}
