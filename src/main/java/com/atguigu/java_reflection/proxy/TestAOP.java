package com.atguigu.java_reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hike97
 * @create 2018-10-08 16:37
 * @desc 动态代理和AOP
 **/
interface Human{
	void info();
	void fly();
}
//被代理类
class SuperMan implements Human{
	@Override
	public void info () {
		System.out.println ("我是超人！我怕谁");
	}

	@Override
	public void fly () {
		System.out.println ("I believe I can fly!");
	}
}
//切面方法
class HumanUtil{
	void method1(){
		System.out.println ("方法一");
	}
	void method2(){
		System.out.println ("方法二");
	}
}
//invoke 实例
class MyInvocationHandler_ implements InvocationHandler{
	Object obj;

	public void setObject(Object obj){
		this.obj = obj;
	}

	@Override
	public Object invoke (final Object proxy, final Method method, final Object[] args) throws Throwable {
		//aop代理方法的实现
		HumanUtil h = new HumanUtil ();
		h.method1 ();
		Object returnVal = method.invoke (obj, args);
		h.method2 ();
		return returnVal;
	}
}
//返回动态代理对象
class MyProxy{
	//动态创建一个代理类的对象
	public static  Object getProxyInstance(Object object){
		MyInvocationHandler_ handler_ = new MyInvocationHandler_ ();
		handler_.setObject (object);
		return Proxy.newProxyInstance (object.getClass ().getClassLoader (),
				object.getClass ().getInterfaces ()
		,handler_);
	}
}
//测试
public class TestAOP {
	public static void main (String[] args) {
		SuperMan man = new SuperMan ();
		Human hu = (Human) MyProxy.getProxyInstance (man);
		hu.info ();//通过代理类的对象调用重写的抽象方法
		System.out.println ();
		hu.fly ();

		//********************
		System.out.println ();
		((ClothFactory)MyProxy.getProxyInstance (new NikeClothFactory ())).productCloth();
	}
}
