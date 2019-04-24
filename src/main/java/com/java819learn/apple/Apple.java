package com.java819learn.apple;

import lombok.Data;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 14:57
 * @Modified By:
 **/
@Data
public class Apple {

	private String color;

	private int weight;

	public Apple (String color, int weight) {
		this.color = color;
		this.weight = weight;
	}
}
