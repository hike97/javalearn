package com.algorithm;


import static com.algorithm.SortUtils.print;

/**
 * @Author hike97
 * @Description 归并排序 版本3：归并排序完整版
 * @create 2019-12-30 17:17
 * @Modified By:
 **/
public class MergeSortV3 {
	/**
	 * 归并排序算法复杂度分析
	 * 空间复杂度：O(n) 每次方法调用后就会销毁
	 * 时间复杂度：O(nlog2N) 忽略递归的因素
	 * java 和 python 对于对象的排序 都是归并排序
	 * Arrays.sort 双轴快速排序
	 * TimSort.sort 二分插入+归并排序
	 *  if (nRemaining < MIN_MERGE) {
	 *             int initRunLen = countRunAndMakeAscending(a, lo, hi, c);
	 *             binarySort(a, lo, hi, lo + initRunLen, c);
	 *             return;
	 *         }
	 * @param args
	 */
	public static void main (String[] args) {
		int [] arr = {1,4,7,8,3,6,9};
        sort (arr,0,arr.length-1);
        print (arr);
	}

	static void sort(int [] arr,int left,int right) {
		if (left==right) return;
		//分成两半
		int mid =  left+((right-left)>>1);
		//左边排序
		sort(arr,left,mid);
		//右边排序
		sort(arr,mid+1,right);

		merge(arr,left,mid+1,right);
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
		//将temp数组 放回arr
		for (int m = 0; m <temp.length ; m++) arr[leftPos + m] = temp[m];

	}
}
