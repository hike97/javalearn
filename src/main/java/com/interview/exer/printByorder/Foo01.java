package com.interview.exer.printByorder;

import com.interview.utils.PoolUtils;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Author hike97
 * @Description notify wait 实现顺序输出
 * @create 2020-09-27 10:00
 * @Modified By:
 **/
public class Foo01 {
	private int flag;

	public Foo01 () {
	}

	public void first () throws InterruptedException {
		synchronized (Foo01.class) {
			System.out.println ("first");
			flag = 2;
			Foo01.class.notifyAll ();
		}
	}

	public void second () throws InterruptedException {
		synchronized (Foo01.class) {
			while (flag != 2) {
				Foo01.class.wait ();
			}
			System.out.println ("second");
			flag = 3;
			Foo01.class.notifyAll ();
		}

	}

	public void third () throws InterruptedException {

		synchronized (Foo01.class) {
			while (flag != 3) {
				Foo01.class.wait ();
			}
			System.out.println ("third");
		}
	}

	public static void main (String[] args) {
		Foo01 foo03 = new Foo01 ();
		ThreadPoolExecutor pool = PoolUtils.getPool (3, 3, null);
		pool.execute (() -> {
			try {
				foo03.second ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		});
		pool.execute (() -> {
			try {
				foo03.first ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		});
		pool.execute (() -> {
			try {
				foo03.third ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		});

		pool.shutdown ();

	}
}






