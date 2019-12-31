package com.algorithm;


import static com.algorithm.SortUtils.print;

/**
 * @Author hike97
 * @Description 归并排序 版本2：做灵活排序 归并数组的一部分
 * @create 2019-12-30 17:17
 * @Modified By:
 **/
public class MergeSortV2 {

	public static void main (String[] args) {
		int [] arr = {1,4,7,8,3,6,9};
		/**
		 * 左边界：1 | index 0
		 * 右边界：3 | index 4
		 * 右界限：9 | index arr.length-1
		 */
//        sort (arr,0,4,arr.length-1);
		/**
		 * 测试一组中间数组 ｛4，7，8，3，6｝
		 * 左边界：4 | index 1
		 * 右边界：3 | index 4
		 * 右界限：6 | index 5
		 */
//        sort (arr,1,4,5);
		sort (arr);
	}

    static void sort(int arr[]){
		merge (arr,0,4, arr.length-1);
	}

	static void merge (int[] arr, int leftPos, int rightPos, int rightBound) {
		int midIndex = rightPos-1;//中间位置为右边界前一位
		int[] temp = new int[rightBound-leftPos+1];
		int leftIndex = leftPos;
		int rightIndex = rightPos;
		int k = 0;//k为临时数组的起始下标
        while (leftIndex<=midIndex &&rightIndex<=rightBound){
        	/*
        	   细节1：<= 为了保证算法的稳定性
        	 */
        	temp[k++]=arr[leftIndex]<=arr[rightIndex]?arr[leftIndex++]:arr[rightIndex++];
		}
        //如果两个数组比较的部分遍历完，将某个剩余的数组复制下来；
		while (leftIndex<=midIndex) temp[k++] = arr[leftIndex++];
		while (rightIndex<=rightBound) temp[k++]=arr[rightIndex++];
		print(temp);
	}
}
