package com.laoshiren.app.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @Author laoshiren
 * @Date 11:00 2023/1/10
 */
@Slf4j
public class Component1 {

    private Component2 component2;

    @Autowired
    public void setComponent2(Component2 component2){
        log.info("@Autowired 生效 ");
        this.component2 = component2;
    }

    public Component2 getComponent2() {
        return component2;
    }

    private Component3 component3;

    public Component3 getComponent3() {
        return component3;
    }

    @Resource
    public void setComponent3(Component3 component3) {
        log.info("@Resource 生效 ");
        this.component3 = component3;
    }

    private String home;

    public String getHome() {
        return home;
    }

    @Autowired
    public void setHome(@Value("${JAVA_HOME}") String home) {
        log.info("@Value 生效 {}", home);
        this.home = home;
    }

    @PostConstruct
    public void init() {
        log.info("初始化");
    }

    @PreDestroy
    public void destroy() {
        log.info("销毁");
    }

    public Component1() {
        log.info("构造");
    }
}
