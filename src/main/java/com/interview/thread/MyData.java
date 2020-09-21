package com.interview.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hike97 2month
 * @create 2020-09-20 13:08
 * @desc
 **/
public class MyData {
	volatile int number = 0;
	public void  addT060(){
		this.number = 60;
	}

	public void addPlusPlus(){
		number ++;
		//底层汇编代码
		/**
		 * getfield
		 * iconst_1
		 * iadd
		 * putfield
		 */
	}
	AtomicInteger atomicInteger = new AtomicInteger();
	public void addMyAtomic() {
		atomicInteger.getAndIncrement ();
	}

	public static void main (String[] args) {
		MyData myData = new MyData ();
		for (int i = 0; i < 20; i++) {
			new Thread (()->{
				for (int j = 0; j < 1000; j++) {
					myData.addPlusPlus ();
					myData.addMyAtomic ();
				}
			},String.valueOf (i)).start ();
		}

		//当前只剩下主线程 和 gc线程时 退出循环
		while (Thread.activeCount ()>2){
			Thread.yield ();
		}

		System.out.println (myData.number);
		System.out.println (myData.atomicInteger);
	}
}
