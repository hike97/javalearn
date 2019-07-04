package com.java8_19learn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-07-04 14:22
 * @Modified By:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubClassEntity {
	private String name;
	private List<Apple> apples;
}
