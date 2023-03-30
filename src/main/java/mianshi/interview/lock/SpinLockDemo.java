package mianshi.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author hike97
 * @Description 重写一个自旋锁
 * @create 2020-09-23 16:35
 * @Modified By:
 * 采用循环的方式去尝试获取锁
 **/
public class SpinLockDemo {
	AtomicReference<Thread> atomicReference = new AtomicReference<> ();

	public void lock(){
		System.out.println (Thread.currentThread ().getName ()+"hold lock");
		do {
//			System.out.println (Thread.currentThread ().getName () + "wait");
		}while (!atomicReference.compareAndSet (null,Thread.currentThread ()));
	}

	public void unlock(){
		System.out.println (Thread.currentThread ().getName ()+"un lock");
		atomicReference.compareAndSet (Thread.currentThread (),null);
	}
	public static void main (String[] args) {
		SpinLockDemo demo = new SpinLockDemo ();
		new Thread (()->{
			demo.lock ();
			//睡五秒 持有 当前锁
			try {
				TimeUnit.SECONDS.sleep (5);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
			demo.unlock ();
		},"AA").start ();

		try {
			TimeUnit.SECONDS.sleep (1);
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}

		new Thread (()->{
			demo.lock ();
			//睡五秒 持有 当前锁
			demo.unlock ();
		},"BB").start ();

	}
}
