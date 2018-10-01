package com.atguigu.java_juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author hike97
 * @create 2018-09-29 19:25
 * @desc 线程调度
 **/
public class TestScheduledThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool( 5 );
        Future<Integer> result = null;
        for (int i =0;i<10;i++) {
            result = pool.schedule( () ->
            {
                int num = new Random().nextInt( 100 );//生成随机数
                System.out.println( Thread.currentThread().getName() + ":" + num );
                return num;
            }, 1, TimeUnit.SECONDS );
            System.out.println( result.get() );
        }
        pool.shutdown();

    }
}
