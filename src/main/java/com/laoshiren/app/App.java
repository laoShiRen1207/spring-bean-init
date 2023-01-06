package com.laoshiren.app;

import com.laoshiren.bean.CartoonCatAndMouse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author laoshiren
 * @Date 14:16 2023/1/5
 */
@SpringBootApplication(scanBasePackages = "com.laoshiren.bean")
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class,args);
        CartoonCatAndMouse bean = ctx.getBean(CartoonCatAndMouse.class);
        bean.play();
    }

}
