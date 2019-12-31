package com.algorithm;

/**
 * @Author hike97
 * @Description 递归例子
 * @create 2019-12-30 16:57
 * @Modified By:
 **/
public class RecursionDemo {
	public static void main (String[] args) {
		System.out.println (f (10));
		System.out.println (fn (3));
	}

	/**
	 * 计算1到10的和
	 * @param n
	 * @return
	 */
	static long f(int n) {
		if (n<1)  return -1;
		if (n==1) return 1;
		return n+f (n-1);
	}

	/**
	 * 计算 n的阶乘
	 * @param n
	 * @return
	 */
	static long fn(int n){
		if (n<1)  return -1;
		if (n==1) return 1;
		return n*fn (n-1);
	}
}
