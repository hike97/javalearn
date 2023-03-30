package mianshi.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author hike97
 * @Description 可重入锁
 * @create 2020-09-23 10:44
 * @Modified By:
 * 可重入锁又叫递归锁
 * 指的是 线程可以进入任何一个他已经拥有的锁 所同步的代码块
 * sychronized 和 reentrantLock 都是可重入锁
 * <p>
 * t1 :sendSMS()
 * t1 :#############sendEmail()
 * t2 :sendSMS()
 * t2 :#############sendEmail()
 **/
public class ReentrantLockDemo {
	public static void main (String[] args) throws InterruptedException {
		Phone phone = new Phone ();
		new Thread (() -> {
			try {
				phone.sendSMS ();
			} catch (Exception e) {
				e.printStackTrace ();
			}
		}, "t1").start ();
		new Thread (() -> {
			try {
				phone.sendSMS ();
			} catch (Exception e) {
				e.printStackTrace ();
			}
		}, "t2").start ();

		System.out.println ();
		System.out.println ();
		System.out.println ();
		System.out.println ();
		TimeUnit.SECONDS.sleep (1);
		new Thread (()->phone.get (),"t3").start ();
		new Thread (()->phone.get (),"t4").start ();
	}
}

class Phone implements Runnable {
	public synchronized void sendSMS () throws Exception {
		System.out.println (Thread.currentThread ().getName () + " :sendSMS()");
		sendEmail ();
	}

	public synchronized void sendEmail () throws Exception {
		System.out.println (Thread.currentThread ().getName () + " :#############sendEmail()");
	}

	Lock lock = new ReentrantLock ();

	@Override
	public void run () {
		get ();
	}

	public void get () {
		lock.lock ();
		try {
			System.out.println (Thread.currentThread ().getName () + " :get ()");
			set ();
		} catch (Exception e) {

			e.printStackTrace ();

		} finally {
			lock.unlock ();
		}
	}

	public void set () {
		lock.lock ();
		try {
			System.out.println (Thread.currentThread ().getName () + " :set()");
		} catch (Exception e) {

			e.printStackTrace ();

		} finally {
			lock.unlock ();
		}
	}


}
