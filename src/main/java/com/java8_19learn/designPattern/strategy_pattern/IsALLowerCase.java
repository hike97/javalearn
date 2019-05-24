package com.java8_19learn.designPattern.strategy_pattern;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 是否全部小写
 * @create 2019-05-24 15:24
 * @Modified By:
 **/
public class IsALLowerCase implements ValidationStrategy {
	@Override
	public boolean execute (String s) {
		return s.matches ("[a-z]+");
	}
}
