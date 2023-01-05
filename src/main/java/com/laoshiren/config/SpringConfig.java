package com.laoshiren.config;

import com.laoshiren.bean.Cat;
import com.laoshiren.bean.Dog;
import com.laoshiren.bean.Mouse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

/**
 * @Author laoshiren
 * @Date 14:17 2023/1/5
 */
public class SpringConfig {

    /**
     * 按类加载
     */
    @Bean
    // 有mouse 这个类就加载
//    @ConditionalOnClass(Mouse.class)
    // 没有这个Wolf类就加载
    @ConditionalOnMissingClass("com.laoshiren.bean.Wolf")
    public Cat tom(){
        return new Cat();
    }

    /**
     * 按Bean 加载
     */
    @Bean
    // 有tom猫的话 就没有jerry
    @ConditionalOnMissingBean(name = "tom")
    public Mouse jerry(){
        return new Mouse();
    }

    @Bean
    // 有猫 还想养条狗
    @ConditionalOnBean(Cat.class)
    public Dog dog(){
        return new Dog();
    }
}
