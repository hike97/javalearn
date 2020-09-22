package com.algorithm.learn;

/**
 * @Author hike97
 * @Description 欧几里得算法的实现
 * @create 2020-03-05 17:51
 * @Modified By:
 **/
public class GCDalgorithmDemo {
	/**
	 * 欧几里得算法就是实现两个数最大公约数的算法
	 * 算法分析：gcd(a,b)=gcd(b,a mod b);
	 */

	public static void main (String[] args) {
		long l = gcd (24, 42);
		System.out.println (l);
	}

	public static long gcd(long a,long b) {
		if (b==0) return a;
		return gcd (b,a%b);
	}
}
