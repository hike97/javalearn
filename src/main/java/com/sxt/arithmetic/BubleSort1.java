package com.sxt.arithmetic;

import java.util.Arrays;

/**
 * @author hike97
 * @create 2018-11-13 9:52
 * @desc 冒泡排序01
 **/
public class BubleSort1 {

	public static void main (String[] args) {
		int arr[] = {9,8,7,6,5};
		int i= 0;
		sort(arr);
	}

	private static void sort (int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			System.out.println ("<------第" + (i + 1) + "趟------>");
			for (int j = 0; j < arr.length-1-i; j++) {
				System.out.println ("第" + (j + 1) + "次");
				if (arr[j]>arr[j+1]){
					int temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1] = temp;
				}
				System.out.println (Arrays.toString (arr));
			}
		}
	}
}
