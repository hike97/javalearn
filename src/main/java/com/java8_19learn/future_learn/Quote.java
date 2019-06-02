package com.java8_19learn.future_learn;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-06-02 21:37
 * @desc
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Quote {

	private  String shopName;
	private  double price;
	private  Discount.Code discountCode;

	public static Quote parse(String s) {
		String[] split = s.split(":");
		String shopName = split[0];
		double price = Double.parseDouble(split[1]);
		Discount.Code discountCode = Discount.Code.valueOf(split[2]);
		return new Quote (shopName, price, discountCode);
	}

}
