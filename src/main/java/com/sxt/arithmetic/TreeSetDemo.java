package com.sxt.arithmetic;

import lombok.Data;
import sun.reflect.generics.tree.Tree;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author hike97
 * @create 2018-11-13 10:30
 * @desc
 * /**
 * TreeSet排序实现方法
 * 1.数据元素可以排序且不可重复
 * 1）元素可以排序 java.lang.Comparable+compareTo
 * new TreeSet()
 * 2)排序业务类 java.util.Comparotor+compare
 **/
public class TreeSetDemo {
	public static void main (String[] args) {
		Person p1 = new Person ("老王", 100);
		Person p2 = new Person ("王富贵", 1000);
		Person p3 = new Person ("老王把", 500);
		Person p4 = new Person ("小王", 300);
		//依次存到TreeSet容器中
//		TreeSet<Person> persons = new TreeSet<> (new Comparator<Person> () {
//			@Override
//			public int compare (Person o1, Person o2) {
//				return o1.getHandsome () - o2.getHandsome ();
//			}
//		});
		TreeSet<Person> persons = new TreeSet<Person> ((o1,o2)->{
				return o1.getHandsome ()-o2.getHandsome ();
		});
		//TreeSet 在添加数据时排序
		persons.add (p1);
		persons.add (p2);
		persons.add (p3);
		persons.add (p4);
		System.out.println (persons);
		//改变数据
//		p4.setName ("老王");
//		p4.setHandsome (100);
		System.out.println (persons);
		TreeSet<Worker> workers = new TreeSet<> ();
		workers.add (new Worker ("1",2000.5));
		workers.add (new Worker ("2",20001.5));
		workers.add (new Worker ("3",92000.5));
		System.out.println (workers);
	}
}
//treeset 实现comparable接口
@Data
class  Worker implements Comparable<Worker>{
	String type;
	double salary;

	public Worker (String type, double salary) {
		this.type = type;
		this.salary = salary;
	}

	/**
	 * 按工资升序
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo (Worker o) {
		return Double.compare (this.salary, o.salary);
	}
}
