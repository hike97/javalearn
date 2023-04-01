package mianshi.neo_concurrent.c_001_ordering;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName T01_DisOrder 有序性
 * @Description 正常执行 不会出现x 或者 y 一个是 0 一个是 1
 * @Author hike97
 * @Date 2023/3/23 13:19
 * @Version 1.0
 **/
public class T01_DisOrder {
    private static int x = 0 ,y =0;
    private static int a = 0 ,b =0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; true; i++) {
            x=0;y=0;a=0;b=0;
            CountDownLatch latch = new CountDownLatch(2);

            Thread one = new Thread(() -> {
                a = 1;
                x = b;
                latch.countDown();
            });

            Thread other = new Thread(() -> {
                b = 1;
                y = a;
                latch.countDown();
            });

            one.start();
            other.start();
            latch.await();
            if (x == 0 && y == 0){
                System.out.printf("第%s次:(%s,%s)%n", i, x, y);
                break;
            }
        }
    }
    //x=b y=a a=1 b=1
    //y=a x=b b=1 a=1 导致预期结果 说明cpu会指令重排
}
