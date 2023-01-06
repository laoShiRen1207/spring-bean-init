package com.laoshiren.bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author laoshiren
 * @Date 9:56 2023/1/6
 * //
 */
@Component
@EnableConfigurationProperties(CartoonProperties.class)
public class CartoonCatAndMouse {


    private Cat cat;

    private Mouse mouse;


    public CartoonCatAndMouse(CartoonProperties cartoonProperties) {
        cat = new Cat();
        cat.setName(cartoonProperties.getCat() != null &&
                StringUtils.hasText(cartoonProperties.getCat().getName()) ?
                cartoonProperties.getCat().getName() : "tom");
        cat.setAge(cartoonProperties.getCat() != null &&
                cartoonProperties.getCat().getAge() != null ?
                cartoonProperties.getCat().getAge() : 5);
        mouse = new Mouse();
        mouse.setName(cartoonProperties.getMouse() != null &&
                StringUtils.hasText(cartoonProperties.getMouse().getName()) ?
                cartoonProperties.getMouse().getName() : "jerry");
        mouse.setAge(cartoonProperties.getMouse() != null &&
                cartoonProperties.getMouse().getAge() != null ?
                cartoonProperties.getMouse().getAge() : 5);
    }

    public void play() {
        System.out.println(cat + "和" + mouse + "玩tom and jerry ");
    }

}
