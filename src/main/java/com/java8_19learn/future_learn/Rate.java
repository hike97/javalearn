package com.java8_19learn.future_learn;

import java.util.Random;

import static com.java8_19learn.future_learn.Shop.delay;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-06-02 22:20
 * @desc 汇率计算
 **/
public class Rate {

	public static double getRate(Money m1,Money m2){
		delay();
		return new Random ().nextDouble ();
	}

	public static enum Money {
		EUR,USD,RMB
	}
}
