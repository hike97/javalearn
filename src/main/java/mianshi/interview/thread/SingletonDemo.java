package mianshi.interview.thread;

/**
 * @author hike97 2month
 * @create 2020-09-20 23:00
 * @desc 双端检锁机制
 **/
public class SingletonDemo {
	private static volatile SingletonDemo instance;
	private SingletonDemo(){
		System.out.println ("inital Thread name:"+Thread.currentThread ().getName ());
	}
	public static SingletonDemo getInstance(){
		if (instance == null){
			synchronized (SingletonDemo.class){
				if (instance == null){
					instance = new SingletonDemo ();
				}
			}
		}
		return instance;
	}

	public static void main (String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread (()->{
				SingletonDemo.getInstance ();
			}).start ();

		}
	}
}
