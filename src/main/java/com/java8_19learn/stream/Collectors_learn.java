package com.java8_19learn.stream;

import com.java8_19learn.entity.Dish;
import com.java8_19learn.entity.GlobalData;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
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
		 //5.summingInt  Integer  对流中项目的一个整数属性求和
		 int totalCalories = menuStream.collect(summingInt(Dish::getCalories));
		 //6.averagingInt  Double  计算流中项目 Integer 属性的平均值
		double avgCalories = menuStream.collect(averagingInt(Dish::getCalories));
		//7.summarizingInt  IntSummaryStatistics 收集关于流中项目 Integer 属性的统计值，例如最大、最小、
		//总和与平均值
		IntSummaryStatistics summaryStatistics = menuStream.collect (summarizingInt (Dish::getCalories));
		summaryStatistics.getAverage ();
		summaryStatistics.getCount ();
		summaryStatistics.getMax ();
		summaryStatistics.getMin ();
		summaryStatistics.getSum ();
		//8.joining`  String  连接对流中每个项目调用 toString 方法所生成的字符串
		String shortMenu = menuStream.map(Dish::getName).collect(joining(", "));
		//9.maxBy  Optional<T> 一个包裹了流中按照给定比较器选出的最大元素的 Optional ，或如果流为空则为 Optional.empty()
		Optional<Dish> fattest = menuStream.collect(maxBy(comparingInt(Dish::getCalories)));
		//10.minBy
		Optional<Dish> lightest = menuStream.collect(minBy(comparingInt(Dish::getCalories)));
		//11.reducing  归约操作产生的类型
		//从一个作为累加器的初始值开始，利用 BinaryOperator 与流
		//中的元素逐个结合，从而将流归约为单个值
		int totalCalories_ =
				menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));
		//12.collectingAndThen 转换函数返回的类型  包裹另一个收集器，对其结果应用转换函数
		int howManyDishes_ =
				menuStream.collect(collectingAndThen(toList(), List::size));
		//13.groupingBy  Map<K, List<T>>
		//根据项目的一个属性的值对流中的项目作问组，并将属性值作
		//为结果 Map 的键
		Map<Dish.Type,List<Dish>> dishesByType =
				menuStream.collect(groupingBy(Dish::getType));
		//14.partitioningBy  Map<Boolean,List<T>> 根据对流中每个项目应用谓词的结果来对项目进行分区
		Map<Boolean,List<Dish>> vegetarianDishes =
				menuStream.collect(partitioningBy(Dish::isVegetarian));
	}

}
