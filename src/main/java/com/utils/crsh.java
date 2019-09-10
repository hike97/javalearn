package com.utils;

import java.util.HashMap;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-07-17 16:10
 * @Modified By:
 **/
public class crsh {
	public static void main (String[] args) {
		HashMap<Object, Object> map = new HashMap<> ();
		map.put ("s","b");
		Object a = map.get ("a");
		System.out.println (a);

	}
}
