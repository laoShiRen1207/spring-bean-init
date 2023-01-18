package com.laoshiren.app.bean;

/**
 * @Author laoshiren
 * @Date 16:18 2023/1/11
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.laoshiren.app.bean.scan")
public class Config {

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

}