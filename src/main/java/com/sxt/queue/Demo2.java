package com.sxt.queue;

/**
 * @author hike97
 * @create 2018-11-13 14:53
 * @desc 测试自定义栈
 * /**
 * 总结：
 * 	一、单向：一端操作
 * 	1.一般：FIFO
 * 	2.优先和堆栈：LIFO
 **/
public class Demo2 {
	public static void main (String[] args) {
		MyStack<String> stack = new MyStack<> (3);
		stack.push ("www.baidu.com");
		stack.push ("www.google.com");
		stack.push ("www.netty.com");
		stack.push ("www.bjsxt.com");

		System.out.println (stack.size ());
		//遍历
		String item = null;
		while (null!=(item=stack.pop ())){
			System.out.println (item);
		}
	}
}
