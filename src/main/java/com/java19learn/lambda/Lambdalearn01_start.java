package com.java19learn.lambda;


/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-04-24 17:54
 * @Modified By:
 **/
public class Lambdalearn01_start {
	/**
	 *Lambda 常用的实例
	 */
	/*
	   1.布尔表达式
	   (List<String> list)->list.isEmpty ();
	   2.创建对象
	   （）-> new Apple(10)
	   3.消费一个对象
		（Apple a）->{
			System.out.println(a.getWeight());
		}
		4.从一个对象中选择/抽取
		（String s）->s.length()
		5.组合两个值
		 （int a ,int b）->a*b
		6.比较两个对象
		（Apple a1 Apple a2）->{
		a1.getWeight.compateTo(a2.getWeight())
		}
	 */
		//函数式接口:只有一个抽象方法
	//函数式接口的抽象方法的签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫作函数描述符。
	public static void main (String[] args) {
		//使用匿名类
		Runnable r1 = new Runnable () {
			@Override
			public void run () {
				System.out.println ("Hello World 2");
			}
		};
		//使用lambda
		Runnable r2 = ()->System.out.println ("Hello World 1");
		process (r1);
		process (r2);
	}
	public static void process(Runnable r) {
		r.run ();
	}
}
