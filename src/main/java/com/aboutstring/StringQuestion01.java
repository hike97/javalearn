package com.aboutstring;



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
}
