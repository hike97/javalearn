package com.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author hike97
 * @Description  6种线程池-fixedThreadPool 固定线程池
 * @create 2019-12-16 14:32
 * @Modified By:
 **/
public class T02_MyThreadPool {
	public static void main (String[] args) throws InterruptedException {
		/*new ThreadPoolExecutor (
		 nThreads,//最大线程
		 nThreads,//最小线程
		 0L,TimeUnit.MILLISECONDS,//多少时间后线程销毁 数字+单位 这里是0毫秒 说明不销毁
		 new LinkedBlockingQueue<Runnable>() //这里用的linkedBlockingQueue 来进行线程之间的执行
		);*/
		ExecutorService service = Executors.newFixedThreadPool (5);//execute submit
		for (int i = 0; i < 6; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		/**
		 * [
		 * Running,//          线程池状态
		 * pool size = 5,      线程池大小
		 * active threads = 5, 活跃的线程池
		 * queued tasks = 1,   阻塞的任务
		 * completed tasks = 0 完成的任务
		 * ]
		 */
		System.out.println(service);

		service.shutdown();
		System.out.println(service.isTerminated());//所有任务是不是执行完
		System.out.println(service.isShutdown());//是不是关闭（关闭中）了？
		/**
		 * [Shutting down,
		 * pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
		 */
		System.out.println(service);

		TimeUnit.SECONDS.sleep(5);//5秒后
		/**
		 * [Terminated,
		 * pool size = 0, active threads = 0, queued tasks = 0,
		 * completed tasks = 6]
		 */
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
