package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-12-15 18:06
 * @desc 线程池
 **/
public class Threadpool {
	public static void main (String[] args) {
		//可以用execute 和 submit两个方法
		ExecutorService service = Executors.newFixedThreadPool (5);
		//线程池中放了六个任务，每个任务睡眠500毫秒并且打印当前线程名称
		for (int i = 0; i < 6; i++) {
			service.execute (()->{
				try {
					TimeUnit.MILLISECONDS.sleep (500);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
				System.out.println (Thread.currentThread ().getName ());
			});
		}

		System.out.println (service);

		service.shutdown ();//等所有线程完成任务后才会关闭
		System.out.println (service.isTerminated ());
		System.out.println (service.isShutdown ());
		System.out.println (service);

		try {
			TimeUnit.SECONDS.sleep (5);
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}

		System.out.println (service.isTerminated ());
		System.out.println (service.isShutdown ());
		System.out.println (service);
	}
}
