package mianshi.newconcurrent.c_004_cacheline;

/**
 * @author: hike97
 * @createTime: 2023/04/07 23:50
 * @description: 缓存行性能测试(在一个缓存行)
 */
public class T02_NewOneCacheLinePadding {

    public static volatile long[] arr =  new long[2];

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000_0000L; i++) {
                arr[0] = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000_0000L; i++) {
                arr[1] = i;
            }
        });
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("耗时："+ (System.nanoTime() - start)/10_0000+"s");
    }
}
