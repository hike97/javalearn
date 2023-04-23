package mianshi.newcode.concurrent.c_010_aqs;

import mianshi.interview.utils.PoolUtils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author hike97
 * @Description
 * @create 2020-09-24 11:28
 * @Modified By:
 **/
public class SemaphoreDemo {
	public static void main (String[] args) throws Exception {
		//模拟三个停车位
		Semaphore semaphore = new Semaphore (0);
		ThreadPoolExecutor pool = PoolUtils.getPool (6, 6, null);
		for (int i = 0; i < 6; i++) {
			pool.execute (()->{
				try {
					semaphore.acquire ();
					System.out.println (Thread.currentThread ().getName ()+" 抢到车位");
					TimeUnit.SECONDS.sleep (3);
					System.out.println (Thread.currentThread ().getName ()+" 停车3s钟离开");
				} catch (InterruptedException e) {
					e.printStackTrace ();
				} finally {
					semaphore.release ();
				}
			});
		}
		pool.shutdown ();
	}
}
