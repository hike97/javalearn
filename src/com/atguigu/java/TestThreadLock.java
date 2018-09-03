package com.atguigu.java;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全学习
 */
public class TestThreadLock {
      //reentrantLock 是lock的子类
      private Lock lock = new ReentrantLock();

    /**
     * 锁测试方法
     * @param thread
     */
      private void method(Thread thread) throws InterruptedException {
          //lock.lock();//获取所对象
          if (lock.tryLock( 5, TimeUnit.SECONDS )){
              try{
                  System.out.println( "线程名：" + thread.getName() + "获得了锁");
                  //Thread.sleep( 3000 );
              }catch(Exception e){
                  e.printStackTrace();
              }finally {
                  System.out.println( "线程名：" + thread.getName() + "释放了锁");
                  lock.unlock();
              }
          }

      }

     /*Lock获取锁的方式：
       Lock：在获取锁的时候，如果拿不到锁，就一直处于等待状态，
       tryLock：是有一个Boolean的返回值的，如果没有拿到锁，
       直接返回false，停止等待，它不会像Lock()那样去一直等待获取锁。*/
    public static void main(String[] args){

        TestThreadLock threadLockTest = new TestThreadLock();

        Thread t1 = new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    threadLockTest.method( Thread.currentThread() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1" );

        Thread t2 = new Thread( () -> {
            try {
                threadLockTest.method( Thread.currentThread() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2" );

        t1.start();
        t2.start();
    }
}
