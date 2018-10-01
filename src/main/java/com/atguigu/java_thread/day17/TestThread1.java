package com.atguigu.java_thread.day17;

/**
 * @author hike97
 * @create 2018-09-19 15:07
 * @desc
 **/
class PrintNum1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            if (i%2 == 0){
                System.out.println( Thread.currentThread().getName() + ":" + i );
            }
        }
    }
}
public class TestThread1 {
    public static void main(String[] args) {
        PrintNum1 p = new PrintNum1();
        Thread thread = new Thread( p );
        thread.start();//启动线程、执行Thread对象生成时构造器形参的对象的run()方法
        Thread thread1 = new Thread( p );
        thread1.start();
    }
}
