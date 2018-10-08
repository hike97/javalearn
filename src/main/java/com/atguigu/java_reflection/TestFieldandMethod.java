package com.atguigu.java_reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author hike97
 * @create 2018-10-08 14:23
 * @desc 调用运行时类中指定的属性 和 方法
 **/
public class TestFieldandMethod {

	@Test
	public void test_1() throws Exception {
		Class<Person> clazz = Person.class;
		//1.获取指定的属性 public 属性
		Field name = clazz.getField ("name");
		//2.创建运行时类的对象
		Person p = clazz.newInstance ();
		System.out.println (p);
		//3.将运行时类的指定的属性赋值
		name.set (p,"aaa");
		System.out.println (p);
		System.out.println ();
		//访问私有的属性:获取声明指定的名为fieldName的属性
		//-1.获取 -2.设置accessible 为true
		Field age = clazz.getDeclaredField ("age");
		age.setAccessible (true);
		age.set (p,10);
		System.out.println (p);

		//Field id = clazz.getField ("id");
		Field id = clazz.getDeclaredField ("id");
	}

	//调用运行时类中指定的方法
	@Test
	public void test_2 () throws Exception {
		Class<Person> clazz = Person.class;
		//获取public f方法
		Method m1 = clazz.getMethod ("show");
		Person person = clazz.newInstance ();
		//invoke(Object obj ,Object ...obj) 实体类  形参
		Object returnVal = m1.invoke (person);
		System.out.println (returnVal);//返回值

		Method m2 = clazz.getMethod ("toString");
		returnVal = m2.invoke (person);
		System.out.println (returnVal);//返回值

		//静态
		System.out.println ("静态方法调用-----------");
		Method m3 = clazz.getMethod ("info");
		m3.invoke (Person.class);
	}

	@Test
	public void test_declaredMethod () throws Exception {
		Class<Person> clazz = Person.class;
		Person p = clazz.newInstance ();
		Method m4 = clazz.getDeclaredMethod ("display", String.class, Integer.class);
		m4.setAccessible (true);
		Object value = m4.invoke (p, "CHN", 10);
		System.out.println (value);

	}
	//调用指定的构造器,创建运行时对象
	@Test
	public void test_otherConstructor () throws Exception {
		Class<Person> clazz = (Class<Person>) Class.forName ("com.atguigu.java_reflection.Person");
		Constructor<Person> con = clazz.getDeclaredConstructor (String.class, int.class);
		con.setAccessible (true);
		Person p = con.newInstance ("罗伟", 20);
		System.out.println (p);
	}
}
