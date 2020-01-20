package com.algorithm;

import java.util.Arrays;

/**
 * @Author hike97
 * @Description 计数排序（稳定版）
 * @create 2020-01-10 14:57
 * @Modified By:
 **/
public class CountSortV2 {
	/*
	 * 算法思想
	 * 	量大但是范围小
	 * 	  某大型企业数万员工年龄排序
	 * 	  如何快速得知高考名次
	 */
	public static void main (String[] args) {
		int arr[] = {2,4,3,7,1,1,0,0,5,6,9,8,5,7,4,0,9};
		int[] result = sort (arr);
		System.out.println (Arrays.toString (result));
	}

	public static int[] sort(int arr[]) {
		//用于装最后结果的数组
		int[] result = new int[arr.length];
		//用于计数的数组 因为范围是0~9 所以数组维度为10
		int[] count = new int[10];
		//遍历原数组 进行计数
		for (int i : arr) {
			count[i]++;
		}
		System.out.println (Arrays.toString (count));
		//进行数组还原
		for (int i = 0,j=0; i <count.length ; i++) {
			while (count[i]-->0)result[j++]=i;
		}
		return result;
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
