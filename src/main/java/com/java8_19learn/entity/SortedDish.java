package com.java8_19learn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-07-04 14:17
 * @Modified By:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortedDish {
	private int dishSize;
	private SubClassEntity sortedDish;
}
