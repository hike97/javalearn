package com.atguigu.java_collection;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author hike97
 * @create 2018-11-12 10:28
 * @desc
 **/
public class TestEquals {

	public static void main (String[] args) {
		ArrayList<String> list = new ArrayList<> ();
		String s1 = new String ("aaa");
		String s2 = new String ("aaa");
		list.add (s1);
		list.add (s2);
		System.out.println (list.size ());

		HashMap<Object, Object> map = new HashMap<> ();
		map.put (s1,"AAA");
		map.put (s2,"BBBB");
		System.out.println (map.get ("aaa"));
		//判断map中的键 是基于equals的方法
		Integer s3 = new Integer ("123");
		Integer s4 = new Integer ("123");

		map.put (s3,"AAA");
		map.put (s4,"BBBB");
		System.out.println (map.get (123));
	}
}
