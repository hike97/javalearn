package com.atguigu.java_collection.genericity;

/**
 * @author hike97
 * @create 2018-11-12 15:28
 * @desc
 **/
public abstract class Father<T,T1> {
	T name;
	public abstract void test(T t);
}
/**
 * 子类制定具体类型
 * 属性类型为具体类型
 * 方法同理
 * 要么子类擦除，父类泛型
 * 1.父类中 随父类
 * 2.子类中 随子类
 */
class Child extends Father<String,Integer>{
	String t2;
	@Override
	public void test (String t) {

	}
}

/**
 * 子类为泛型类,类型在使用时确定
 * @param <T>
 */
class Child2<T,T1> extends  Father<T,T1>{

	@Override
	public void test (T t) {

	}
}
/**
 * 子类为泛型类，父类不指定类型
 */
class Child3<T1,T2> extends Father{
	T1 name2;
	@Override
	public void test (Object t) {

	}
}
/**
 * 子类与父类同时擦除
 */
class Child4 extends Father{
	String name;
	@Override
	public void test (Object t) {

	}
}
/**
 * 错误：子类擦除，父类使用泛型
 */
