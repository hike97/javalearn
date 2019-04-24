package com.java819learn.apple;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;


/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 筛苹果
 * @create 2019-04-24 14:55
 * @Modified By:
 **/
public class AppleTest {

	public static List<Apple> apples = Arrays.asList (new Apple ("red", 100),
				new Apple ("green", 120),
				new Apple ("blue", 158));

	/**
	 * 根据颜色筛选
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<> ();
		for (Apple apple : inventory) {
			if ("green".equals (apple.getColor ()))
				result.add (apple);
		}
		return result;
	}

	@Test
	public void test_01 () {
		List<Apple> appleList = filterGreenApples (apples);
		System.out.println (appleList);
	}

	/**
	 * 根据重量筛选
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterHeavyApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<> ();
		for (Apple apple : inventory) {
			if (apple.getWeight () > 150)
				result.add (apple);
		}
		return result;
	}

	@Test
	public void test_02 () {
		List<Apple> appleList02 = filterHeavyApples (apples);
		System.out.println (appleList02);
	}
	/**
	 * java8 实现
	 * 用功能性函数 Predicate<T>接口</>
	 */
	public static boolean isGreen(Apple a){
		return  "green".equals (a.getColor ());
	}
	public static boolean isHeavy(Apple a){
		return  a.getWeight ()>150;
	}

	static List<Apple> filterApples(List<Apple> inventory , Predicate<Apple> p){
		List<Apple> result = new ArrayList<> ();
		for (Apple apple : inventory) {
			if (p.test (apple))
				result.add (apple);
		}
		return result;
	}
	/*
	    什么是谓词？
		前面的代码传递了方法 Apple::isGreenApple （它接受参数 Apple 并返回一个
		boolean ）给 filterApples ，后者则希望接受一个 Predicate<Apple> 参数。词 谓词（predicate）
		在数学上常常用来代表一个类似函数的东西，它接受一个参数值，并返回 true 或 false 。你
		在后面会看到，Java 8也会允许你写 Function<Apple,Boolean> ——在学校学过函数却没学
		过谓词的读者对此可能更熟悉，但用 Predicate<Apple> 是更标准的方式，效率也会更高一
		点儿，这避免了把 boolean 封装在 Boolean 里面。
	 */
	@Test
	public void test_03() {
		List<Apple> appleList = filterApples (apples, AppleTest::isGreen);
		System.out.println (appleList);
		appleList = filterApples (apples, AppleTest::isHeavy);
		System.out.println (appleList);
	}

	/**
	 * lambda 表达式处理
	 */
	@Test
	public void test_04 () {
		List<Apple> appleList = filterApples (apples, (Apple a) -> "red".equals (a.getColor ()));
		System.out.println (appleList);
		//java8 已经有filter方法
		List<Apple> collect = apples.stream ().filter (a -> a.getWeight () > 150).collect (toList ());
		System.out.println (collect);
		List<Apple> collect1 = apples.parallelStream ().filter (a -> a.getWeight () > 150).collect (toList ());
		System.out.println (collect1);
	}
}
