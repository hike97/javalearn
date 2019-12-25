package com.algorithm;

/**
 * @Author hike97
 * @Description 排序静态方法工具类
 * @create 2019-12-25 14:28
 * @Modified By:
 **/
public class SortUtils {
	//打印数组方法
	static void print(int [] arr ) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print (arr[i] + " ");
		}
	}
	//交换
	static void swap(int[]arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
