package com.java19learn.entity;

import lombok.Data;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 14:57
 * @Modified By:
 **/
@Data
public class Apple extends Fruit{

	private String color;

	private int weight;

	public Apple () {
	}

	public Apple (int weight) {
		this.weight = weight;
	}

	public Apple (String color) {
		this.color = color;
	}

	public Apple (String color, int weight) {
		this.color = color;
		this.weight = weight;
	}
}
