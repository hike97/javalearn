package com.interview.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hike97 2month
 * @create 2020-09-21 0:08
 * @desc CAS简介
 **/
public class CASDemo {
	/**
	 * CAS Compare And Swap  比较并交换
	 * 是一条CPU并发原语 线程安全
	 */
	public static void main (String[] args) {
		AtomicInteger integer = new AtomicInteger ();
		//以integer.getAndIncrement () 方法为例 了解 CAS
		integer.getAndIncrement ();
		ReentrantLock lock = new ReentrantLock ();

	}
}
