package com.laoshiren.app.processor.autowired;

import com.laoshiren.app.bean.Component1;
import com.laoshiren.app.bean.Component2;
import com.laoshiren.app.bean.Component3;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author laoshiren
 * @Date 14:26 2023/1/11
 */
public class MyDebugAutowiredAnnotationBeanPostProcessor {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 因为传入的是一个对象所以，不会执行创建过程，依赖注入，初始化过程
        beanFactory.registerSingleton("component2", new Component2());
        beanFactory.registerSingleton("component3", new Component3());
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());

        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setBeanFactory(beanFactory);
        Component1 component1 = new Component1();
        // 依赖注入阶段调用 解析@Autowired @Value
        postProcessor.postProcessProperties(null, component1,"component1");
        System.out.println(component1);

        // 修饰属性查找值
        Field component2 = Component1.class.getDeclaredField("component2");
        DependencyDescriptor dependencyDescriptorField = new DependencyDescriptor(component2, false);
        Object propertiesBean = beanFactory.doResolveDependency(dependencyDescriptorField, null, null, null);
        System.out.println(propertiesBean);

        // 方法查找值
        Method setComponent2 = Component1.class.getDeclaredMethod("setComponent2", Component2.class);
        DependencyDescriptor dependencyDescriptorMethod =
                new DependencyDescriptor(new MethodParameter(setComponent2,0),false);
        Object methodFindBean = beanFactory.doResolveDependency(dependencyDescriptorMethod, null, null, null);

        System.out.println(methodFindBean);
    }

}
