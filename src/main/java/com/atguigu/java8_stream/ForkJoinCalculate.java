package com.atguigu.java8_stream;

import java.util.concurrent.RecursiveTask;

/**
 * @author hike97
 * @create 2018-09-07 15:23
 * @desc 工作窃取模式
 * alt+shift+p 实现接口
 *  Recursive [rɪ'kɝsɪv] 递归
 **/
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;//开始
    private long end;//结束

    private static final  long THRESHOLD = 10000;//临界值

    public ForkJoinCalculate(long start,long end){
        this.start=start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD){
            long sum = 0;
            for (long i = start; i <= end ; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start + end)/2;
            ForkJoinCalculate left = new ForkJoinCalculate( start, middle );
            left.fork();
            ForkJoinCalculate right = new ForkJoinCalculate( middle + 1, end );
            right.fork();
            return  left.join() + right.join();
        }
    }
}
