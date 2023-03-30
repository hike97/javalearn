package com.java19learn.lambda;

import com.java19learn.entity.Apple;
import com.java19learn.entity.Fruit;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 方法的引用
 * @create 2019-04-26 10:05
 * @Modified By:
 **/
public class Lambdalearn02_methodindex {
	public static void main (String[] args) {
		Function<String,Integer> stringToInteger =
				(String s) ->Integer.parseInt (s);
		//可简写为
		Function<String,Integer> stringToInteger02 = Integer::parseInt;
//		List<String> list = Arrays.asList("a","b","A","B");
		BiPredicate<List<String>,String> contains = (list,element)-> list.contains (element);
		BiPredicate<List<String>,String> contains02 = List::contains;
		//1.构造函数的引用
		Supplier<Apple> supplier1 = ()->new Apple ();
		Supplier<Apple> supplier = Apple::new;
		Apple apple = supplier.get ();
			//1.1构造函数1个带参引用
		Function<Integer,Apple> f2 = (weight)-> new Apple (weight);
		Apple apple2 = f2.apply (112);

		Function<Integer,Apple> f1 = Apple::new;
		Apple apple1 = f1.apply (110);
			//1.2 构造函数两个参数引用
		BiFunction<String,Integer,Apple> f3 = (color,weight) -> new Apple (color,weight);
		f3.apply ("black",108);

		BiFunction<String,Integer,Apple> f4 = Apple::new;
		f4.apply ("what",213);

		/**
		 * 一个由 Integer 构成的 List 中的每个元素都通过我们前面定义的类似的map方法
		 * 传递给了 Apple 的构造函数，得到了一个具有不同重量苹果的 List
		 */
		List<Integer> weights = Arrays.asList (7, 3, 4, 10);
		List<Apple> appleList = map (weights, Apple::new);

	}
	static Map<String,Function<Integer, Fruit>> map = new HashMap<> ();
	static {
		map.put ("entity",Apple::new);
	}

	public static List<Apple> map(List<Integer> list,Function<Integer,Apple> function) {
		List<Apple> result = new ArrayList<> ();
		for (Integer integer : list) {
			result.add (function.apply (integer));
		}
		return result;
	}
	/**
	 * 创建给水果方法
	 */
	public static Fruit giveMeFruit(String fruit,Integer weight){
		return map.get (fruit.toLowerCase ()).apply (weight);
	}
}
