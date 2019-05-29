package com.java8_19learn.future_learn;

import java.util.concurrent.*;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description future的应用
 * @create 2019-05-28 15:11
 * @Modified By:
 **/
public class FutureApply {
	public static void main (String[] args) {
		//创建ExecutorService ，通过它你可以向线程池提交任务
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Double> future = executor.submit(new Callable<Double> () {
			//向 ExecutorService 提交一个Callable 对象
			public Double call() {
				return 2.0;
				//return doSomeLongComputation();
			}});
		//doSomethingElse();
		try {
			//获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
			Double result = future.get(1, TimeUnit.SECONDS);
		} catch (ExecutionException ee) {
			// 计算抛出一个异常
		} catch (InterruptedException ie) {
			// 当前线程在等待过程中被中断
		} catch (TimeoutException te) {
			// 在Future对象完成之前超过已过期
		}
	}
}
