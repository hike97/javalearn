package com.thread_pool;


import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Author hike97
 * @Description  简单了解ThreadPoolExecutor
 * @create 2019-12-17 15:17
 * @Modified By:
 **/
public class T08_ThreadPoolExecutor {

	/**
	 * ThreadPoolExecutor 伪类
	 * @param corePoolSize  线程池线程
	 * @param maximumPoolSize  最多线程
	 * @param keepAliveTime     多久后消失 0l 代表线程永久不会消失
	 * @param unit               消失单位
	 * @param workQueue          装任务的容器
	 */
	void ThreadPoolExecutor(int corePoolSize,
					   int maximumPoolSize,
					   long keepAliveTime,
					   TimeUnit unit,
					   BlockingQueue<Runnable> workQueue){}

	public static void main (String[] args) {
		 //链表式阻塞
		//return new ThreadPoolExecutor(nThreads, nThreads,
		//                                      0L, TimeUnit.MILLISECONDS,
		//                                      new LinkedBlockingQueue<Runnable>());
		Executors.newFixedThreadPool(5);
		/*return new ThreadPoolExecutor (0, Integer.MAX_VALUE,
				60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(),//队列长度为0 来一个任务就必须处理一下
				threadFactory);*/
		Executors.newCachedThreadPool ();
		/*return new Executors.FinalizableDelegatedExecutorService
				(new ThreadPoolExecutor(1, 1, //长度为1 最大值也是1
						0L, TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>()));*/
		Executors.newSingleThreadExecutor ();

		/*定时执行 用delayQueue
		super(corePoolSize, Integer.MAX_VALUE,
				DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
				new ScheduledThreadPoolExecutor.DelayedWorkQueue ());*/
		Executors.newScheduledThreadPool (5);
	}

}
