package com.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author hike97
 * @Description 线程池的实际应用：并行计算
 * @create 2019-12-16 16:02
 * @Modified By:
 **/
public class T04_MyThreadPool_FixedThreadPool_impl {
	public static void main (String[] args) throws ExecutionException, InterruptedException {
		/**
		 * 不用线程池计算
		 */
		long start = System.currentTimeMillis();
		List<Integer> results = getPrime(1, 200000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		/**
		 * 运用线程池
		 */
		final int cpuCoreNum = 4;
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
		//按质数的稀疏分 825
		MyTask t1 = new MyTask (1, 80000);
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);

		//平均分 1237
//		MyTask t1 = new MyTask (1, 50000);
//		MyTask t2 = new MyTask(50001, 100000);
//		MyTask t3 = new MyTask(100001, 150000);
//		MyTask t4 = new MyTask(150001, 200000);

		Future<List<Integer>> f1 = service.submit (t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);

		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	/**
	 * 计算 一个区间中的list
	 */
	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;

		MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}

		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}

	}

	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

	static List<Integer> getPrime(int start, int end) {
		List<Integer> results = new ArrayList<> ();
		for(int i=start; i<=end; i++) {
			if(isPrime(i)) results.add(i);
		}

		return results;
	}
}
