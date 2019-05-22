package com.java8_19learn.stream;

import com.java8_19learn.entity.Dish;
import com.java8_19learn.entity.GlobalData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-22 15:53
 * @Modified By:
 **/
public class Collectors_learn {
	public static void main (String[] args) {
		List<Dish> menu = GlobalData.menu;
		Stream<Dish> menuStream = menu.stream ();
		/**Collectors类的静态工厂方法**/

		//1.tolist 把流中所有项目收集到一个 List
		List<Dish> dishes = menuStream.collect(toList());
		//2.toSet  Set<T>  把流中所有项目收集到一个 Set ，删除重复项
		Set<Dish> dishes1 = menuStream.collect(toSet());
		//3.toCollection  Collection<T>  把流中所有项目收集到给定的供应源创建的集合
		Collection<Dish> dishes2 = menuStream.collect(toCollection(ArrayList::new));
		//4.counting  Long  计算流中元素的个数
		 long howManyDishes = menuStream.collect(counting());
	}

}
