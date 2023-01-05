package com.laoshiren.config;

import com.laoshiren.bean.MyImportSelector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author laoshiren
 * @Date 10:58 2023/1/5
 */
@Configuration
@ComponentScan(basePackages = "com.laoshiren.bean")
@Import(MyImportSelector.class)
public class SpringConfig6 {
}
