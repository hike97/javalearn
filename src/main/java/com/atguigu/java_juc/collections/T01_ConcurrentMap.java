package com.atguigu.java_juc.collections;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author hike97
 * @Description map和并发的map
 * @create 2019-12-11 14:02
 * @Modified By:
 **/
public class T01_ConcurrentMap {
	public static void main (String[] args) {
//		Map<String,String> map =new HashMap<> ();
		Map<String,String>  map = new TreeMap<> ();
//		Map<String, String> map = new Hashtable<> ();
//		Map<String, String> map = Collections.synchronizedMap (new HashMap<> ());3081
//		Map<String, String> map = new ConcurrentHashMap<> ();3159
//		Map<String,String>  map = new ConcurrentSkipListMap<> ();//3312
		Random random = new Random ();
		//生成一个ths线程数组
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch (ths.length);
		long start = System.currentTimeMillis ();
		for (int i = 0; i < ths.length; i++) {
			//每个线程里面循环10000次，放入map10000个值
			ths[i] = new Thread (() -> {
				for (int j = 0; j < 10000; j++) {
					map.put ("a" + UUID.randomUUID (), "a" + random.nextInt (100000));
				}
				latch.countDown ();
			});
		}
		Arrays.asList (ths).forEach (Thread::start);
		//CountDownLatch.await() 方法在倒计数为0之前会阻塞当前线程.与thread.join 相似
		System.out.println ("主线程阻塞,等待所有子线程执行完成");
		try {
			latch.await ();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}
		long end = System.currentTimeMillis ();
		System.out.println ("所有线程执行完成!map.size:" + map.size ());
		System.out.println ("耗时：" + (end - start));
	}
}
