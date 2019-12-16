package com.thread_pool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-12-16 23:45
 * @desc 工作偷取线程
 **/
public class t06_MyThreadPool_WorkStealPool {
    //与之前的线程池不同 该线程池主动获取任务 ：底层实现为forkjoinPool
	public static void main (String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool ();
		//工作偷取线程默认创建和cpu核数相同的线程
		System.out.println ("本机cpu核心数："+Runtime.getRuntime ().availableProcessors ());
		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000)); //daemon 机灵线程又叫守护线程又叫后台进程
		service.execute(new R(2000));
		service.execute(new R(4000));
		service.execute(new R(5000));

		//由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
		System.in.read();
	}

	static class R implements Runnable {

		int time;

		R(int t) {
			this.time = t;
		}

		@Override
		public void run() {

			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(time  + " " + Thread.currentThread().getName());

		}

	}
}
