package com.java8_19learn.designPattern.strategy_pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-24 15:27
 * @Modified By:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validator {

	ValidationStrategy strategy;

	public boolean validate(String s){
		return strategy.execute(s);
	}
}
