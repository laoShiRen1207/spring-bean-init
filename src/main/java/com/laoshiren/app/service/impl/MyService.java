package com.laoshiren.app.service.impl;

import com.laoshiren.app.aspect.match.Ass;
import com.laoshiren.app.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date: 2023/1/23 00:07
 * @author: lasohiren
 */
@Slf4j
@Service
public class MyService implements IService {

    @Override
    @Ass
    public void foo(){
        log.info(" service.foo() ");
    }

}
