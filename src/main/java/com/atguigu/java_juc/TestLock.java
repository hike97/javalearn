package com.atguigu.java_juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hike97
 * @create 2018-09-29 10:26
 * @desc 同步代码块 同步方法 同步锁 --用于解决多线程安全问题
 * jdk 1.5 后
 * 同步锁 Lock 显示的锁
 * 其他两种 都是隐式锁
 **/
public class TestLock {
    public static void main(String[] args) {
        Ticket_ ticket_ = new Ticket_();
        new Thread( ticket_,"1号窗口" ).start();
        new Thread( ticket_,"2号窗口" ).start();
        new Thread( ticket_,"3号窗口" ).start();

    }
}
class Ticket_ implements Runnable{
    private  int tick = 100;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){

            lock.lock();

            try {
                if (tick>0){
                    try {
                        Thread.sleep( 200 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( Thread.currentThread().getName() + "售票，余票"+ --tick );
                }
            } finally {
                lock.unlock();//释放锁

            }
        }
    }
}