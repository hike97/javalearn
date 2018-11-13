package com.atguigu.java_collection;


import java.util.HashMap;

/**
 * @author hike97
 * @create 2018-11-12 11:20
 * @desc 自定义HashSet
 **/
public class SxtHashSet {

	HashMap map;
	int size;
	// Dummy value to associate with an Object in the backing Map
	private static final Object PRESENT = new Object();

	public int size(){
		return map.size ();
	}
	public SxtHashSet(){
		map = new HashMap ();
	}

	public void add(Object o){
		map.put (o,PRESENT);//set的不可重复就是利用了map里面对象的不可重复
	}

	public static void main (String[] args) {
		SxtHashSet s = new SxtHashSet ();
		s.add ("aaa");
		s.add (new String ("aaa"));
		System.out.println (s.size ());
	}
}
