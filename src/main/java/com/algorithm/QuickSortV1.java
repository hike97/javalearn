package com.algorithm;


import java.util.Arrays;

/**
 * @Author hike97
 * @Description 快速排序 1次
 * @create 2020-01-10 14:57
 * @Modified By:
 **/
public class QuickSortV1 {

	public static void main (String[] args) {
		int arr[] = {7,3,2,10,8,1,9,5,4,6};
		sort (arr,0,arr.length-1);
		print (arr);
	}

	public static void sort(int arr[],int leftBound,int rightBound) {
		//如果左边边界大于右边边界 直接返回
		if (leftBound>=rightBound) return;
		partition(arr,leftBound,rightBound);
	}
	static void partition(int arr[],int leftBound,int rightBound){
		//中轴为右边的值
		int pivot = arr[rightBound];
		//左指针和右指针
		int left = leftBound;
		int right = rightBound -1;
        while (left<right){
        	//左边的值只要小于等于pivot 指针一直++
        	while (left<=right&&arr[left]<=pivot)left++; //此处left必须等于right 需要将left的位置后移一位
        	while (left<=right&&arr[right]>pivot) right--;//此处应该是>pivot 为了防止相同的数字被排到了右边
			System.out.println ("before swap: left->" + left + "right->" + right);
			if (left<right) swap (arr,left,right);
			System.out.println (Arrays.toString (arr));
		}
        //将轴与右边界第一个数交换位置
        swap (arr,left,rightBound);
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
