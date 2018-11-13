package com.sxt.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author hike97
 * @create 2018-11-13 14:46
 * @desc 使用队列实现自定义堆栈
 * 1.弹
 * 2.压
 * 3.获取头
 **/
public class MyStack<E> {
	//容器
	private Deque<E> container = new ArrayDeque<> ();
	//容量
	private Integer cap;

	public MyStack (Integer cap) {
		this.cap = cap;
	}

	//压栈
	public boolean push(E e){
		if (container.size ()+1>cap){
			return false;
		}
		return container.offerLast (e);
	}
	//弹栈
	public E pop(){
		return container.pollLast ();
	}
	//获取
	public E peek(){
		return container.peekLast ();
	}
	//大小
	public int size(){
		return  this.container.size ();
	}
}
