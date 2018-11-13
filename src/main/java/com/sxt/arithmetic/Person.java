package com.sxt.arithmetic;

import lombok.Data;

/**
 * @author hike97
 * @create 2018-11-13 10:29
 * @desc person类
 **/
@Data
public class Person {

	private final String name;
	private final int handsome;

	public Person (String name, Integer handsome) {
		this.name = name;
		this.handsome = handsome;
	}

}
