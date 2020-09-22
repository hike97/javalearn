package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author hike97
 * @Description sychronized 和 reentrantLock 的区别
 * @create 2020-09-14 19:03
 * @Modified By:
 **/
public class SyncAndRentrantLockDemo {
	/**
	 * AA 打印5次 BB打印10次 CC打印15次 *10
	 */
	public static void main (String[] args) {
		ShareResource resource = new ShareResource ();
		for (int i = 0; i < 10; i++) {
			new Thread (()->resource.print5AA (),"ThreadA").start ();
			new Thread (()->resource.print10BB (),"ThreadB").start ();
			new Thread (()->resource.print15CC (),"ThreadC").start ();
		}
	}
}
class ShareResource{
	private String word = "A";
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print5AA(){
		lock.lock ();
		try {
			//判断
			while (!word.equals ("A")){
				c1.await ();
			}
			//干活
			for (int i = 0; i < 5; i++) {
				System.out.println (Thread.currentThread ().getName ()+": AA");
			}
			//通知
			word = "B";
			c2.signal ();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}
	}

	public void print10BB(){
		lock.lock ();
			try {
				while (!word.equals ("B")) {
					c2.await ();
				}
				for (int i = 0; i < 10; i++) {
					System.out.println (Thread.currentThread ().getName ()+": BB");
				}
				word="C";
				c3.signal ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}finally {
				lock.unlock ();
			}

	}

	public void print15CC(){
		lock.lock ();
		try {
			while (!word.equals ("C")) {
				c3.await ();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println (Thread.currentThread ().getName ()+": CC");
			}
			word="A";
			c1.signal ();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}finally {
			lock.unlock ();
		}

	}

}
