package com.laoshiren.app.bean;

import com.laoshiren.app.ioc.BeanFactoryMain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author laoshiren
 * @Date 16:19 2023/1/11
 */
@Slf4j
public class Bean1 {

    @Autowired
    private Bean2 bean2;

    public Bean1(){
        log.info("bean1 构造");
    }


}
