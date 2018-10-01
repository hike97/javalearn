package com.atguigu.java_juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author hike97
 * @create 2018-09-29 9:18
 * @desc 闭锁
 **/
/*
  CountDownLatch:闭锁，在完成某些运算时，
  只有其他所有线程的运算全部完成，
  当前运算才继续执行。
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        /**
         * 需求：求十个线程结束后耗费时间
         * 现状：十一个线程并行 无法计算时长
         * 解决方案：闭锁。初始化一个值，每次线程执行完后值-1
         */
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();//Type:
        for (int i = 0; i <5 ; i++) {
            new Thread( ld ).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println( "消耗时间为：" + (end - start) );
    }

}

class LatchDemo implements Runnable{
    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50000 ; i++) {
                if (i%2 == 0){
                    System.out.println( i );
                }
            }
        } finally {
            latch.countDown();//闭锁减1
        }

    }
}