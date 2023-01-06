package com.laoshiren.bean;

import lombok.Data;

/**
 * @Author laoshiren
 * @Date 14:16 2023/1/5
 */
@Data
public class Mouse {

    private String name;

    private Integer age;

    public String toString(){
        return getAge()+"岁的"+getName();
    }
}
