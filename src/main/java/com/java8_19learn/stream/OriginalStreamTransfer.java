package com.java8_19learn.stream;

import com.java8_19learn.entity.Dish;
import com.java8_19learn.entity.GlobalData;
import org.junit.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 原始类型流特化
 * @create 2019-04-30 16:17
 * @Modified By:
 **/
public class OriginalStreamTransfer {
	/**
	 *  mapToInt 会从每道菜中提取热量（用一个 Integer 表示），并返回一个 IntStream
	 * （而不是一个 Stream<Integer> ）
	 */
	@Test
	public void test_1() {

		int sum = GlobalData.menu.stream ().mapToInt (Dish::getCalories)
				.sum ();
		System.out.println (sum);
		//原始流装箱
		Stream<Integer> boxed = GlobalData.menu.stream ().mapToInt (Dish::getCalories).boxed ();
		//求最大值  求和错误后默认返回0 求最大值返回optional 进行操作
		OptionalInt max = GlobalData.menu.stream ().mapToInt (Dish::getCalories).max ();
		int i = max.orElse (1);
		//不存在就返回1

	}
	/**
	 * 数值范围 rangeClosed 和 range
	 * rangeClosed 是开区间
	 * range 闭区间
	 */
	@Test
	public void test_2 () {
		long count = IntStream.rangeClosed (1, 100).filter (n -> n % 2 == 0).count ();
		long count_ = IntStream.range (1, 100).filter (n -> n % 2 == 0).count ();
		System.out.println ("rangeClosed闭区间计算1到100偶数个数："+count);
		System.out.println ("range开区间计算1到100偶数个数："+count_);
	}
}
