package com.java8_19learn.entity;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-26 16:10
 * @Modified By:
 **/
public class Letter {
	public static String addHeader(String text){
		return "From Raoul, Mario and Alan: " + text;
	}
	public static String addFooter(String text){
		return text + " Kind regards";
	}
	public static String checkSpelling(String text){
		return text.replaceAll("labda", "lambda");
	}
}
