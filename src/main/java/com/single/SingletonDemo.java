package com.single;



/**
 * @author hike97 2month
 * @create 2020-09-02 21:23
 * @desc 单例模式的几种实现方式
 **/
public class SingletonDemo {
	public static void main (String[] args) {
		SingleObject object = SingleObject.init ();
		SingleObject object2 = SingleObject.init ();
		System.out.println (object == object2);
	}
}
class SingleObject {
	private static volatile SingleObject obj;
	//私有构造方法
	private SingleObject(){}
	//获取实例的方法
	public static SingleObject init(){
		if(obj==null){
			synchronized (SingleObject.class){
				if (obj==null) obj = new SingleObject ();
			}
		}
		return obj;
	}


}