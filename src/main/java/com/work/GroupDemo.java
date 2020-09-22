package com.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
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
				new Dish("pork", false, 800, Dish.Type.MEAT,0),
				new Dish("beef", false, 700, Dish.Type.MEAT,0),
				new Dish("chicken", false, 400, Dish.Type.MEAT,0),
				new Dish("french fries", true, 530, Dish.Type.OTHER,1),
				new Dish("rice", true, 350, Dish.Type.OTHER,1),
				new Dish("season fruit", true, 120, Dish.Type.OTHER,1),
				new Dish("pizza", true, 550, Dish.Type.OTHER,1),
				new Dish("prawns", false, 300, Dish.Type.FISH,0),
				new Dish("salmon", false, 450, Dish.Type.FISH,0) );
//		System.out.println ("before change :" + menu);
//		List<Dish> collect = menu.stream ().filter (m -> 300 < m.getCalories ()).sorted (Comparator.comparing (Dish::getCalories)).collect (toList ());
//		collect.get (0).setCalories (99999);
//		menu = collect;
//		System.out.println ("after change :"+menu);
//		List<Dish> test = menu.stream ().sorted (Comparator.comparing (Dish::getValue, (x, y) -> {
//			if (x == 0&&y!=0) return -1;
//			if (x != 0&&y==0) return  1;
//			return 0;
//		}).reversed ()).collect (toList ());
//		test.forEach (System.out::println);

		double v = Double.parseDouble ("7708.69566");
		boolean flag = v==0;
		System.out.println (flag);
		if (v==0.00){
			System.out.println ("test");
		}
	}

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Dish {
	private String name;
	private boolean vegetarian;
	private int calories;
	private Type type;
	private int value;
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public enum Type { MEAT, FISH, OTHER }
}
