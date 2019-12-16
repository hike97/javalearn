package com.thread_pool;

import java.util.concurrent.*;

/**
 * @Author hike97
 * @Description  6种线程池-fixedThreadPool
 *               固定线程池 - 之 submit方法 带返回值future
 * @create 2019-12-16 14:32
 * @Modified By:
 **/
public class T03_MyThreadPool {
	public static void main (String[] args) throws InterruptedException, ExecutionException {
		//futureTask 包装callable 方法
		//FutureTask<V> implements RunnableFuture<V>
		//interface RunnableFuture<V> extends Runnable, Future<V>
		FutureTask<Integer> task = new FutureTask<> (() -> {
			TimeUnit.MILLISECONDS.sleep (500);
			return 1000;
		});
		new Thread (task).start ();
		System.out.println (task.get ());//阻塞

		//*******************************放到线程池中执行，线程池中自动new FutureTask
		ExecutorService service = Executors.newFixedThreadPool (5);//execute submit
		Future<Integer> future = service.submit (() ->
		{
			try {
				TimeUnit.MILLISECONDS.sleep (500);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
			return 1;
		});
		System.out.println (future.get ());//get 阻塞
		System.out.println (future.isDone ());


	}
}
