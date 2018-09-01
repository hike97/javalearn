package com.atguigu.java;
//单线程！一条线串起来 单线程
public class TestMain {

    public static void main(String[] args) {
        method2("atguigu.com");
    }

    public static void method1(String str) {
        System.out.println("method 1 ```");//3
        System.out.println(str);//4 end 传入atguigu
    }

    public static void method2(String str) {
        System.out.println("method2");//1
        method1(str);//2
    }

}
