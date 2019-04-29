package com.java8_19learn.lambda;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description Execute Around 环绕执行模式转labmda
 * @create 2019-04-25 10:01
 * @Modified By:
 **/
public class Lambdalearn01_usagestep {

	public static String processFile() throws Exception {
		//JDK7以前如果rd.readLine()与rd.close()(在finally块中）都抛出异常则只会抛出finally块中的异常，不会抛出rd.readLine()；中的异常。这样经常会导致得到的异常信息不是调用程序想要得到的。
		//JDK7及以后版本中如果采用try-with-resource机制，如果在try-with-resource声明中抛出异常（可能是文件无法打或都文件无法关闭）同时rd.readLine()；也抛出异常，则只会抛出rd.readLine()的异常。
		try (BufferedReader br = new BufferedReader (new FileReader ("dpcp.txt"))) {
			return br.readLine ();
		}
	}
	//第一步：行为参数化：将processFile的行为参数化
	/*
	  String result = processFile((BufferedReader br) ->br.readLine() + br.readLine());
	 */
	//第二步：使用函数式接口传递行为 ：BufferedReader -> String
	@FunctionalInterface
	public interface BufferedReaderProcessor {
		String process(BufferedReader b) throws IOException;
	}
	//第三步：执行一个行为
	public static String processFile(BufferedReaderProcessor p) throws Exception {
		//解决fileReader获取相对路径的问题
		String root = System.getProperty("user.dir");
		String FileName="1.txt";
		String filePath = root+ File.separator+FileName;
		FileReader fr = new FileReader(filePath);

		try (BufferedReader br = new BufferedReader (new FileReader(filePath))) {
			return p.process (br);
		}
	}
	//第四步：传递Lambda
	public static void main (String[] args) {
		try {
			String oneLine = processFile ((BufferedReader br) -> br.readLine ());
			String twoLines = processFile ((BufferedReader br) -> br.readLine ()+br.readLine ());
			System.out.println ("读一行："+oneLine);
			System.out.println ("读二行："+twoLines);
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	/**
	 * 三大常用的函数式接口
	 */
	//1.Predicate
	//行为参数化 --  执行一个行为 判断一个list的元素是否为空 不是就添加到结果集
	//函数式编程接口 predict
	public static <T>List<T> filter(List<T> list,Predicate<T> p){
		List<T> results = new ArrayList<> ();
		for (T t : list) {
			if (p.test (t)){
				results.add (t);
			}
		}
		return results;
	}
	@Test
	public void test_predicate () {
		List<String> listOfString = Arrays.asList ("", "a", "b", "c");
		//传递lambda表达式
		Predicate<String> stringFilter = (String s) -> !s.isEmpty ();
		List<String> nonEmpty = filter (listOfString, stringFilter);
		System.out.println (nonEmpty);//[a, b, c]

	}
	//2.Consumer accept方法 无返回值
	//java.util.function.Consumer<T> 定义了一个名叫 accept 的抽象方法，
	// 它接受泛型 T的对象，没有返回（ void ）。
	// 你如果需要访问类型 T 的对象，并对其执行某些操作，就可以使用这个接口。
	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T i : list) {
			c.accept (i);
		}
	}
	@Test
	public void test_consumer () {
		forEach (Arrays.asList (1,2,3,4,5),(Integer i) -> System.out.println (i));
	}
	//3.Function apply
	//java.util.function.Function<T, R> 接口定义了一个叫作 apply 的方法，它接受一个
	//泛型 T 的对象，并返回一个泛型 R 的对象。如果你需要定义一个Lambda，将输入对象的信息映射
	//到输出，就可以使用这个接口（比如提取苹果的重量，或把字符串映射为它的长度）。
	public static <T, R> List<R> map(List<T> list,
									 Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for(T s: list){
			result.add(f.apply(s));
		}
		return result;
	}

	@Test
	public void test_function () {
		// [7, 2, 6]
		List<Integer> l = map(
				Arrays.asList("lambdas","in","action"),
				(String s) -> s.length()
		);
		System.out.println (l);
	}
	/*在Java里有一个将原始类型转换为对应
	的引用类型的机制。这个机制叫作装箱（boxing）。相反的操作，也就是将引用类型转换为对应
	的原始类型，叫作拆箱（unboxing）。Java还有一个自动装箱机制来帮助程序员执行这一任务：装
	箱和拆箱操作是自动完成的。*/

   //Lambda表达式只能捕获指派给它们的局部变量一次。
}
