package com.work;

import lombok.Data;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.collectingAndThen;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-06-20 10:00
 * @Modified By:
 **/
public class GroupDemo {

	static List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH) );

	public static void main (String[] args) {
//		Map<Dish.Type, HashSet<String>> collect = menu.stream ().collect (
//				groupingBy (Dish::getType, mapping (
//						dish -> {
//							if (dish.getCalories () <= 400) return "group1";
//							else if (dish.getCalories () <= 700) return "group2";
//							else return "group3";
//						},
//						toCollection (HashSet::new))));
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
		System.out.println ("before change :" + menu);
		List<Dish> collect = menu.stream ().filter (m -> 300 < m.getCalories ()).sorted (Comparator.comparing (Dish::getCalories)).collect (toList ());
		collect.get (0).setCalories (99999);
		menu = collect;
		System.out.println ("after change :"+menu);
	}

}
@Data
class Dish {
	private String name;
	private boolean vegetarian;
	private int calories;
	private Type type;
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public enum Type { MEAT, FISH, OTHER }
}
