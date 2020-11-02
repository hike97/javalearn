package com.aboutstring;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Author hike97
 * @Description
 * @create 2020-10-27 14:18
 * @Modified By:
 **/
public class StringDemo {
	/**
	 * String 字符串
	 * 1.String 声明为final 不可被继承
	 * 2.		实现Serializable 表示支持序列化
	 * 			实现了Comparable接口 表示可以比较大小
	 * 3.String 内部定义了 final char[] value 用于存储字符串数据
	 * 4.String 代表不可变的字符序列 简称：不可变
	 */
	@Test
	public void test_01 () {
		String s1 = "abc";//字面量定义方式
		String s2 = "abc";
		System.out.println (s1 == s2);
		s1="hello";
		System.out.println (s1);
		System.out.println (s2);

	}

	@Test
	public void test_02 () {

		String str2 = new String("str")+new String("01");
		str2.intern();
		String str1 = "str01";
		System.out.println(str2==str1);

//		String str2 = new String("str")+new String("01");
//		String str1 = "str01";
//		str2.intern();
//		System.out.println(str2==str1);
	}

	public static void main (String[] args) {
		String s = new String("1");
		s.intern();
		String s2 = "1";
		System.out.println(s == s2);

		String s3 = new String("1") + new String("1");
		String s4 = "11";
		s3.intern();
		System.out.println(s3 == s4);

	}

	/**
	 * String 字符串 和 byte  数组的转换
	 */
	@Test
	public void test_03 () throws UnsupportedEncodingException {
		String str1 = "abc123中国";
		System.out.println ("------------------>>>>string 转 byte数组，utf-8 一个汉字为3个byte，gbk 一个汉字为2个byte");
		byte[] bytes = str1.getBytes ();
		System.out.println (Arrays.toString (bytes));
		byte[] gbks = str1.getBytes ("gbk");
		System.out.println (Arrays.toString (gbks));
		System.out.println ("---------------------->>>>byte 数组转 string");
		String utf_8_str = new String (bytes);
		String gbk_str = new String (gbks);
		String gbk_str_correct = new String (gbks,"gbk" );
		System.out.println (utf_8_str);
		System.out.println (gbk_str);
		System.out.println (gbk_str_correct);

	}
}
