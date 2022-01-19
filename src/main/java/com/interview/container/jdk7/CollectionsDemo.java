package com.interview.container.jdk7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author hike97
 * @Description collections 常用方法
 * @create 2020-11-13 10:54
 * @Modified By:
 **/
public class CollectionsDemo {
	public static void main (String[] args) {
		List<Object> list = new ArrayList<> ();
		list.add (123);
		list.add (43);
		list.add (765);
		list.add (-97);
		list.add (0);
		System.out.println ("原list~~~~~~~~~~~~~~~~~~~");
		System.out.println (list);
		System.out.println ("Collections.shuffle 随机排序后 ~~~~~~~~~~~~");
		Collections.shuffle (list);
		System.out.println (list);
		System.out.println ("Collections.frequency 判断123的出现频率 ~~~~~~~~~~~~");
		System.out.println (Collections.frequency (list, 123));
	    List<Object> dest = new ArrayList<> ();
		System.out.println ("Collections.copy 方法不指定dest的大小 会报错 java.lang.IndexOutOfBoundsException: Source does not fit in dest");
		dest = Arrays.asList (new Object[list.size ()]);
		Collections.copy (dest,list);
		System.out.println (dest);

	}
}
