package com.java819learn.prettyapple;

import com.java819learn.apple.Apple;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 17:13
 * @Modified By:
 **/
public class AppleSimpleFormatter implements AppleFormatter {

	public String accept(Apple apple){
		return "An apple of " + apple.getWeight() + "g";
	}
}
