package com.java8_19learn.designPattern.strategy_pattern;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 策略模式
 * @create 2019-05-24 15:18
 * @Modified By:
 **/
public interface ValidationStrategy {
	/**
	 *   一个代表某个算法的接口（它是策略模式的接口）。
	 *   一个或多个该接口的具体实现，它们代表了算法的多种实现（比如，实体类 Concrete-
	 * StrategyA 或者 ConcreteStrategyB ）。
	 *   一个或多个使用策略对象的客户。
	 */
	//假设判断输入一个字符串验证文本
	boolean execute(String s);
}
