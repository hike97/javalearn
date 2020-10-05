package com.interview.exer.printByorder;

import com.interview.utils.PoolUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Author hike97
 * @Description 用门闩实现 顺序打印
 * @create 2020-09-27 10:00
 * @Modified By:
 **/
public class Foo04 {
	private CountDownLatch latch1, latch2;

	public Foo04 () {
		latch1 = new CountDownLatch (1);
		latch2 = new CountDownLatch (1);
	}

	public void first () throws InterruptedException {
		System.out.println ("first");
		latch1.countDown ();
	}

	public void second () throws InterruptedException {
		latch1.await ();
		System.out.println ("second");
		latch2.countDown ();
	}

	public void third () throws InterruptedException {
		latch2.await ();
		System.out.println ("third");
	}

	public static void main (String[] args) {
		Foo04 foo03 = new Foo04 ();
		ThreadPoolExecutor pool = PoolUtils.getPool (3, 3, null);
		pool.execute (()-> {
			try {
				foo03.second ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		});pool.execute (()-> {
			try {
				foo03.first ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		});pool.execute (()-> {
			try {
				foo03.third ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		});

		pool.shutdown ();

	}
}






