package com.atguigu.java_collection.genericity;

/**
 * @author hike97
 * @create 2018-11-12 15:06
 * @desc 接口泛型
 * 接口泛型字母只能使用在方法中，不能使用全局常量中
 **/
public interface Comparator<T> {
	void compare(T t);
}


