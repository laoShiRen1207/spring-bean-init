package com.laoshiren.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date: 2023/1/23 00:07
 * @author: lasohiren
 */
@Slf4j
@Service
public class MyService {

    public void foo(){
        log.info(" service.foo() ");
    }

}
