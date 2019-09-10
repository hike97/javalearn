package com.atguigu.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * @author hike97
 * @create 2018-11-01 17:23
 * @desc test类
 **/
public class Test {
	public static void main (String[] args) {
//		float f = 98.8f;
//		BigDecimal decimal = new BigDecimal (f).setScale (0, RoundingMode.HALF_UP);
//		System.out.println (decimal);
//		Optional<Object> optional = Optional.empty ();
//		optional.ifPresentOrElse (System.out::println,()->System.out.println ("啥玩营业没有！"));
		BigDecimal zero = new BigDecimal ("0.0");
		System.out.println (BigDecimal.TEN.compareTo (zero));
		System.out.println (zero.compareTo (null));
	}
}
