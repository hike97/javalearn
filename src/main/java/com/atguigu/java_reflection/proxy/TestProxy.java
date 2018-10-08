package com.atguigu.java_reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hike97
 * @create 2018-10-08 15:23
 * @desc 动态代理:反射是动态语言的关键
 **/
interface Subject{
	void action();
}
//被代理类
class RealSubject implements  Subject{
	@Override
	public void action () {
		System.out.println ("被代理类执行，么么哒");
	}
}

class MyInvocationHandler implements InvocationHandler{
	Object obj;//实现了接口的被代理类的对象的声明
	//1.给被代理的对象实例化 2.返回一个代理类的对象
	public Object blind(Object obj){
		this.obj = obj;
		return Proxy.newProxyInstance (
				obj.getClass ().getClassLoader (),//加载类
				obj.getClass ().getInterfaces (),//实现的接口
				this);//指明invoke
	}
	//当通过代理类的对象发起对被重写的方法的调用时，
	// 都会转换为对如下的invoke方法的调用
	@Override
	public Object invoke (final Object proxy, final Method method, final Object[] args) throws Throwable {
		//抽象方法的返回值是returnVal
		Object returnValue = method.invoke (obj, args);
		System.out.println ("invoke执行");
		return returnValue;
	}
}

public class TestProxy {
	public static void main (String[] args) {
		//1.被代理类的对象
		RealSubject real = new RealSubject ();
		//2.创建一个实现了invocationHandler接口的对象
		MyInvocationHandler handler = new MyInvocationHandler ();
		//3.调用blind()方法，动态返回一个同样实现了real所在类实现的接口Subject的代理类的对象
		Object proxy = handler.blind (real);
		Subject sub = (Subject) proxy;
		sub.action ();//转到对MyInvocationHandler.invoke的调用。

		//在举一例
		NikeClothFactory nike = new NikeClothFactory ();
		ClothFactory clothFactory = (ClothFactory) handler.blind (nike);
		clothFactory.productCloth ();
	}
}
