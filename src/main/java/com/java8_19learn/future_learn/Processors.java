package com.java8_19learn.future_learn;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-06-17 15:03
 * @Modified By:
 **/
public class Processors {
//	如果服务是cpu密集型的，设置为电脑的核数
//	如果服务是io密集型的，设置为电脑的核数*2
	public static void main (String[] args) {
		int i = Runtime.getRuntime ().availableProcessors ();
		System.out.println (i);
	}

}
