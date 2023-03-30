package mianshi.interview.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author hike97
 * @Description
 * @create 2020-09-23 19:46
 * @Modified By:
 **/
public class ReadWriteLockDemo {
	public static void main (String[] args) {
		Cache cache = new Cache ();
		for (int i = 0; i < 5; i++) {
			int finalI = i;
			new Thread (() -> cache.write (finalI + "", finalI), "t" + i).start ();
		}

		for (int i = 0; i < 5; i++) {
			int finalI = i;
			new Thread (() -> cache.read (finalI + ""), "t" + i).start ();
		}


	}
}

class Cache {
	private volatile Map<String, Object> map = new HashMap<> ();
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock ();

	public void write (String key, Object value) {
		lock.writeLock ().lock ();
		try {
			System.out.println (Thread.currentThread ().getName () + " :write start");
			try {
				TimeUnit.MILLISECONDS.sleep (300);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
			map.put (key, value);
			System.out.println (Thread.currentThread ().getName () + " :write finish");
		} catch (Exception e) {

			e.printStackTrace ();

		} finally {
			lock.writeLock ().unlock ();
		}

	}

	public Object read (String key) {
		lock.readLock ().lock ();
		try {
			System.out.println (Thread.currentThread ().getName () + " :read start");
			try {
				TimeUnit.MILLISECONDS.sleep (300);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
			Object o = map.get (key);
			System.out.println (Thread.currentThread ().getName () + " :read finish" + " result: " + o);
			return o;
		} catch (Exception e) {

			e.printStackTrace ();

		} finally {
			lock.readLock ().unlock ();
		}
		return null;
	}
}
