package com.algorithm;


import static com.algorithm.SortUtils.print;

/**
 * @Author hike97
 * @Description 选择排序优化：关键点：当maxIndex 和 i 相同时 需要移位 否则会导致排好的最小值 与 最大值位置交换
 * @create 2019-12-20 16:00
 * @Modified By:
 **/
public class SelectionSort_optimize {
	public static void main (String[] args) {
		int[] arr = {1, 3, 2, 4, 5, 6, 9, 8, 7};
		sortV2 (arr);
		print(arr);

	}
	static int[] sortV1(int [] array){
		//如果数组长度为0 或 1  不需要排序 返回本身
		if (array.length==0||array.length==1) return array;
		int len =  array.length;
		int minPos,temp;
		for (int i = 0; i < len-1; i++) {
			minPos = i;
			for (int j = i+1; j < len; j++) {
				if (array[j]<array[minPos]){
					minPos=j;
				}
			}
			if (minPos!=i){
				temp = array[i];
				array[i]=array[minPos];
				array[minPos]=temp;
			}
		}
		return array;
	}
	static int[] sortV2(int[] array){
		//如果数组长度为0 或 1  不需要排序 返回本身
        if (array.length==0||array.length==1) return array;
        //同时计算最大值和最小值
		int len = array.length;
		int minPos ,maxPos,temp,count=0;
		for (int i = 0; i < len/2; i++) {
			++count;
			minPos = i;
			maxPos = i;
			for (int j = i+1; j <len-i ; j++) {
				if (array[minPos]>array[j]){
					minPos=j;
				}else if (array[maxPos]<array[j]){
					maxPos=j;
				}
			}
			if (i != minPos){
				temp = array[i];
				array[i]=array[minPos];
				array[minPos]=temp;
				if (i==maxPos){
					maxPos=minPos;
				}
			}
			if (len-1-i != maxPos) {
				temp = array[len-1-i];
				array[len-1-i] = array[maxPos];
				array[maxPos] = temp;
			}
			System.out.print("第" + count + "次循环，最小值坐标：" + minPos + " 最大值坐标：" + maxPos+" :");
			print (array);
			System.out.println ();
		}
		return array;
	}
}
