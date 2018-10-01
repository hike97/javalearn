package com.atguigu.java_thread.day17;

/**
 * @author hike97
 * @create 2018-09-19 15:48
 * @desc 线程安全问题
 **/
class Window2 implements Runnable{
    int ticket  = 100;
    //Object o = new Object();
    @Override
    public void run() {

        while (true) {
        synchronized (this){//this 表示当前对象，本题中即为w
            if (ticket >0) {
                try {
                    Thread.currentThread().sleep( 10 );
                    System.out.println( Thread.currentThread().getName() + "售票，票号" + ticket-- );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        }
    }
}

public class Thread_safe {
    /*
     1.线程安全问题存在原因：
           由于一个线程在操作共享数据过程中，未执行完毕的情况下，另外的线程参与进来
           导致共享数据存在了安全问题。
     2.如何解决线程的安全问题？
     必须让一个线程操作共享数据完毕以后，其他线程才有机会参与共享数据的操作
     3.java 如何实现线程的安全：线程的同步机制
     方式一：同步代码块
     synchronized(同步监视器）｛
            //需要被同步的代码块（即为操作共享数据的代码）
     ｝
     1.共享数据：多个线程共同操作的同一个数据（变量）
     2.同步监视器：任何一个类的对象来充当。那个线程获取监视器，谁就执行
     要求：所有线程必须共用同一把锁！
     大括号里被同步的代码。俗称：锁
     注：在实现的方式中，考虑同步的话，可使用this来充当锁。
     继承方式 需要static object 保证公用同一个锁
     方式二：同步方法
    4.线程同步的弊端：由于同一个时间只有一个线程访问共享数据，效率变低了。
     */
    public static void main(String[] args) {
        Window2 w = new Window2();
        Thread t1 = new Thread( w, "窗口1" );
        Thread t2 = new Thread( w, "窗口2" );
        Thread t3 = new Thread( w, "窗口3" );

        t1.start();
        t2.start();
        t3.start();
    }
    /**
     * 释放锁操作
     *  1.当前线程同步方法 同步代码块 执行结束
     *  2.遇到 break return
     *  3.出现了未处理的Error Exception
     *  4.wait() 线程暂停 并释放锁
     *
     *  sleep 不会释放锁
     */
}
