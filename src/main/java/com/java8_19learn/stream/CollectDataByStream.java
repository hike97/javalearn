package com.java8_19learn.stream;

import com.java8_19learn.entity.Dish;
import com.java8_19learn.entity.GlobalData;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-05-19 18:17
 * @desc 用流收集数据
 **/
public class CollectDataByStream {
	public static void main (String[] args) {
		List<Dish> menu = GlobalData.menu;
		//根据所含热量对菜肴进行比较
		//1创建一个比较器
		Comparator<Dish> dishCaloriesComparator =
				Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish =
				menu.stream()
						.collect(maxBy(dishCaloriesComparator));
		//summingInt 接收对象映射成int
		//averagingInt 平均值
		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		//字符串连接
		String shortMenu = menu.stream().map(Dish::getName).collect(joining());
		//joining
		String shortMenu_ = menu.stream().map(Dish::getName).collect(joining(", "));
	}
}
