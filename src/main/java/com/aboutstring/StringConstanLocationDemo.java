package com.aboutstring;

/**
 * @Author hike97
 * @Description
 * @create 2020-10-28 17:40
 * @Modified By:
 **/
public class StringConstanLocationDemo {
	/**
	 * 字符串常量池 位置证明
	 */
	private static String str = "";

	public static void main (String[] args) {
		//-Xms32m -Xmx32m
		char element = 'a';
		StringBuffer sb = new StringBuffer ();
		for (int i = 0; i < 1024 * 1024 * 64; i++) {
			sb.append (element);
		}
		str = sb.toString ();
		System.out.println ();
	}
}
