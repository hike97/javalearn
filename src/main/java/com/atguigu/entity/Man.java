package com.atguigu.entity;

import lombok.Data;

/**
 * @author hike97
 * @create 2018-09-07 17:19
 * @desc
 **/
@Data
public class Man {
    private Godness godness;

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }
}
