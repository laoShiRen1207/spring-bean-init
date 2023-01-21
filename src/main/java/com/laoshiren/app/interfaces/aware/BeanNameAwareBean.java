package com.laoshiren.app.interfaces.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * @date: 2023/1/21 21:19
 * @author: lasohiren
 */
public class BeanNameAwareBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, EmbeddedValueResolverAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("name:" +name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext:"+ applicationContext.getClass());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory:"+ beanFactory.getClass());
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {

    }

    @Autowired
    public void laoshiren(ApplicationContext applicationContext){
        System.out.println("注解的方式 applicationContext:"+ applicationContext.getClass());
    }
}
