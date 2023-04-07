package mianshi.neoconcurrent.c_004_cacheline;

/**
 * @author: hike97
 * @createTime: 2023/04/07 23:50
 * @description: 缓存行性能测试
 */
public class T03_NewCacheLinePadding {

    public static volatile long[] arr =  new long[16];

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000_0000L; i++) {
                arr[0] = i;
            }
        });
        //一个long 8个字节  第9个 和 第1个 不在同一个缓存行 就不会产生伪共享问题
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000_0000L; i++) {
                arr[8] = i;
            }
        });
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //1s =1000ms 1ms = 1000 微妙 1微妙 = 1000纳秒
        System.out.println("耗时："+ (System.nanoTime() - start)/10_0000+"ms");
    }
}
