package com.atguigu.java_juc.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author hike97
 * @Description 队列
 * @create 2019-12-12 16:25
 * @Modified By:
 **/
public class T03_ConcurrentQueue {
	//queue的简介
	public static void main (String[] args) {
		Queue<Object> queue = new ConcurrentLinkedQueue<> ();//无界队列
		//只有put会阻塞，等待队列有空闲位置；
		// add和offer都是直接返回了，一个返回false，一个返回异常。
		for (int i = 0; i < 10; i++) {
			queue.offer ("a " + i);
		}
		System.out.println (queue);
		System.out.println (queue.size ());
		//poll 拿出来 并删除当前元素
		System.out.println (queue.poll ());
		System.out.println (queue.size ());
		//peek 拿出来 不删除当前元素
		System.out.println (queue.peek ());
		System.out.println (queue.size ());
	}

	/**
	 * 在并发的队列上jdk提供了两套实现，
	 * 一个是以ConcurrentLinkedQueue为代表的高性能队列，
	 * 一个是以BlockingQueue接口为代表的阻塞队列，
	 *        LinkedBlockingQueue
	 *        ArrayBlockingQueue
	 *        DelayQueue
	 *        SynchrounosQueue
	 *        TransferQueue
	 * 无论在那种都继承自Queue。
	 */
	/*
	   阻塞式的queue
	    无界队列
	   		linkedBlockingQueue
	   		用哪个linkedBlockingQueue实现生产者消费模式
	   		运用put和peek方法。
		有界队列 ArrayBlockingQueue
	 */
	@Test
	public void test_linkedBlockingQueueORArrayBlockingQueue () throws InterruptedException {
		BlockingQueue<String> queue
				//				= new LinkedBlockingQueue<> ();
				= new ArrayBlockingQueue<> (50);
		Random random = new Random ();

		//生产者线程
		new Thread (() -> {
			for (int i = 0; i < 100; i++) {
				//put满了 会阻塞
				try {
					queue.put ("a" + i);
					TimeUnit.MILLISECONDS.sleep (random.nextInt (1000));
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
		}, "producer").start ();

		//消费者线程
		for (int i = 0; i < 5; i++) {
			new Thread (() -> {
				for (; ; ) {
					try {
						System.out.println (Thread.currentThread ().getName () + "take - " + queue.take ());
					} catch (InterruptedException e) {
						e.printStackTrace ();
					}
				}
			}, "consumer-" + i).start ();
		}
		//除此之外的offer方法  成功或失败返回 true或false 可添加时间
		queue.offer ("a");
		queue.offer ("b", 1, TimeUnit.MILLISECONDS);
		Thread.sleep (100000);
	}

	/**
	 * delayQueue:插入数据(需要实现Delayed接口)后
	 * 经过一段时间 才能从队列拿出
	 * 默认等待时间最长的元素最先被拿出
	 * 可以用来做定时执行任务
	 */
	@Test
	public void test_DelayQueue () {
		long now = System.currentTimeMillis ();
		//创建时间后多少秒 时间越短 说明存在越长
		MyTask t1 = new MyTask (now + 1000);
		MyTask t2 = new MyTask (now + 2000);
		MyTask t3 = new MyTask (now + 1500);
		MyTask t4 = new MyTask (now + 2500);
		MyTask t5 = new MyTask (now + 500);
		BlockingQueue<MyTask> tasks = new DelayQueue ();
		try {
			tasks.put (t1);
			tasks.put (t2);
			tasks.put (t3);
			tasks.put (t4);
			tasks.put (t5);
			System.out.println (tasks);
			//取出数据
			System.out.println ("--------------------------------------------");
			for (int i = 0; i < 5; i++) {
				System.out.println (tasks.take ());
			}
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}


	}

	/**
	 * transferQueue 元素先不存储到队列中，
	 * 如果有消费者存在，直接将元素推给消费者
	 * 适用于更高的并发
	 * 没有消费者线程 调用transfer 线程会阻塞
	 */
	@Test
	public void test_transferQueue () throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
        //transfer 为linkedTransferQueue 专有方法
		strs.transfer("aaa");//transfer 用于实时消息处理

//		strs.put("aaa"); add offer 都会阻塞


		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
        Thread.sleep (100000);
	}

	/**
	 * synchronousQueue 为即时队列 长度为0 无法使用add方法 只能用put
	 * 是一种特殊的transferQueue
	 */
	@Test
	public void test_synchronusQueue () throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<> ();
//        strs.add ("a");//java.lang.IllegalStateException: Queue full
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		strs.put ("a");
		Thread.sleep (100000);
	}















	//创建一个实现Delayed接口的类并重写比较方法
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	static class MyTask implements Delayed {
		long runningTime;

		@Override
		public long getDelay (TimeUnit unit) {
			return unit.convert (runningTime - System.currentTimeMillis (), TimeUnit.MILLISECONDS);
		}

		@Override
		public int compareTo (Delayed o) {
			if (this.getDelay (TimeUnit.MILLISECONDS) < o.getDelay (TimeUnit.MILLISECONDS))
				return -1;
			else if (this.getDelay (TimeUnit.MILLISECONDS) > o.getDelay (TimeUnit.MILLISECONDS))
				return 1;
			else
				return 0;
		}
	}
}
