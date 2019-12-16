package com.thread_pool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author hike97
 * @Description
 *       弹性线程池   CachedThreadPool
 *       单线程线程池 SingledThreadPool
 *       定时线程池   ScheduledThreadPool
 * @create 2019-12-16 17:19
 * @Modified By:
 **/
public class T05_MyThreadPool_CachedPool {
	public static void main (String[] args) throws InterruptedException {
		/*
		 * new ThreadPoolExecutor(
		 * 0, Integer.MAX_VALUE, 最小值0 最大值 int的最大值
		 * 60L, TimeUnit.SECONDS, 销毁时间超过60s
		 *new SynchronousQueue<Runnable>()); 任务队列为同步队列
		 */
		ExecutorService service = Executors.newCachedThreadPool ();
		System.out.println ("beforeMethod:"+service);
		//[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
		for (int i = 0; i < 2; i++) {
			service.execute (()->{
				try {
					TimeUnit.MILLISECONDS.sleep (500);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
				System.out.println (Thread.currentThread ().getName ());
			});
		}
		//[Running, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]
		System.out.println (service);
//		TimeUnit.SECONDS.sleep (65);
//		System.out.println ("65s passed ------------");
//		System.out.println (service);
//        service.shutdown ();

       //保证任务的前后顺序执行：使用单线程线程池
		/*
		new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>())
		 */
		ExecutorService service2 = Executors.newSingleThreadExecutor ();
		for (int i = 0; i < 5; i++) {
			final int j = i;
			service2.execute (()->
				System.out.println (j + " " + Thread.currentThread ().getName ())
			);

		}
		//带定时的线程池
        /*
        super(corePoolSize, Integer.MAX_VALUE,
              DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
              new DelayedWorkQueue());
         */
		ScheduledExecutorService service1 = Executors.newScheduledThreadPool (4);
		service1.scheduleAtFixedRate (()->{
			try {
				TimeUnit.MILLISECONDS.sleep (new Random ().nextInt (1000));
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		},0,500,TimeUnit.MILLISECONDS);
	}
}
