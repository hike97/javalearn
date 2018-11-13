package com.atguigu.java_collection.genericity;

import com.atguigu.java_collection.Student;

/**
 * @author hike97
 * @create 2018-11-12 16:23
 * @desc 泛型通配符测试
 * ？类型不定，使用时确定类型
 * ？使用：声明类型|声明方法上，不能声明类或使用时
 * ？extends:<=上限
 * ？super:>=下限 指定类型为自身或父类
 **/
public class WildCard<T> extends genericityHasNoPolymorphic {
	T score;

	public static void main (String[] args) {
		WildCard<String> stu = new WildCard<> ();
	}

	public static void test1(WildCard<?> student){

	}
	public static void test(WildCard<genericityHasNoPolymorphic> student){

	}
	public static void test3(WildCard<? extends genericityHasNoPolymorphic> student){

	}
}

/**
 * 泛型不存在多态
 */
class genericityHasNoPolymorphic{

}
