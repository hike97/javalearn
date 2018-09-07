package com.atguigu.java8_stream;

/**
 * @author hike97
 * @create 2018-09-07 19:55
 * @desc
 **/
public interface MyInterface {

    default String getName(){
        return "嘿嘿嘿";
    }

    public static void show(){
        System.out.println( "接口中的静态方法" );
    }
}
