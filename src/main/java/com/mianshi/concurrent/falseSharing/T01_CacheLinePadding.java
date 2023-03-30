package com.mianshi.concurrent.falseSharing;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName T01_CacheLinePadding
 * @Description 伪共享之缓存行问题
 * @Author hike97
 * @Date 2023/3/23 11:11
 * @Version 1.0
 **/
public class T01_CacheLinePadding {
    //遍历十亿次
    public static long COUNT =10_0000_0000L;

    private static class T {
        private long p1, p2, p3, p4, p5, p6, p7;
        public long x;
        private long p11, p12, p13, p14, p15, p16, p17;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });
        long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }
}
