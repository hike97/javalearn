package com.atguigu.entity;

import lombok.Data;

/**
 * @author hike97
 * @create 2018-09-07 17:20
 * @desc
 **/
@Data
public class Godness {

    private String name;

    public Godness(String name) {
        this.name = name;
    }
}
