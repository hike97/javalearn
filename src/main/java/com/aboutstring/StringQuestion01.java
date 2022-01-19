package com.aboutstring;


import org.junit.Test;

/**
 * @author hike97 2month
 * @create 2020-09-06 12:00
 * @desc 关于String的面试题
 **/
public class StringQuestion01 {
	//ldc Push item from run-time constant pool
	//https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ldc
	//dup Duplicate the top operand stack value
	//Invoke instance method; special handling for superclass, private, and instance initialization method invocations
	public static void main (String[] args) {
//		String str2 = new String ("str") + new String ("01");
//		str2.intern ();
//		String str1 = "str01";
//		System.out.println (str2 == str1);

		String str2 = new String("str")+new String("01");
//		String str1 = "str01";
//		str2.intern();
//		System.out.println(str2==str1);
	}

	/**
	 * 常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量
	 * 只要其中一个是变量，结果就在堆中
	 * 如果拼接的结果调用intern方法，返回值就在常量池中
	 */
	@Test
	public void test_01 () {
		String s1 = "javaEE";
		String s2 = "hadoop";
		String s3 = "javaEEhadoop";
		String s4 = "javaEE" + "hadoop";
		String s5 = s1 + "hadoop";
		String s6 = "javaEE" + s2;
		String s7 = s1 + s2;
		System.out.println (s3 == s4);
		System.out.println (s3 == s5);
		System.out.println (s3 == s6);
		System.out.println (s3 == s7);
		System.out.println (s5 == s6);
		System.out.println (s5 == s7);
		System.out.println (s6 == s7);
		String s8 = s5.intern (); //返回s5相等的常量池的引用
		System.out.println (s3 == s8);
		/*
		 *	true
		 *	false
		 *	false
		 *	false
		 *	false
		 *	false
		 *	false
		 *	true
		 */
	}

	@Test
	public void test_02 () {
		/*
		false
		true
		 */

	}
}
