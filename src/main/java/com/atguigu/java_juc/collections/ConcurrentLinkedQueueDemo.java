package com.atguigu.java_juc.collections;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author hike97
 * @Description
 * @create 2019-12-10 19:47
 * @Modified By:
 **/
public class ConcurrentLinkedQueueDemo {
	static Queue<String> tickets = new ConcurrentLinkedQueue();
	static {
		for (int i = 0; i < 1000; i++) {
			tickets.add ("票 编号："+i);
		}
	}

	/**
	 * poll 方法 如果没有拿到会返回null 不存在线程安全问题
	 * @param args
	 */
	public static void main (String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread (()->{
				while (true) {
					String s = tickets.poll ();
					if (s == null)break;
					else System.out.println ("saled--" + s);
				}
			}).start ();
		}
	}
}
