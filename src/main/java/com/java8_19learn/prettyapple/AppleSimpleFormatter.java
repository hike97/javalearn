package com.java8_19learn.prettyapple;

import com.java8_19learn.entity.Apple;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 17:13
 * @Modified By:
 **/
public class AppleSimpleFormatter implements AppleFormatter {

	public String accept(Apple apple){
		return "An entity of " + apple.getWeight() + "g";
	}
}
