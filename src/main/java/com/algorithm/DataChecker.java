package com.algorithm;

import java.util.Arrays;
import java.util.Random;

import static com.algorithm.BubbleSort.bubbleSort;
import static com.algorithm.SelectionSort_optimize.sortV1;
import static com.algorithm.SelectionSort_optimize.sortV2;

/**
 * @Author hike97
 * @Description 对数器 ：用于验证算法的正确性
 * @create 2019-12-24 16:09
 * @Modified By:
 **/
public class DataChecker {
	/**
	 * 随机生成一个10000大小的数组
	 * @return
	 */
	static int[] generateRandomArray(){
		Random random = new Random ();
		int[]  arr = new int[10000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt (10000);
		}
		return arr;
	}

	static void check() {
		int[] arr = generateRandomArray ();
		//arr的副本arr2
		int[] arr2 = new int[arr.length];
		System.arraycopy (arr,0,arr2,0,arr.length);
		Arrays.sort (arr);
//		sortV1(arr2);
//		sortV2(arr2);
//		bubbleSort(arr2);
//		InsectionSort.sort (arr2);
		MergeSortV3.sort (arr2,0,arr2.length-1);
		boolean same =  true;
		for (int i = 0; i < arr2.length; i++) {
			if (arr[i] != arr2[i]) same=false;
		}
		System.out.println (same == true ? "right" : "wrong");
	}

	public static void main (String[] args) {
		check ();
	}
}
