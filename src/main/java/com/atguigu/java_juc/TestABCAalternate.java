package com.atguigu.java_juc;

/**
 * @author hike97
 * @create 2018-09-29 14:47
 * @desc ABC交替打印
 **/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启三个线程的ID分别为A.B.C
 * 要求输出10次
 * ABCABC... 依次递归
 */
public class TestABCAalternate {
    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();
        new Thread( ()-> {
            for (int i = 1; i <=20 ; i++) {
                ad.loopA(i);
            }
        },"A").start();
        new Thread( ()-> {
            for (int i = 1; i <=20 ; i++) {
                ad.loopB(i);
            }
        },"B").start();
        new Thread( ()-> {
            for (int i = 1; i <=20 ; i++) {
                ad.loopC(i);
                //System.out.println( "---------------------------------" );
            }
        },"C").start();
    }
}
class AlternateDemo{
    private int number  = 1;//表示正在执行线程的标记

    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    //输出A
    public  void  loopA(int totalLoop){
        lock.lock();
        //1.判断
        try {
            if (number !=1){
                condition1.await();
            }
        //2.打印
//            for (int i = 1; i <=5; i++) {
//                System.out.println( Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop );
//            }
            System.out.print( Thread.currentThread().getName());
        //3.唤醒
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public  void  loopB(int totalLoop){
        lock.lock();
        //1.判断
        try {
            if (number !=2){
                condition2.await();
            }
        //2.打印
//            for (int i = 1; i <=15; i++) {
//                System.out.println( Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop );
//            }
            System.out.print( Thread.currentThread().getName());
        //3.唤醒
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public  void  loopC(int totalLoop){
        lock.lock();
        //1.判断
        try {
            if (number !=3){
                condition3.await();
            }
        //2.打印
//            for (int i = 1; i <= 20; i++) {
//                System.out.println( Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop );
//            }
            System.out.print( Thread.currentThread().getName());
            //3.唤醒
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}