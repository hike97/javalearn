package com.java8_19learn.default_method;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-27 10:29
 * @Modified By:
 **/
public interface A {
	/**
	 * (1) 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优
	 * 先级。
	 * (2) 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择
	 * 拥有最具体实现的默认方法的接口，即如果 B 继承了 A ，那么 B 就比 A 更加具体。
	 * (3) 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，
	 * 显式地选择使用哪一个默认方法的实现。
	 */
	default void hello() {
		System.out.println("Hello from A");
	}
}
interface B extends A{
	default void hello() {
		System.out.println("Hello from B");
	}
}
class C implements B,A{
	public static void main(String... args) {
		new C().hello();
	}
}
class D implements A{ }
//class C extends D implements B, A {
//	public static void main(String... args) {
//		new C().hello();
//	}
//}
