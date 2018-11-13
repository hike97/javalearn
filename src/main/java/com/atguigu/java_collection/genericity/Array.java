package com.atguigu.java_collection.genericity;

import java.util.ArrayList;

/**
 * @author hike97
 * @create 2018-11-12 16:49
 * @desc 泛型和数组
 * 声明可以使用 但是创建失败
 **/
public class Array {
	public static void main (String[] args) {
		Integer[] arr = new Integer[4];
		//声明可以使用 但是创建失败
		//MyArrayList<String>[] list = new MyArrayList<String>[10];
		MyArrayList<?>[] lists = new MyArrayList[10];
	}
}

class MyArrayList<E>{
	Object[] cap = new Object[10];

	public E[] getAll(){
		return (E[]) cap;
	}

	public E getElem(int idx){
		return (E) cap[idx];
	}
}
