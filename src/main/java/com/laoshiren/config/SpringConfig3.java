package com.laoshiren.config;

import com.laoshiren.bean.DogFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @date: 2023/1/4 13:55
 * @author: lasohiren
 */
@ComponentScan({"com.laoshiren.bean"})
public class SpringConfig3 {
    @Bean
    public DogFactoryBean dog(){
        return new DogFactoryBean();
    }
}
