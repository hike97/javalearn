package mianshi.newconcurrent.c_004_cacheline;

import sun.misc.Contended;

/**
 * @author: hike97
 * @createTime: 2023/04/08 0:07
 * @description: 用jdk8 注解 实现缓存行独享 需要jvm配置参数 -XX:-RestrictContended
 */
public class T04_CacheLineWithContended {
    @Contended
    volatile long x;
    @Contended
    volatile long y;

    public static void main(String[] args) throws InterruptedException {
        T04_CacheLineWithContended t = new T04_CacheLineWithContended();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000_0000L; i++) {
                t.x = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000_0000L; i++) {
                t.y = i;
            }
        });
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("耗时："+ (System.nanoTime() - start)/10_0000+"ms");
    }
}
