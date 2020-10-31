package com.JVM;

/**
 * @author shkstart
 * @create 2020 下午 12:11
 */
public class StackStruTest {
    public static void main(String[] args) {
        //int i = 2 + 3;
        int i = 2;
        int j = 3;
        int k = i + j;

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //执行一个所谓的java程序的时候，真真正正在执行的是一个叫做java虚拟机的进程。
        System.out.println("hello");
        //Runtime 饿汉式 单例
    }
}
