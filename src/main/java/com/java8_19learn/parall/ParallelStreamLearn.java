package com.java8_19learn.parall;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 并行流
 * @create 2019-05-23 15:26
 * @Modified By:
 **/
public class ParallelStreamLearn {
	public static void main (String[] args) {
		//获取并行流默认数量
//		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
		int i = Runtime.getRuntime ().availableProcessors ();
		System.out.println (i);//4核

	}
}
