package mianshi.interview.exer.printByorder;

import mianshi.interview.utils.PoolUtils;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Author hike97
 * @Description
 * @create 2020-09-27 10:00
 * @Modified By:
 **/
public class Foo02 {
	private volatile int flag = 0;
	Lock lock = new ReentrantLock ();
	Condition condition0 = lock.newCondition ();
	Condition condition1 = lock.newCondition ();
	Condition condition2 = lock.newCondition ();

	public Foo02 () {
	}

	public void first () throws InterruptedException {
		lock.lock ();
		try {
			while (flag != 0){
				condition0.await ();
			}
			System.out.println ("first");
			flag = 2;
			condition1.signalAll ();
		} catch (Exception e) {

			e.printStackTrace ();

		} finally {
			lock.unlock ();
		}

	}

	public void second () throws InterruptedException {

		lock.lock ();
		try {
			while (flag != 2) {
				condition1.await ();
			}
			System.out.println ("second");
			flag = 3;
			condition2.signalAll ();
		} catch (Exception e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}


	}

	public void third () throws InterruptedException {

		lock.lock ();
		try {
			while (flag != 3) {
				condition2.await ();
			}
			System.out.println ("third");
			flag = 0;
			condition0.signalAll ();
		} catch (Exception e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}

	}

	public static void main (String[] args) throws InterruptedException {
		Foo02 foo03 = new Foo02 ();
		ThreadPoolExecutor pool = PoolUtils.getPool (500, 1000, null);
		int i = 1;
		while (i < 100) {
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
			i++;
		}
		pool.shutdown ();

	}
}






