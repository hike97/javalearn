package com.atguigu.java_reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author hike97
 * @create 2018-10-08 10:49
 * @desc
 **/
public class TestConstructor {
	Class<Person> clazz = Person.class;

	/**
	 * 条件：
	 * 	1.无参构造器
	 * 	2.权限
	 * @throws Exception
	 */
	@Test
	public void test_1 () throws Exception {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		Object o = clazz.newInstance ();
		Person p  = (Person)o;
		System.out.println (p);

	}
	//获取运行时类的属性field
	@Test
	public void test_2() {
		Class<Person> clazz = Person.class;
		//1.只显示了name -> 只能获取到运行时类中(+父类)声明为public的属性
		Field[] fields = clazz.getFields ();
		for (Field field : fields) {
			System.out.println (field);
		}
		System.out.println ("---------------------华丽的分割线----------------");
		//2.getDeclaredFields ()：获取运行时类本身声明的所有属性（-父类）
		Field[] declaredFields = clazz.getDeclaredFields ();
		for (Field declaredField : declaredFields) {
			System.out.println (declaredField.getName ());
		}
	}
	//权限修饰符 变量类型 变量名
	//获取属性的各个部分的内容
	@Test
	public void test_3 () {

		Field[] f = clazz.getDeclaredFields ();
		for (Field field : f) {
			//1.获取每个属性的权限修饰符
			System.out.print (Modifier.toString (field.getModifiers ())+" ");
			//2.获取属性的变量类型
			System.out.print (field.getType ().getName ()+" ");
			//3.获取属性名
			System.out.print (field.getName ());
			System.out.println ();
		}
	}
	//方法
	//1.获取运行时类的方法
	@Test
	public void test_Method () {
		Class<Person> clazz = Person.class;
		//1.getMethod 获取运行时类及其父类中所有的声明为public的方法
		Method[] m1 = clazz.getMethods ();
		for (Method method : m1) {
			System.out.println (method);
		}
		System.out.println ("-----------------分割线-----------------");
		//2.getDeclaredMethod:获取类本身声明的所有方法
		Method[] m2 = clazz.getDeclaredMethods ();
		for (Method m : m2) {
			System.out.println (m);
		}
	}
	//注解 权限修饰符 返回至类型 方法名 形参列表 异常
	@Test
	public void test_Method_detail() {
		Method[] m1 = clazz.getMethods ();

		for (Method method : m1) {
			//1.注解
			Annotation[] ann = method.getAnnotations ();
			for (Annotation a : ann) {
				System.out.println (a);
			}

			//2.权限修饰符
			System.out.print (Modifier.toString (method.getModifiers ())+" ");
			//3.返回至类型
			System.out.print (method.getReturnType ().getName ()+" ");
			//4.方法名
			System.out.print (method.getName ()+" ");
			//5.形参列表
			Class<?>[] params = method.getParameterTypes ();
			int i = 0;
			System.out.print ("(");
			for (Class<?> param : params) {
				System.out.print (param.getName () + " args-" + i+++" ");
			}
			System.out.print (")");
			//6.异常类型
			Class<?>[] exps = method.getExceptionTypes ();
			if (exps.length != 0){
				System.out.print ("throws ");
			}
			for (int i1 = 0; i1 < exps.length; i1++) {
				System.out.print (exps[i1].getName () + " ");
			}
			System.out.println ();
		}
	}
	//获取构造器
	@Test
	public void test_constructor() throws Exception {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		Constructor<?>[] constructors = clazz.getDeclaredConstructors ();
		for (Constructor<?> constructor : constructors) {
			System.out.println (constructor);
		}
	}
	//1-获取运行时类的父类
	@Test
	public void test_superclass() throws Exception {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		Class<?> superclass = clazz.getSuperclass ();
		System.out.println (superclass);
	}
	//2-获取带泛型的父类
	@Test
	public void test_generator () throws Exception {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		Type gen = clazz.getGenericSuperclass ();
		System.out.println (gen);
	}
	//3-获取父类的泛型
	@Test
	public void test_43() throws ClassNotFoundException {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		Type gen = clazz.getGenericSuperclass ();
		ParameterizedType pa = (ParameterizedType) gen;
		Type[] ars = pa.getActualTypeArguments ();
		System.out.println (((Class) ars[0]).getName());
	}
	//4-获取实现的接口
	@Test
	public void test_4 () throws ClassNotFoundException {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		Class<?>[] interfaces = clazz.getInterfaces ();
		for (Class<?> anInterface : interfaces) {
			System.out.println (anInterface);
		}
	}
	//4-获取实包
	@Test
	public void test_5() throws ClassNotFoundException {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		System.out.println (clazz.getPackage ());
	}
	//4-获取注解
	@Test
	public void test_6() throws ClassNotFoundException {
		String className = "com.atguigu.java_reflection.Person";
		Class<?> clazz = Class.forName (className);
		Annotation[] anns = clazz.getAnnotations ();
		for (Annotation ann : anns) {
			System.out.println (ann);
		}
	}
}
