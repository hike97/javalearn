package com.atguigu.java8_arrayList;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author hike97
 * @create 2018-09-21 14:50
 * @desc jdk7 和 jdk8 的区别
 **/
public class ListTest {

    @Test
    public void test_1() {
        ArrayList<Object> list = new ArrayList<>();
        System.out.println( list.size() );
    }
}
