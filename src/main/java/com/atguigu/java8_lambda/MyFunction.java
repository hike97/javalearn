package com.atguigu.java8_lambda;

/**
 * @author hike97
 * @create 2018-09-05 20:32
 * @desc 自定义接口
 **/
@FunctionalInterface
public interface MyFunction<T> {
    public Integer getValue(Integer num);
}
