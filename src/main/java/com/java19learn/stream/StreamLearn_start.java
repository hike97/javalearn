package com.java19learn.stream;

import com.java19learn.entity.Dish;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description stream 流的引入
 * @create 2019-04-26 19:37
 * @Modified By:
 **/
public class StreamLearn_start {

	List<Dish> menu = new ArrayList<> ();

	/**
	 * 需求：根据菜肴的卡路里排序并输出菜名
	 */
	@Test
	public void test_java7 () {
		List<Dish> lowCaloricDishes = new ArrayList<> ();


		for(Dish d: menu){
			if(d.getCalories() < 400){
				lowCaloricDishes.add(d);
			}
		}
		Collections.sort(lowCaloricDishes, new Comparator<Dish> () {
			public int compare(Dish d1, Dish d2){
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});
		List<String> lowCaloricDishesName = new ArrayList<>();
		for(Dish d: lowCaloricDishes){
			lowCaloricDishesName.add(d.getName());
		}
	}

	/**
	 *java8实现
	 */
	@Test
	public void test_java8 () {

		List<String> lowCaloricDishesName =
				menu.stream()
						.filter(d -> d.getCalories() < 400)
						.sorted(comparing(Dish::getCalories))
						.map(Dish::getName)
						.collect(toList());
		//并行流
		List<String> lowCaloricDishesNamepara =
				menu.parallelStream()
						.filter(d -> d.getCalories() < 400)
						.sorted(comparing(Dish::getCalories))
						.map(Dish::getName)
						.collect(toList());

	}

}
//后端开发（dev）环境更新： 涉及模块：生存金领取方式变更
//		负责人：卢海源
//		--1  生存金领取方式变更校验接口  	     /survival-transfer/check
//		--2  生存金领取方式变更选择被保人接口   /survival-transfer/select
//		--3  生存金领取方式变更提交校验接口     /survival-transfer/preCommit
//		--4  生存金领取方式变更提交接口         /survival-transfer/commit
