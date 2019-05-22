package com.java8_19learn.stream;

import com.java8_19learn.entity.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-27 15:01
 * @Modified By:
 **/
public class Streamlearn_defination {
	//流的概念：从支持数据处理操作的源生成的元素序列 集合讲的是数据，流讲的是计算
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
	 *第一个例子
	 */
	@Test
	public void test_eg1 () {
		List<String> threeHighCaloricDishNames =
				menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.limit(3)
				.collect(toList());
		System.out.println(threeHighCaloricDishNames);
	}
	/**
	 * 流和集合的区别
	 * 流：需求驱动，实施创造
	 * 集合：供应商驱动：先把仓库装满，在开始卖
	 * filter map limit 中间操作
	 * collect 终端操作
	 */
	//内部迭代分析
	@Test
	public void test_analize () {
		List<String> names =
				menu.stream()
						.filter(d -> {
							System.out.println("filtering： " + d.getName());
							return d.getCalories() > 300;
						})
						.map(d -> {
							System.out.println("mapping： " + d.getName());
							return d.getName();
						})
						.limit(3)
						.collect(toList());
		System.out.println(names);
	}
	/**
	 * 流的使用：
	 *   一个数据源（如集合）来执行一个查询；
	 *   一个中间操作链，形成一条流的流水线；
	 *   一个终端操作，执行流水线，并能生成结果。
	 */

}
