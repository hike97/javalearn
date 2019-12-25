package com.algorithm;

import static com.algorithm.SortUtils.print;

/**
 * @Author hike97
 * @Description 插入排序
 * @create 2019-12-25 16:02
 * @Modified By:
 **/
public class InsectionSort {
	public static void main (String[] args) {
		int[] a = {9,3,1,4,6,8,7,5,2};
//		sort (a);
		sort_optimize (a);
		print(a);
	}
	static void sort(int[] a ) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j >0 ; j--) {
				//右边小于左边 两个数相交换
				if (a[j]<a[j-1] ) SortUtils.swap (a,j,j-1);
			}
		}
	}

	/**
	 * 插入排序优化 将 swap 操作 改为 tmp坐标模式
	 * @param a
	 */
	static void sort_optimize(int[] a) {
		int j=0,temp = 0;
		//从数组第二个数开始插入（数组第一个数左边没有数）
		for (int i = 1; i < a.length; i++) {
            temp = a[i];//将待插入数据取出
			j=i-1;//排好序数组的最后一个（用来和待插入数据比较的位置）
			while (j>=0&&temp<a[j]){
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = temp;//将最后一个元素后一位添加为tmp对比值
		}
	}
}
