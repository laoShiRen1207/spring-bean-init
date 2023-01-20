package com.laoshiren.app.mapper.processor.bean;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * @date: 2023/1/20 23:15
 * @author: lasohiren
 */
public class MapperBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            // 找注解省略
            // 找包省略
            // 找 mapper
            PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = patternResolver
                    .getResources("classpath:com/laoshiren/app/mapper/mapper/**/*.class");
            // 读取元数据
            CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
            AnnotationBeanNameGenerator beanNameGenerator =  new AnnotationBeanNameGenerator();
            for (Resource resource : resources) {
                MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                // 类属性
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                // 不是接口就跳过
                if (!classMetadata.isInterface()) {
                    continue;
                }
                AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                // 没有Mapper 注解就跳过
                boolean hasMapper = annotationMetadata.hasAnnotation(Mapper.class.getName());
                if (!hasMapper) {
                    continue;
                }
                // 准备生成MapperFactoryBean
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition(MapperFactoryBean.class)
                        // 添加构造方法的值
                        .addConstructorArgValue(classMetadata.getClassName())
                        // 自动装配 去装SqlSessionFactory
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .getBeanDefinition();
                // 需要用各自的FactoryBean 里实现的类的名字
                AbstractBeanDefinition beanNameDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition(classMetadata.getClassName())
                        .getBeanDefinition();
                // 更具beanNameDefinition 创建BeanName
                String beanName = beanNameGenerator.generateBeanName(beanNameDefinition, registry);
                // 直接注册beanDefinition 会导致后注册上来的BeanName 与前面的一致（因为都是MapperFactoryBean）
                // 所以需要重新注册一个Bean 防止覆盖
                // 注册到beanFactory
                registry.registerBeanDefinition(beanName, beanDefinition);
            }
        } catch (IOException e) {
            throw new BeanCreationException(e.getMessage());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
