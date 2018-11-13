package com.sxt.arithmetic;

import java.util.Set;
import java.util.TreeMap;

/**
 * @author hike97
 * @create 2018-11-13 11:16
 * @desc treeMap排序
 **/
public class TreeMapDemo {
	public static void main (String[] args) {
		Person p1 = new Person ("老王", 100);
		Person p2 = new Person ("王富贵", 1000);
		Person p3 = new Person ("老王把", 500);
		Person p4 = new Person ("小王", 300);

		TreeMap<Person, String> map = new TreeMap<Person,String> ((o1,o2)->{
			return Integer.compare (o1.getHandsome (),o2.getHandsome ());
		});
		map.put (p1,"bjsxt");
		map.put (p2,"bjsxt");
		map.put (p3,"bjsxt");
		map.put (p4,"bjsxt");
		//查看键
		Set<Person> people = map.keySet ();
		System.out.println (people);
	}
}
