package com.algorithm;

import static com.algorithm.SortUtils.print;

/**
 * @Author hike97
 * @Description 希尔排序(改进的插入排序)
 * @create 2019-12-31 16:58
 * @Modified By:
 **/
public class ShellSort {
	/**
	 * 希尔的时间复杂度O(n1.3)
	 */
	public static void main (String[] args) {
        int []arr = {9,6,11,3,5,12,8,7,10,15,14,4,1,13,2};
//		sortV1 (arr);
//		sortV2 (arr);
        print(arr);
	}

	/**
	 * 普通间隔为2的
	 * @param arr
	 */
	static void sortV1(int [] arr) {
		for (int gap = arr.length/2;gap>0;gap/=2) { //每次间隔除以2
			for (int i = gap; i < arr.length; i++) {
				for (int j = i; j > gap - 1; j -= gap) {
					//右边小于左边 两个数相交换
					if (arr[j] < arr[j - gap]) SortUtils.swap (arr, j, j - gap);
				}
			}
		}
	}

	/**
	 * 用Knuth序列 Donald
	 * h=1
	 * h=3*h+1
	 */
    static void sortV2(int arr[]) {
    	int h = 1;
    	//h 不能超过 数组的三分之一
    	while (h<= arr.length/3){
    		h = h*3+1;
		}

		for (int gap = h; gap >0 ; gap = (gap-1)/3) {
			for (int i = gap; i < arr.length; i++) {
				for (int j = i; j > gap - 1; j -= gap) {
					//右边小于左边 两个数相交换
					if (arr[j] < arr[j - gap]) SortUtils.swap (arr, j, j - gap);
				}
			}
		}
	}


}
