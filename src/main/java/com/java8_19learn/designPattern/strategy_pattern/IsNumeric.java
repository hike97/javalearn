package com.java8_19learn.designPattern.strategy_pattern;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-24 15:25
 * @Modified By:
 **/
public class IsNumeric implements ValidationStrategy {
	@Override
	public boolean execute (String s) {
		return s.matches ("\\d+");
	}
}
