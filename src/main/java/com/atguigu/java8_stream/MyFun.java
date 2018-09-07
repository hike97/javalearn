package com.atguigu.java8_stream;

/**
 * @author hike97
 * @create 2018-09-07 19:41
 * @desc 接口中的默认方法
 **/
public interface MyFun {

    default String getName(){
        return "哈哈哈";
    }
}
