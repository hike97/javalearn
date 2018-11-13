package com.atguigu.java_collection;

/**
 * @author hike97
 * @create 2018-11-12 16:59
 * @desc 手写迭代器
 **/
public class SxtIterator {

	private String[] elem = {"a","b","c","d","e","f","g"};
	private int size = elem.length;

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

	public static void main (String[] args) {
		SxtIterator iterator = new SxtIterator ();
		while (iterator.hasNext ()){
			System.out.print (iterator.next ()+" ");
		}
	}
}
