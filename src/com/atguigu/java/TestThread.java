package com.atguigu.java;

class SubThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <=100 ; i++) {
            System.out.println(Thread.currentThread().getName()+""+i);
        }
    }
}
public class TestThread {
    /*
     * 创建一个子线程，完成1到100输出
     * 主线程执行同样的操作
     */
    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        //start 启动线程+调用相应的run方法
        subThread.start();
        //subThread.start();//一个线程只能够执行一次start
        subThread.run();//没有启动线程 相当于调方法

        for (int i = 0; i <=100 ; i++) {
            System.out.println(Thread.currentThread().getName()+""+i);
        }
    }
}
