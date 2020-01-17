package com.algorithm;


import java.util.Arrays;

import static com.algorithm.SortUtils.print;

/**
 * @Author hike97
 * @Description 归并排序 版本1 :先规定已经排好的两个数组 arr1 arr2
 * @create 2019-12-30 17:17
 * @Modified By:
 **/
public class MergeSortV1 {

	public static void main (String[] args) {
		int [] arr = {1,4,7,8,3,6,9};
        sort (arr);
	}

	static void sort(int [] arr) {
		merge(arr);
	}

	static void merge (int[] arr) {
		int midIndex = arr.length>>1;//中间位置 index = 3
		int[] temp = new int[arr.length];
		int leftIndex = 0;
		int rightIndex = midIndex+1;
		int k = 0;//k为临时数组的起始下标
        while (leftIndex<=midIndex &&rightIndex<arr.length){
        	/*
        	   细节1：<= 为了保证算法的稳定性
        	 */
        	temp[k++]=arr[leftIndex]<=arr[rightIndex]?arr[leftIndex++]:arr[rightIndex++];
		}
        //如果两个数组比较的部分遍历完，将某个剩余的数组复制下来；
		while (leftIndex<=midIndex) temp[k++] = arr[leftIndex++];
		while (rightIndex<arr.length) temp[k++]=arr[rightIndex++];
		print(temp);
	}
}
