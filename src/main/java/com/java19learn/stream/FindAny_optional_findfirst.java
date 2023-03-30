package com.java19learn.stream;

import com.java19learn.entity.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description findAny和findFirst使用 以及optional的理解
 * @create 2019-04-28 16:31
 * @Modified By:
 **/
public class FindAny_optional_findfirst {

	List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH) );

	/**
	 * findAny的用法
	 */
	@Test
	public void test_findAny () {

		//Optional 简介
		/**
		 *   isPresent() 将在 Optional 包含值的时候返回 true , 否则返回 false 。
		 *   ifPresent(Consumer<T> block) 会在值存在的时候执行给定的代码块。我们在第3章
		 * 介绍了 Consumer 函数式接口；它让你传递一个接收 T 类型参数，并返回 void 的Lambda表达式。
		 *   T get() 会在值存在时返回值，否则抛出一个 NoSuchElement 异常。
		 *   T orElse(T other) 会在值存在时返回值，否则返回一个默认值。
		 */
				menu.stream()
						.filter(Dish::isVegetarian)
						.findAny()
						.ifPresent (d->System.out.println (d.getName ()));
	}
	/*
	 *  何时使用 findFirst 和 findAny
		你可能会想，为什么会同时有 findFirst 和 findAny 呢？答案是并行。找到第一个元素
		在并行上限制更多。如果你不关心返回的元素是哪个，请使用 findAny ，因为它在使用并行流
		时限制较少。
	 */
	@Test
	public void test_finFirst () {
		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> firstSquareDivisibleByThree =
				someNumbers.stream()
						.map(x -> x * x)
						.filter(x -> x % 3 == 0)
						.findFirst(); // 9
	}

}
