package com.algorithm;

/**
 * @Author hike97
 * @Description 快速排序 完整版带递归操作
 * @create 2020-01-10 14:57
 * @Modified By:
 **/
public class QuickSortV2 {

	public static void main (String[] args) {
		int arr[] = {7,3,2,10,8,1,9,5,4,6};
		sort (arr,0,arr.length-1);
		print (arr);
	}

	public static void sort(int arr[],int leftBound,int rightBound) {
		//如果左边边界大于右边边界 直接返回
		if (leftBound>=rightBound) return;
		int mid = partition (arr, leftBound, rightBound);
		sort (arr,leftBound,mid-1);
		sort (arr,mid+1,rightBound);

	}
	static int partition(int arr[],int leftBound,int rightBound){
		int left = 0;//左指针
		int right = rightBound-1;//右指针
		int pivot = arr[rightBound];//确定中轴（此时以右边界为轴）
		while (left<=right){
			while (left<=right&&arr[left]<=pivot)left++;
			while (left<=right&&arr[right]>pivot)right--;
			//将两个元素交换
			if (left<right)swap (arr,left,right);
		}
		//转换轴和right第一个数的位置
		swap (arr,left,rightBound);
		return left;

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
