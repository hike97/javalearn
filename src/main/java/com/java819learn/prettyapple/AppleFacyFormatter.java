package com.java819learn.prettyapple;

import com.java819learn.apple.Apple;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 17:11
 * @Modified By:
 **/
public class AppleFacyFormatter implements AppleFormatter {

	@Override
	public String accept(Apple apple){
		String characteristic = apple.getWeight() > 150 ? "heavy" :
				"light";
		return "A " + characteristic +
				" " + apple.getColor() +" apple";
	}
}
