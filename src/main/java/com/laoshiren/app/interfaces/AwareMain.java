package com.laoshiren.app.interfaces;

import com.laoshiren.app.interfaces.aware.BeanNameAwareBean;
import com.laoshiren.app.interfaces.initializing.InitializingRealBean;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @date: 2023/1/21 21:12
 * @author: lasohiren
 */
public class AwareMain {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);

        context.registerBean(AutowireNotWorkConfig.class);

        context.refresh();
    }

}
