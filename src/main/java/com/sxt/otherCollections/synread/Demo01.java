package com.sxt.otherCollections.synread;

import org.junit.Test;

import java.util.*;

/**
 * @author hike97
 * @create 2018-11-30 13:59
 * @desc 容器的同步控制
 **/
public class Demo01 {
	public static void main (String[] args) {
		ArrayList<String> list = new ArrayList<> ();
		list.add ("a");
		list.add ("v");
		list.add ("d");
		//list同步
		List<String> synchronizedList = Collections.synchronizedList (list);
		System.out.println ("线程安全的list制作完毕");
	}

	/**
	 * 只读设置
	 */
	@Test
	public void test_ () {
		Map<String, String> map = new HashMap<> ();
		map.put ("1","1");
		map.put ("2","2");

		//只读控制
		Map<String, String> map2 = Collections.unmodifiableMap (map);
//		map2.put ("s","s");
		System.out.println (map2.size ());
		//只有一个元素的容器
		List<String> list = Collections.singletonList (new String ());
		list.add ("1");
		list.add ("1");

	}
	/**
	 * emptyCollection的用法
	 * 防止 空指针
	 */
	@Test
	public void test_1 () {
		po(new HashSet<> ());
	}
	public static Set<String> po(Set<String> set){
		if (set==null){
			return Collections.emptySet ();
		}
		return set;
	}
}
