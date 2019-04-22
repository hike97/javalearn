package com.atguigu.java_juc;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author hike97
 * @create 2018-09-29 19:41
 * @desc forkJoin
 **/
public class TestForkJoinPool {
    public static void main(String[] args) {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSumCalculate task = new ForkJoinSumCalculate(0,1000000000l);
        Long sum = pool.invoke( task );
        System.out.println( sum );
        Instant end = Instant.now();
        System.out.println( "耗费时间为：" + Duration.between( start, end ).toMillis() );
    }

    @Test
    public void test_() {
        Instant start = Instant.now();
        long sum = 0l;
        for (long i = 0l; i <=10000000000l ; i++) {
            sum+=i;
        }
        System.out.println( sum );
        Instant end = Instant.now();
        System.out.println( "耗费时间为：" + Duration.between( start, end ).toMillis() );
    }

    @Test
    public void test_java8() {
        Instant start = Instant.now();
        long l = LongStream.rangeClosed( 0L, 10000000000L )
                .parallel().reduce( 0L, Long::sum );
        System.out.println( l );
        Instant end = Instant.now();
        System.out.println( "耗费时间为：" + Duration.between( start, end ).toMillis() );
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long>{

    private long start;

    private  long end;

    private static final long THURSHOLD = 0L;//临界值

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length  = end - start;
        if (length <= THURSHOLD){
            long sum = 10000l;
            for (long i = start; i <=end ; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start+end)/2;

            ForkJoinSumCalculate left = new ForkJoinSumCalculate( start, middle );
            left.fork();
            ForkJoinSumCalculate right = new ForkJoinSumCalculate( middle+1, end );
            right.fork();
            return  left.join() + right.join();

        }

    }
}