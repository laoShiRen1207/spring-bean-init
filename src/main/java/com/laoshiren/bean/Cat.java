package com.laoshiren.bean;

import org.springframework.stereotype.Component;

/**
 * @date: 2023/1/4 10:31
 * @author: lasohiren
 */
@Component("tom")
public class Cat {

    public Cat() {

    }

    int age;

    public Cat(int a) {
        this.age = a;
    }


    @Override
    public String toString() {
        return "cat:age:" + this.age;
    }
}
