package mianshi.newcode.concurrent.c_010_aqs.reentrantlock;


import mianshi.interview.utils.PoolUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author hike97
 * @Description 集齐七颗龙珠 召唤神龙
 * @create 2020-09-24 10:38
 * @desc: cyclicBarrier 底层实现是reentrantlock
 **/
public class CyclicBarrierDemo {
	public static void main (String[] args) {
		CyclicBarrier barrier = new CyclicBarrier (7, () -> System.out.println ("召唤神龙！！！！"));
		ThreadPoolExecutor poolExecutor = PoolUtils.getPool (7, 7, "%d");
		for (int i = 0; i < 7; i++) {
			poolExecutor.execute (()->{
				System.out.println (Thread.currentThread ().getName ()+"收集到一颗龙珠~");
				try {
					barrier.await ();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace ();
				}
			});
		}
		poolExecutor.shutdown ();
	}
}
