package com.java819learn;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 17:24
 * @Modified By:
 **/
public class MeaningOfThis {
	public final int value = 4;
	public void doIt()
	{
		int value = 6;
		Runnable r = new Runnable(){
			public final int value = 5;
			public void run(){
				int value = 10;
				System.out.println(this.value);
			}
		};
		r.run();
	}
	public static void main(String...args)
	{
		MeaningOfThis m = new MeaningOfThis();
		m.doIt();
		//返回 5 this指的就是当前的值 即匿名函数中的值
	}
}
