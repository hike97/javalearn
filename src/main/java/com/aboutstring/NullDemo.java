package com.aboutstring;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-22 14:26
 * @desc 对null的理解 关于null的9件事
 **/
public class NullDemo {
	/*
	 *   1）    首先，null是java中的关键字，像public、static、final。它是大小写敏感的，
	 *   你不能将null写成Null或NULL，编译器将不能识别他们然后报错。
	 */
//	private String testNULL = NuLL; 报错会
	/*
	   2）     就像每种原始类型都有默认值一样，int默认值为0，boolean的默认值为false，
	   null是任何引用类型的默认值，不严格的说是所有object类型的默认值。
	   就像你创建了一个布尔类型的变量，它将false作为自己的默认值，
	   java中的任何引用变量都将null作为默认值。这对所有变量都是适用的，
	   如成员变量、局部变量、实例变量、静态变量(但当你使用一个没有初始化的局部变量，编译器会警告你)。
	 */
	private String s;
	@Test
	public void test_nulldefaultValue () {
		System.out.println (s);//输出结果 null
	}
	/*
		3）     要澄清一些误解，null既不是对象也不是一种类型，
		它仅是一种特殊的值，你可以将其赋予任何引用类型，你也可以将null转化成任何类型，
	 */
	@Test
	public void test_nullTransfer () {
		String  string = null;
		Integer intValue = null;
		Double  doubValue = null;

		String st = (String) null;
		Integer it = (Integer) null;
		Double db = (Double)null;
	}
	/*
	     4） null可以赋值给引用变量，不能将null赋值给基本类型变量，如int、double、float、boolean。
	      可以看到，直接将null赋值给基本类型会出现编译错误。但将null赋值给包装类object，
	      然后将object赋给各自的基本类型，编译不会报错，但运行会空指针，这是自动拆箱导致的。
	 */
//	    int i = null; 报错
//	    Integer itr = null;
//	    int i = itr; //这里会报空指针
	/*
	   5)  任何含有null值的包装类在java拆箱生成基本数据类型时候都会抛出一个空指针异常
	 */
	@Test
	public void test_Nullpackage () {
		Map map = new HashMap<> ();
		int [] numbers = {1,2,5,6,8};
		for (int i : numbers) {
			//这里会报错 map.get(i) 会返回一个null
			//HashMap的get()方法将会返回null，而不是0，因为Integer的默认值是null而不是0。
			// 当把null值传递给一个int型变量的时候自动装箱将会返回空指针异常。
			int count = (int) map.get (i);
			map.put (i,count++);
		}
	}

	/*
	   6）     如果使用了带有null值的引用类型的变量，instanceof操作会返回false
	 */
	@Test
	public void test_instanceOf () {
		Integer integer = null;
		if (integer instanceof Integer){
			System.out.println ("null 是Integer 类型");
		}else {
			System.out.println ("null 不是 Integer 类型");
		}
	}
	/*
	   7）    可以使用静态方法来使用一个值为null的引用类型变量。
	   	  因为静态方法使用静态类型绑定，不会抛空指针异常
	 */
	@Test
	public void test_staticMethod() {
		NullDemo nullDemo = null;
		nullDemo.method1();
		nullDemo.method2 ();
	}
	private static void method1(){
		System.out.println ("静态方法执行");
	}
	private void method2(){
		System.out.println ("非静态方法执行");
	}
	/*
	 8）  可以将null传递给方法使用，这时方法可以接收任何引用类型，
	       如 public void print(Object obj) 可以这样调用print(null).
	       编译可以通过，但结果就取决去你的方法了。这个例子中的print方法，
	       不会抛出空指针异常，只是退出，业务逻辑允许的话，推荐使用null安全的方法。
	 */
	@Test
	public void test_nullIsParameter () {
		NullDemo nullDemo = new NullDemo ();
//		String print = nullDemo.print (null);
		Object print = nullDemo.print (null);
		System.out.println ((print instanceof String));
	}

	public Object print (Object o){
		return o;
	}
	/*
	  9)    可以使用== 或者 != 操作来比较null值，
	  		但是不能使用其他算法或者逻辑操作，如大于、小于。
	  		与SQL不同，java中的null==null会返回true；
	 */
	@Test
	public void test_null$null () {
		String a = null;
		String b = null;
		if (a==b){
			System.out.println ("null == null in java");
		}
		if (null !=null){
			System.out.println ("null !=null in java");
		}
	}
}
