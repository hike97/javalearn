package com.interview.thread;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hike97 2month
 * @create 2020-09-20 21:52
 * @desc 指令重排demo
 **/
public class ReSortSeqDemo {

	int a = 0;
	boolean flag = false;

	public void method01() {
		a=1;
		flag=true;
	}

	public void method02() {
		if (flag){
			a= a+5;
			System.out.println ("returnValue:" + a);
		}
	}
	//多线程环境中线程交替执行，由于编译器优化重拍的存在，两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测
	public static void main (String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor (1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<> (20000), Executors.defaultThreadFactory (), new ThreadPoolExecutor.AbortPolicy ());
		ThreadPoolExecutor poolExecutor2 = new ThreadPoolExecutor (1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<> (20000), Executors.defaultThreadFactory (), new ThreadPoolExecutor.AbortPolicy ());
		ReSortSeqDemo demo = new ReSortSeqDemo ();
		for (int i = 0; i < 20000; i++) {
			poolExecutor.execute (()->demo.method01 ());
			poolExecutor2.execute (()->demo.method02 ());
		}
		poolExecutor.shutdown ();


	}
}
