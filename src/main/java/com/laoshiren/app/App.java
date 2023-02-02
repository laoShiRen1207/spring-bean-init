package com.laoshiren.app;

import com.laoshiren.app.service.impl.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author laoshiren
 * @Date 10:17 2023/1/10
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@Slf4j
public class App {

    public static void main(String[] args)  {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        MyService bean = context.getBean(MyService.class);
        log.info("class "+ bean.getClass());
        bean.foo();

        context.close();

    }
}
