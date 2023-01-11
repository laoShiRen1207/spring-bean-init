package com.laoshiren.app.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author laoshiren
 * @Date 14:06 2023/1/11
 */
@ConfigurationProperties(prefix = "java")
@Data
public class Bean4Properties {

    private String home;

    private String version;

}
