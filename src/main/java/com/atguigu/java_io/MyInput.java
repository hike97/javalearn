package com.atguigu.java_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author hike97
 * @create 2018-10-10 14:14
 * @desc 模拟scanner类
 **/
public class MyInput {

	public String nextString() {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String str = null;
		try {
			str = br.readLine ();
		} catch (IOException e) {
			e.printStackTrace ();
		}
		return str;
	}

	public int nextInt(){
		return Integer.parseInt (nextString ());
	}

	public boolean nextBoolean(){
		return Boolean.parseBoolean (nextString ());
	}

	public static void main (String[] args) {
		MyInput input = new MyInput ();
		System.out.println ("请输入一个字符串：");
		String s = input.nextString ();
		System.out.println (s);
		System.out.println ("请输入一个boolean：");
		boolean b = input.nextBoolean ();
		System.out.println (b);
		System.out.println ("请输入一个int：");
		int i = input.nextInt ();
		System.out.println (i+1);
	}
}
