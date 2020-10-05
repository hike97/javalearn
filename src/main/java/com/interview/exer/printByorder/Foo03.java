package com.interview.exer.printByorder;

import com.interview.utils.PoolUtils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Author hike97
 * @Description
 * @create 2020-09-27 10:00
 * @Modified By:
 **/
public class Foo03 {
	//声明两个 Semaphore变量
	private Semaphore spa, spb;

	public Foo03 () {
		//初始化Semaphore为0的原因：如果这个Semaphore为零，如果另一线程调用(acquire)这个Semaphore就会产生阻塞，便可以控制second和third线程的执行
		spa = new Semaphore (0);
		spb = new Semaphore (0);
	}

	public void first () throws InterruptedException {
		// printFirst.run() outputs "first". Do not change or remove this line.
//		printFirst.run ();
		//只有等first线程释放Semaphore后使Semaphore值为1,另外一个线程才可以调用（acquire）
		System.out.println ("first");
		spa.release ();
	}

	public void second () throws InterruptedException {
		//只有spa为1才能执行acquire，如果为0就会产生阻塞
		spa.acquire ();
		// printSecond.run() outputs "second". Do not change or remove this line.
//		printSecond.run ();
		System.out.println ("second");
		spb.release ();
	}

	public void third () throws InterruptedException {
		//只有spb为1才能通过，如果为0就会阻塞
		spb.acquire ();
		// printThird.run() outputs "third". Do not change or remove this line.
//		printThird.run ();
		System.out.println ("third");
	}

	public static void main (String[] args) {
		Foo03 foo03 = new Foo03 ();
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






