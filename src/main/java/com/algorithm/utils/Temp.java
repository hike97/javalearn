package com.algorithm.utils;

/**
 * @Author hike97
 * @Description 快速排序
 * @create 2020-01-10 14:57
 * @Modified By:
 **/
public class Temp {

	public static void main (String[] args) {
		int arr[] = {7,3,2,8,1,9,5,4,6};
	}

	public static void sort(int arr[],int leftBound,int rightBound) {
		//如果左边边界大于右边边界 直接返回
		if (leftBound>=rightBound) return;
		partition(arr,leftBound,rightBound);
	}
	static void partition(int arr[],int leftBound,int rightBound){

	}

	static void swap(int arr[],int i,int j){
		int temp =arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void print(int [] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print (arr[i]+" ");
		}
	}
}
