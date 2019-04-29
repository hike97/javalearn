package com.java8_19learn.entity;

import lombok.Data;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-26 19:44
 * @Modified By:
 **/
@Data
public class Dish {
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}
	public enum Type { MEAT, FISH, OTHER }
}
