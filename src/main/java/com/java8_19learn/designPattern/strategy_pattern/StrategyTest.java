package com.java8_19learn.designPattern.strategy_pattern;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-24 15:29
 * @Modified By:
 **/
public class StrategyTest {

	public static void main (String[] args) {
		Validator numericValidator = new Validator (new IsNumeric ());
		boolean b1 = numericValidator.validate ("aaaaa");
		Validator lowerCaseValidator = new Validator (new IsALLowerCase ());
		boolean b2 = lowerCaseValidator.validate ("bbbb");
		System.out.println (b1);
		System.out.println (b2);
		//lambda表达式 -->已经对部分代码进行了封装
		Validator numericValidator_ =
				new Validator((String s) -> s.matches("[a-z]+"));
		boolean b1_ = numericValidator.validate("aaaa");
		Validator lowerCaseValidator_ =
				new Validator((String s) -> s.matches("\\d+"));
		boolean b2_ = lowerCaseValidator.validate("bbbb");

	}
}
