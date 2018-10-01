package com.atguigu.java_juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hike97
 * @create 2018-09-21 17:00
 * @desc 测试原子性
 **/
public class TestAtomicDemo {
    /**
     * 一。i++的原子性问题：的实际操作分为三个步骤“读-改-写”
     * int i =  10;
     * i = i++;//10
     *
     * int temp = i; i=10 temp=10
     * i = i+1; i=11
     * i =temp  10
     * 二、原子变量：jdk1.5 后 java.util.concurrent.atomic 包下提供了常用的原子变量
     *  1.volatile 保证内存可见性
     *  2.CAS 算法保证数据的原子性（Compare - And - Swap）非阻塞式
     *      CAS 算法 是 硬件 对于并发操作共享数据的支持
     *      CAS包含三个操作数：
     *       内存值V
     *       预估值A
     *       更新值B,当且V==A时，V=B.否则，将不做任何操作
     */
    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i <10 ; i++) {
            new Thread( atomicDemo ).start();
        }
    }
}

class AtomicDemo implements Runnable{
    //private int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();
    @Override
    public void run() {
        try {
            Thread.sleep( 200 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( Thread.currentThread().getName() + ":" + getSerialNumber() );
    }

    private int getSerialNumber() {

        return serialNumber.getAndIncrement();
    }
}

