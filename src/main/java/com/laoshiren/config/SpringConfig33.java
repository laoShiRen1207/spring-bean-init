package com.laoshiren.config;

import com.laoshiren.bean.DogFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2023/1/4 13:55
 * @author: lasohiren
 */
@Configuration(proxyBeanMethods = true)
public class SpringConfig33 {
    @Bean
    public DogFactoryBean dog(){
        return new DogFactoryBean();
    }
}
