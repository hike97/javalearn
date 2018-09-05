package com.atguigu.inter_face;

/**
 * @author hike97
 * @create 2018-09-05 16:26
 * @desc 断言接口
 **/
public interface MyPredicate<T> {

    public boolean test(T t);
}
