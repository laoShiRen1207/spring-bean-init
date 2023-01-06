package com.laoshiren.bean;

import lombok.Data;

/**
 * @Author laoshiren
 * @Date 14:15 2023/1/5
 */
@Data
public class Cat {

    private String name;

    private Integer age;

    public String toString(){
        return getAge()+"岁的"+getName();
    }

}
