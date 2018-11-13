package com.sxt;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author hike97
 * @create 2018-11-13 16:11
 * @desc enumeration的使用
 * 1.hasMoreElements()-->hasNext()
 * 2.nextElement ()-->next()
 * //Enumeration子类
 * StringTokenizer:String split() 字符串分割
 * 不支持正则表达式
 * StringTokenizer(String str,String delim)
 **/
public class enumeration {
	public static void main (String[] args) {
		Vector<String> vector = new Vector<> ();
		vector.add ("javase");
		vector.add ("html");
		vector.add ("Oracle");

		//遍历该vector
		Enumeration<String> elements = vector.elements ();
		while (elements.hasMoreElements ()){
			System.out.println (elements.nextElement ());
		}

		String emailStr = "bjsxt@163.com;bjsxt@qq.com;bjsxt@sohu.com";
		StringTokenizer token = new StringTokenizer (emailStr, ";");
		while (token.hasMoreElements ()){
			System.out.println (token.nextElement ());
		}
	}
}
