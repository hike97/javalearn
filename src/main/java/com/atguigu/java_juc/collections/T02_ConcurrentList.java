package com.atguigu.java_juc.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author hike97
 * @Description list 和并发的list
 * @create 2019-12-12 15:50
 * @Modified By:
 **/
public class T02_ConcurrentList {
	public static void main (String[] args) {
		List<String> list
//				= new ArrayList<> ();
//		        =new Vector<> ();
		        = Collections.synchronizedList (new ArrayList<> ());
//		        =new CopyOnWriteArrayList<> ();//写少读多时使用
		Random random = new Random ();
		Thread[] ths = new Thread[100];
		for (int i = 0; i < ths.length; i++) {
			Runnable task = new Runnable () {
				@Override
				public void run () {
					for (int j = 0; j < 1000; j++) {
						list.add ("a" + random.nextInt (10000));
					}
				}
			};
			ths[i] =  new Thread(task);
		}
		runAndComputeTime (ths);
		System.out.println ("list.size:" + list.size ());
	}

	static void runAndComputeTime(Thread[] ths) {
		long start = System.currentTimeMillis ();
		Arrays.asList (ths).forEach (Thread::start);
		Arrays.asList (ths).forEach (thread -> {
			try {
				thread.join ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		});
		long end = System.currentTimeMillis ();
		System.out.println (end - start);
	}
}
