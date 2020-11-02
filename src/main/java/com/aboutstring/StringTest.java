package com.aboutstring;


/**
 * @Author hike97
 * @Description
 * @create 2020-10-29 16:26
 * @Modified By:
 **/
public class StringTest {
	String str = new String("good");
	char[] ch = {'t','e','s','t'};
	public void change(String str,char ch[]){
		str = "test ok";
		ch[0] = 'b';
	}

	public static void main (String[] args) {
		StringTest ex = new StringTest ();
		ex.change (ex.str,ex.ch);
		System.out.println (ex.str);
		System.out.println (ex.ch);
	}
}
