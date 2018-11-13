package com.sxt.queue;




import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author hike97
 * @create 2018-11-13 14:28
 * @desc 队列模拟银行存款业务
 **/
public class Demo1 {
	public static void main (String[] args) {
		Queue<Request> que = new ArrayDeque<> ();
		for (int i = 0; i <10 ; i++) {
			final int num = i;
			que.offer (new Request () {//存款
				@Override
				public void deposit () {
					System.out.println ("第" + num + "人,办理存款业务，存款额度为：" + (Math.random () * 10000));
				}
			});
		}
		dealWith (que);
	}

	//处理业务
	public static void dealWith(Queue<Request> queue){
		Request request = null;
		while (null!=(request=queue.poll ())){
			request.deposit ();
		}
	}
}
interface Request{
	//存款
	void deposit();
}
