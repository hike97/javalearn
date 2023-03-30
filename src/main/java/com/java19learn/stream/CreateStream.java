package com.java19learn.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-05-19 17:37
 * @desc 构建流
 **/
public class CreateStream {
	public static void main (String[] args) {
		//由值创建流
		Stream<String> stream = Stream.of ("java 8", "Lambda", "In", "Action");
		stream.map (String::toUpperCase).forEach (System.out::println);
		Stream.empty (); //空流
		//由数组创建流
		int[] numbers = {2,3,5,7,11,13};
		int sum = Arrays.stream (numbers).sum ();
		System.out.println (sum);
		//文件生成流

//		try(Stream<String> lines = Files.lines (Path.get ("data.txt"), Charset.defaultCharset ());) {
//			lines.flatMap (line->Arrays.stream (line.split (" "))).distinct ().count ();
//		} catch (IOException e) {
//			e.printStackTrace ();
//		}
	}
	//斐波那契数列 迭代生成
	@Test
	public void test_ () {
		//输出斐波那契数组
		Stream.iterate (new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit (20).forEach (
				t-> System.out.println("(" + t[0] + "," + t[1] +")")
		);
		//输出斐波那契数列数组头一位
		Stream.iterate (new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit (20).map (t->t[0])
				.forEach (System.out::println);
	}

	@Test
	public void test_generate () {
		Stream.generate(Math::random).limit(5).forEach (System.out::println);
	}
}
