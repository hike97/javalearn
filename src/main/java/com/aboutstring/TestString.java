package com.aboutstring;


/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-22 9:59
 * @desc 测试String 空 null 空格字符串
 **/
public class TestString {

	public static void main (String[] args) {
		String s1  = "";
		String s2  = " ";
		String s3  = null;
		System.out.println ("空字符串的长度"+s1.length ());
		System.out.println ("空格字符串的长度"+s2.length ());
		//System.out.println ("null的长度"+s3.length ());

		System.out.println (s1.isEmpty ());
		System.out.println (s2.isEmpty ());
//		System.out.println (s3.isEmpty ());
		System.out.println ("-----------------------华丽的分割线-----------------");
		System.out.println (s1 == null);
		System.out.println (s2 == null);
		System.out.println (s3 == null);
	}
}
