package com.atguigu.java_juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hike97
 * @create 2018-09-29 15:18
 * @desc 读写锁 --乐观锁
 **/
public class TestReadWriteLock {
    /*
      1.readWriteLock : 读写锁
        写写/读写需要“互斥”
        读读不需要互斥
     */
    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();

        new Thread( ()->{
            rw.set( (int)(Math.random()*101) );
        },"Write").start();

        for (int i = 0; i < 100; i++) {
            new Thread( ()->{
                rw.get();
            },"Read").start();
        }
    }
}
class ReadWriteLockDemo{
    private int number  = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    //读
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println( Thread.currentThread().getName() + ":" + number );
        } finally {
            lock.readLock().unlock();
        }
    }
    //写
    public void set(int number){
        try {
            System.out.println( Thread.currentThread().getName() );
            this.number = number;
        } finally {

        }
    }
}