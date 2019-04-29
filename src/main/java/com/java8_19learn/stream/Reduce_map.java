package com.java8_19learn.stream;

import com.atguigu.entity.Trader;
import com.atguigu.entity.Transaction;
import org.graalvm.compiler.phases.common.ConditionalEliminationPhase;
import org.junit.Test;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 归约
 * @create 2019-04-28 16:44
 * @Modified By:
 **/
public class Reduce_map {
	/**
	 * 概念：
	 * 查询需要将流中所有元素反复结合起来，得到一个值，比如一个 Integer 。
	 * 这样的查询可以被归类为归约操作（将流归约成一个值）。
	 * 用函数式编程语言的术语来说，这称为折叠（fold）
	 */
	//reduce
	@Test
	public void test_sumTest () {
		//一、元素求和
		List<Integer> numbers = Arrays.asList (1, 2, 3, 6);
		int sum = numbers.stream().reduce(0, (a, b) -> a + b);
		int product = numbers.stream().reduce(1, (a, b) -> a * b);
		numbers.stream ().reduce (0,Integer::sum);//方法引用
		//无初始值reduce 返回optional
		Optional<Integer> reduce = numbers.stream ().reduce ((a, b) -> a + b);
		//二、求最大值 最小值
		Optional<Integer> max = numbers.stream().reduce(Integer::max);
		Optional<Integer> min = numbers.stream().reduce(Integer::min);
		/*
		 *计算一个流中的元素个数
		 */
		numbers.stream ().map (n->1).reduce (Integer::sum);
		/*map 和 reduce 的连接通常称为 map-reduce 模式，因Google用它来进行网络搜索而出名，
		因为它很容易并行化*/

	}
	/**
	 * 需求：
	 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
	 * (2) 交易员都在哪些不同的城市工作过？
	 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
	 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
	 * (5) 有没有交易员是在米兰工作的？
	 * (6) 打印生活在剑桥的交易员的所有交易额。
	 * (7) 所有交易中，最高的交易额是多少？
	 * (8) 找到交易额最小的交易。
	 */

	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario","Milan");
	Trader alan = new Trader("Alan","Cambridge");
	Trader brian = new Trader("Brian","Cambridge");
	List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
	);

	@Test
	public void test_exerise () {
		List<Transaction> tr2011 = transactions.stream ().filter (t -> 2011 == t.getYear ())
				.sorted (comparing (Transaction::getValue))
				.collect (toList ());
		System.out.println (tr2011);
	}

//	代码清单5-2 交易员都在哪些不同的城市工作过
	List<String> cities =
			transactions.stream()
					.map(transaction -> transaction.getTrader().getCity())
					.distinct()
					.collect(toList());
	//传set去重
	Set<String> cities_ =
			transactions.stream()
					.map(transaction -> transaction.getTrader().getCity())
					.collect(toSet());
	//代码清单5-3 查找所有来自于剑桥的交易员，并按中文abc姓名排序
	List<Trader> traders =
			transactions.stream()
					.map(Transaction::getTrader)
					.filter(trader -> trader.getCity().equals("Cambridge"))
					.distinct()
					.sorted(comparing(Trader::getName, Collator.getInstance (Locale.CHINA)))
					.collect(toList());

	//代码清单5-4 返回所有交易员的姓名字符串，按字母顺序排序
	String traderStr =
			transactions.stream()
					.map(transaction -> transaction.getTrader().getName())
					.distinct()
					.sorted()
					.reduce("", (n1, n2) -> n1 + n2);
	//更高效的方法
	String traderStr_ =
			transactions.stream()
					.map(transaction -> transaction.getTrader().getName())
					.distinct()
					.sorted()
					.collect(joining());
	//代码清单5-5 有没有交易员是在米兰工作的
	boolean milanBased =
			transactions.stream()
					.anyMatch(transaction -> transaction.getTrader()
							.getCity()
							.equals("Milan"));
	//代码清单5-6 打印生活在剑桥的交易员的所有交易额
	@Test
	public void test_ () {
		transactions.stream()
				.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
				.map(Transaction::getValue)
				.forEach(System.out::println);
	}
	//最大交易额
	Optional<Integer> highestValue =
			transactions.stream()
					.map(Transaction::getValue)
					.reduce(Integer::max);
	Optional<Integer> smallestValue =
			transactions.stream()
					.map(Transaction::getValue)
					.reduce(Integer::min);

}
