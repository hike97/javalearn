package com.java8_19learn.stream;

import com.java8_19learn.entity.Dish;
import com.java8_19learn.entity.SortedDish;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 流的使用
 * @create 2019-04-27 15:50
 * @Modified By:
 **/
public class Stream_usage {
	List<Dish> menu = Arrays.asList (
			new Dish ("pork", false, 800, Dish.Type.MEAT),
			new Dish ("beef", false, 700, Dish.Type.MEAT),
			new Dish ("chicken", false, 400, Dish.Type.MEAT),
			new Dish ("french fries", true, 530, Dish.Type.OTHER),
			new Dish ("rice", true, 350, Dish.Type.OTHER),
			new Dish ("season fruit", true, 120, Dish.Type.OTHER),
			new Dish ("pizza", true, 550, Dish.Type.OTHER),
			new Dish ("prawns", false, 300, Dish.Type.FISH),
			new Dish ("salmon", false, 450, Dish.Type.FISH));

	/**
	 * 一、筛选和切片
	 * 用谓词（返回一个boolean的函数）筛选
	 */
	@Test
	public void test_1 () {
		List<Dish> vegetarianMenu = menu.stream ()
				.filter (Dish::isVegetarian)
				.collect (toList ());
	}

	/**
	 * 筛选出列表中所有的偶数，并确保没有重复
	 */
	@Test
	public void test_2 () {
		List<Integer> numbers = Arrays.asList (1, 2, 1, 3, 3, 2, 4);
		numbers.stream ().filter (i -> i % 2 == 0).distinct ().forEach (System.out::println);

	}

	/**
	 * 利用流来筛选前两个荤菜
	 */
	@Test
	public void test_3 () {
		List<Dish> collect = menu.stream ().filter (d -> d.getType () == Dish.Type.MEAT).limit (2).collect (toList ());
		System.out.println (collect);
	}

	/**
	 * 如果你要找出每道菜的名称有多长，怎么做？你可以像下
	 * 面这样，再链接上一个 map ：
	 */
	@Test
	public void test_4 () {
		List<Integer> dishNameLengths = menu.stream ()
				.map (Dish::getName)
				.map (String::length)
				.collect (toList ());
		System.out.println (dishNameLengths);
	}

	/**
	 * 对于一张单词 表 ，如 何 返 回 一 张 列 表 ，列 出 里 面 各 不 相 同 的 字 符 呢 ？
	 * 例 如 ， 给 定 单 词 列 表
	 * ["Hello","World"] ，你想要返回列表 ["H","e","l", "o","W","r","d"] 。
	 */
	List<String> words = Arrays.asList ("Hello", "World");

	@Test
	public void test_alphaTest () {


		List<String[]> collect = words.stream ().map (word -> word.split (""))
				.distinct ().collect (toList ());
		System.out.println (collect);

	}

	//分析：传递给 map 方法的Lambda为每个单词返回了一个 String[] （ String列表）。
	// 因此， map 返回的流实际上是 Stream<String[]> 类型的。你真正想要的是用
	//Stream<String> 来表示一个字符流。
	@Test
	public void test_arrayOfWords () {
		String[] arrayOfWords = {"Hello", "World"};
		List<Stream<String>> collect = words.stream ()
				.map (word -> word.split (""))
				.map (Arrays::stream)
				.distinct ()
				.collect (toList ());
		System.out.println (collect);
	}
	//解决不了：只是吧单词转换为一个数组，然后把把每个数组换成一个独立的流

	/**
	 * flatMap 方法的效果是，
	 * 各个数组并不是分别映射成一个流，而是映射成流的内容
	 */
	@Test
	public void test_flatMap () {
		List<String> uniqueCharacters =
				words.stream ()
						.map (w -> w.split (""))
						.flatMap (Arrays::stream)
						.distinct ()
						.collect (Collectors.toList ());
		System.out.println (uniqueCharacters);
	}

	/**
	 * 需求一：给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,
	 * 5]，应该返回[1, 4, 9, 16, 25]
	 */
	@Test
	public void test_numberSquares () {
		List<Integer> numbers = Arrays.asList (1, 2, 3, 4, 5);
		List<Integer> squares =
				numbers.stream ()
						.map (n -> n * n)
						.collect (toList ());

	}

	/**
	 * 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，
	 * 应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
	 * 为简单起见，你可以用有两个元素的数组来代表数对。
	 */
	@Test
	public void test_faltMap () {
		List<Integer> number1 = Arrays.asList (1, 2, 3);
		List<Integer> number2 = Arrays.asList (3, 4);
		List<int[]> collect = number1.stream ()
				.flatMap (i -> number2.stream ()
						.map (j -> new int[]{i, j})).collect (toList ());
		//		collect.stream ().forEach (System.out::println);
		//		for (int[] ints : collect) {
		//			System.out.println (Arrays.toString (ints));
		//		}
		/**
		 * 再添加一个限制条件：两个数相加能够被3整除
		 */
		List<int[]> collect1 = number1.stream ().flatMap (i -> number2.stream ().filter (j -> (i + j) % 3 == 0).map (j -> new int[]{i, j})).collect (toList ());
		collect1.forEach (ints -> {
			System.out.println (Arrays.toString (ints));
		});
	}

	@Test //groupingby的应用
	public void test_ () {
		Map<Dish.Type, List<Dish>> dishesByType = menu.stream ().collect (groupingBy (Dish::getType));
		System.out.println (dishesByType);
		//带条件返回
		Map<CaloricLevel, List<Dish>> caloricLevelListMap = menu.stream ().collect (groupingBy (dish -> {
			if (dish.getCalories () <= 400) return CaloricLevel.DIET;
			else if (dish.getCalories () <= 700) return CaloricLevel.NORMAL;
			else return CaloricLevel.FAT;
		}));
		System.out.println (caloricLevelListMap);
	}
	//多级分组
	@Test
	public void test_mutiLevel () {
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream ().collect (
				groupingBy (Dish::getType,
						groupingBy (dish -> {
							if (dish.getCalories () <= 400) return CaloricLevel.DIET;
							else if (dish.getCalories () <= 700) return CaloricLevel.NORMAL;
							else return CaloricLevel.FAT;
						})
				)
		);
		System.out.println (dishesByTypeCaloricLevel);
	}

	@Test
	public void test_groupingbyCounting () {
		Map<Dish.Type, Long> typesCount = menu.stream().collect(
				groupingBy(Dish::getType, counting()));
		System.out.println (typesCount);
	}

	@Test
	public void test_分组之后然后取第一个 () {
		Map<Dish.Type, Dish> collect = menu.stream ().collect (groupingBy (Dish::getType, collectingAndThen (
				toList (), v -> v.get (0))
		));
		System.out.println (collect);
	}
	//把收集器的结果转换为另一种类型
	@Test
	public void test_collectingAndThen () {
		Map<Dish.Type, Dish> mostCaloricByType =
				menu.stream()
						.collect(groupingBy(Dish::getType,
								collectingAndThen(
										maxBy(comparingInt(Dish::getCalories)),
										Optional::get)));
		System.out.println (mostCaloricByType);
	}

	@Test
	public void test_hashset () {
		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
				menu.stream().collect(
						groupingBy(Dish::getType, mapping(
								dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
								else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
								else return CaloricLevel.FAT; },
								toCollection(HashSet::new))));

		System.out.println (caloricLevelsByType);
	}
	//partitioningBy的用法 分区
	@Test
	public void test_partition () {
		Map<Boolean, List<Dish>> partitionedMenu =
				menu.stream().collect(partitioningBy(Dish::isVegetarian));

		//partition
		Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
				menu.stream().collect(
						partitioningBy(Dish::isVegetarian,
								groupingBy(Dish::getType)));
		Map<Dish.Type, List<Dish>> map = vegetarianDishesByType.get (true);
		System.out.println (map);
		//
		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
				menu.stream().collect(
						partitioningBy(Dish::isVegetarian,
								collectingAndThen(
										maxBy(comparingInt(Dish::getCalories)),
										Optional::get)));
	}

		//列出1~100之间的质数
		@Test
		public void test_isPrime () {
			Map<Boolean, List<Integer>> map = partitionPrimes (100);
			System.out.println (map);
		}

		public boolean isPrime(int candidate) {
		 return IntStream.range (2,candidate)
				 .noneMatch (i->candidate%i==0);
		}

		public boolean isPrimeBySqrt(int candidate) {
			int candidateRoot = (int) Math.sqrt((double) candidate);
			return IntStream.rangeClosed(2, candidateRoot)
					.noneMatch(i -> candidate % i == 0);
		}

		public Map<Boolean, List<Integer>> partitionPrimes(int n) {
			return IntStream.rangeClosed(2, n).boxed()
					.collect(
							partitioningBy(candidate -> isPrime(candidate)));
		}






















	public enum CaloricLevel {
		DIET, NORMAL, FAT
	}
}
