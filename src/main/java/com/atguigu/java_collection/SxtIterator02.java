package com.atguigu.java_collection;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

/**
 * @author hike97
 * @create 2018-11-12 17:52
 * @desc
 **/
public class SxtIterator02 implements Iterable<String> {

	private String[] elem = {"a","b","c","d","e","f","g"};
	private int size = elem.length;

	private class MyIt implements Iterator<String> {
		private int cursor = -1;

		/**
		 * 判断是否存在下一个元素
		 * @return
		 */
		public boolean hasNext(){

			return cursor+1<size;
		}
		/**
		 * 获取下一个元素
		 */
		public String next(){
			cursor++;//移动一次
			return  elem[cursor];
		}
		//删除元素
		public void remove(){

		}
	}
	public Iterator<String> iterator(){
		return new MyIt ();
	}

	public static void main (String[] args) {
		SxtIterator02 list = new SxtIterator02 ();
		Iterator<String> iterator = list.iterator ();
		while (iterator.hasNext ()){
			System.out.println (iterator.next ());
		}
		//增强for 底层实现了iterable接口
		for (String s : list) {
			System.out.println (s);
		}
}
}
