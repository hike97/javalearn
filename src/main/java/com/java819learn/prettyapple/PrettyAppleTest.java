package com.java819learn.prettyapple;

import com.java819learn.apple.Apple;
import com.java819learn.apple.AppleTest;

import java.util.List;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 17:04
 * @Modified By:
 **/
public class PrettyAppleTest {
	/*
	    编写一个 prettyPrintApple 方法，它接受一个 Apple 的 List ，并可以对它参数化，以
	多种方式根据苹果生成一个 String 输出（有点儿像多个可定制的 toString 方法）。例如，你
	可 以 告 诉 prettyPrintApple 方 法 ， 只 打 印 每 个 苹 果 的 重 量 。 此 外 ， 你 可 以 让
	prettyPrintApple 方法分别打印每个苹果，然后说明它是重的还是轻的。
   */
	public static void prettyPrintApple(List<Apple> inventory,
										AppleFormatter formatter){
		for(Apple apple: inventory){
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}

	public static void main (String[] args) {
		prettyPrintApple (AppleTest.apples,new AppleFacyFormatter ());
	}
}
