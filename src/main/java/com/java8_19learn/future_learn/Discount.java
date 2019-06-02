package com.java8_19learn.future_learn;

import static com.java8_19learn.future_learn.Shop.delay;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-06-02 21:09
 * @desc 以枚举类定义的折扣代码
 **/
public class Discount {

	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		private final int percentage;
		Code(int percentage) {
			this.percentage = percentage;
		}
	}
	// Discount类的具体实现这里暂且不表示，参见代码清单11-14
	public static String applyDiscount(Quote quote) {
		return quote.getShopName() + " price is " +
				Discount.apply(quote.getPrice(),
						quote.getDiscountCode());
	}
	private static double apply(double price, Code code) {
		delay();
		return price * (100 - code.percentage) / 100;
	}
}
