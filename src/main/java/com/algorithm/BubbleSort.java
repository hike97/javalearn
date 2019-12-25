package com.algorithm;

import static com.algorithm.SortUtils.swap;

/**
 * @Author hike97
 * @Description 冒泡排序 :O(n2) O(1) 稳定
 * @create 2019-12-25 14:27
 * @Modified By:
 **/
public class BubbleSort {
	public static void main (String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		bubbleSort (arr);
		SortUtils.print (arr);
	}

	static void bubbleSort(int  arr[]){
		int count = 1;
		for (int i = arr.length-1; i >0; i--) {
			//分别取出arr[length-1-i]的最大值
			boolean didSwap = false;
			for (int j = 0; j < i; j++) {
				if (arr[j]>arr[j+1] ) {
					swap(arr,j,j+1);
				}
				didSwap =true;
			}
			if (didSwap==false) {
				return;
			}
//			System.out.println ("第" + count + "次循环");
//			count++;
//			print(arr);
//			System.out.println ();
		}
	}
}
