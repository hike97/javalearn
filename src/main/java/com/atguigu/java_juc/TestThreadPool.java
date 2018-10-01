package com.atguigu.java_juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author hike97
 * @create 2018-09-29 16:27
 * @desc 线程池
 **/
public class TestThreadPool {
    //之前线程 创建-->用完之后立马销毁
    /*
        一、线程池：提供了一个线程队列，对垒中保存着所有等待状态的线程，
        避免了创建与销毁额外开销，提高了响应的速度。
        二、线程池的体系结构：
        java.util.concurrent.Executor:负责线程的使用与调度的根接口
            |--**ExecutorService 子接口：线程池的主要接口
                |--ThreadPoolExecutor 实现类。
                |--ScheduledExecutorService 子接口：负责线程的调度
                    |--ScheduledThreadPoolExecutor 实现类
                        extends ThreadPoolExecutor implements ScheduledExecutorService ：
        三、工具类：executors
            ExecutorService newFixedThreadPool(int nThreads):创建固定大小的线程池
            ExecutorService newCachedThreadPool() ：缓存线程池，线程池的数量不固定
            ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory)：创建单个线程池。线程池中只有一个线程

            ScheduledExecutorService newScheduledThreadPool(int corePoolSize) 固定大小的线程，可以延迟或定时的执行任务。
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool( 5 );
        ThreadPoolDemo task = new ThreadPoolDemo();
        List< Future<Integer>> list = new ArrayList<>();

        for (int j= 0;j<10;j++) {
            Future<Integer> future = pool.submit( () -> {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                return sum;
              });
            //System.out.println( "callable-->"+future.get() );
            list.add( future );
        }

        pool.shutdown();
        list.stream().forEach( f -> {
            try {
                System.out.println( f.get() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } );

//        //2.为线程池中的线程分配任务
//        for (int i = 0; i <10 ; i++) {
//            pool.submit( task );
//        }
//        //3.关闭线程池
//        pool.shutdown();//等待现有任务关闭
//        //pool.shutdownNow();//强制关闭
    }

}

class ThreadPoolDemo implements Runnable{
    private int i = 0;
    @Override
    public void run() {
        while (i<=100){
            System.out.println( Thread.currentThread().getName() + ":" + i++ );

        }
    }
}