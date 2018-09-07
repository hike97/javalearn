package com.atguigu.java8_stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author hike97
 * @create 2018-09-07 15:58
 * @desc FokrJoin测试
 **/
public class TestForkJoin {
    /**
     * fork join  框架
     */
    @Test
    public void test_1() {
        //long start = System.currentTimeMillis();
        Instant now = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate( 0,1000000000L);
        Long sum = forkJoinPool.invoke( task );
        System.out.println( sum );
        Instant end = Instant.now();
        System.out.println( "耗费时间为："+Duration.between( now, end ).toMillis());
    }
    /**
     * for循环
     */
    @Test
    public void test_2() {
        Instant now = Instant.now();
        long sum = 0l;
        for (int i = 0; i <= 1000000000L; i++) {
            sum+=i;
        }
        System.out.println( sum );
        Instant end = Instant.now();
        System.out.println( "耗费时间为："+Duration.between( now, end ).toMillis());
    }
    /**
     * java8 并行流
     */
    @Test
    public void test_3() {
        Instant now = Instant.now();
        LongStream.rangeClosed( 0,10000000000L ).
                reduce( 0,Long::sum);
        Instant end = Instant.now();
        System.out.println( "单线程——耗费时间为："+Duration.between( now, end ).toMillis());

        now = Instant.now();
        LongStream.rangeClosed( 0,10000000000L ).parallel().
                reduce( 0,Long::sum);
        end = Instant.now();
        System.out.println( "并行流——耗费为："+Duration.between( now, end ).toMillis());
    }
}
