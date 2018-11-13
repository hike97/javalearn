package com.atguigu.java_collection;

import java.util.LinkedList;

/**
 * @author hike97
 * @create 2018-11-08 10:59
 * @desc 自定义Map升级版
 * 1.提高查询效率
 * 2.map底层实现 数组+链表
 **/
public class SxtMap002 {

	LinkedList[] arr = new LinkedList[990];//map的底层结构：数组+链表！
	int size;

	public void put(Object key,Object value){
		SxtEntry e = new SxtEntry (key, value);
		//对负数进行处理
		int hash = key.hashCode ();
		hash = hash<0?-hash:hash;
		int a = hash%arr.length;
		//a:1000-->1 b:10000-->13
		if (arr[a]==null){
			LinkedList list = new LinkedList ();
			arr[a] = list;
			list.add (e);

		}else {
			LinkedList<SxtEntry> list = arr[a];
			for (SxtEntry entry : list) {
				if (entry.key.equals (key)){
					entry.value = value;//键值重复 直接覆盖！
					return;
				}
			}
			arr[a].add (e);
		}
	}

	public Object get(Object key){
		int a = key.hashCode ()%arr.length;
		if (arr[a]!=null){
			LinkedList<SxtEntry> list = arr[a];
			for (SxtEntry entry : list) {
				if (entry.key.equals (key)){
					return entry.value;
				}
			}
		}
		return null;
	}

	public static void main (String[] args) {
		SxtMap002 m = new SxtMap002 ();
		m.put ("1","hehe");
		m.put ("1","哈哈");
		Object o = m.get ("1");
		System.out.println (o);
	}
}
