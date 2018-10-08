package com.atguigu.java_reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hike97
 * @create 2018-10-08 17:19
 * @desc 练习
 **/
interface Fridge{
	void putSomeThing();
}
class FridgeUtil{
	void open(){
		System.out.println ("把冰箱门打开");
	}
	void close(){
		System.out.println ("把冰箱门关上");
	}
}
class Myinvokemethod implements InvocationHandler{
	Object obj;

	public void setObj (final Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke (final Object proxy, final Method method, final Object[] args) throws Throwable {
		FridgeUtil util = new FridgeUtil ();
		util.open ();
		method.invoke (obj,args);
		util.close ();
		return null;
	}
}
class HaierFridge implements Fridge{

	@Override
	public void putSomeThing () {
		System.out.println ("把大象装进去");
	}
}
class Proxy_{
	public static Object getproxy(Object object){
		Myinvokemethod myinvokemethod = new Myinvokemethod ();
		myinvokemethod.setObj (object);
		return Proxy.newProxyInstance (object.getClass ().getClassLoader (),
				object.getClass ().getInterfaces ()
		,myinvokemethod);
	}
}
public class TestAOP_my {
	public static void main (String[] args) {
		HaierFridge hf = new HaierFridge ();
		Fridge pro = (Fridge) Proxy_.getproxy (hf);
		pro.putSomeThing ();
	}
}
