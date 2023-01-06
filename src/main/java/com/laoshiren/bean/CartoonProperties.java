package com.laoshiren.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author laoshiren
 * @Date 15:22 2023/1/5
 */
@Data
@ConfigurationProperties(prefix = "cartoon")
public class CartoonProperties {

    private Cat cat;

    private Mouse mouse;

}
