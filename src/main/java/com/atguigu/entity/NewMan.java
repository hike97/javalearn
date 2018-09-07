package com.atguigu.entity;

import lombok.Data;

import java.util.Optional;

/**
 * @author hike97
 * @create 2018-09-07 19:24
 * @desc optional包装类
 **/
@Data
public class NewMan {

    private Optional<Godness> godness = Optional.empty();//默认是null

    public NewMan() {
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }
}
