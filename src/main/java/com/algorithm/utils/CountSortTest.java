package com.algorithm.utils;

import com.algorithm.CountSortV1;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @Author hike97
 * @Description 计数排序算法测试类
 * @create 2020-01-20 16:29
 * @Modified By:
 **/
public class CountSortTest {
	int  [] generateRandomArray() {
		Random r = new Random ();
		int[] arr = new int[10000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt (10);
		}
		return arr;
	}

	@Test
	public void test_ () {
		int[] a = generateRandomArray ();
		int[] result = CountSortV1.sort (a);
		Arrays.sort (a);
		boolean same = true;
		for (int i = 0; i < a.length; i++) {
			if (result[i] != a[i]) same = false;
		}
		assertEquals(true,same);
	}
}
