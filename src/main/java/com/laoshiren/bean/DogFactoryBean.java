package com.laoshiren.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @date: 2023/1/4 14:01
 * @author: lasohiren
 */
public class DogFactoryBean implements FactoryBean<Dog> {

    @Override
    public Dog getObject() throws Exception {
        return new Dog();
    }

    @Override
    public Class<?> getObjectType() {
        return Dog.class;
    }
}
