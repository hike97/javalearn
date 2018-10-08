package com.atguigu.java_reflection.proxy;

/**
 * @author hike97
 * @create 2018-10-08 15:12
 * @desc 静态代理实例
 **/
interface  ClothFactory{
	void productCloth();
}
//被代理类
class NikeClothFactory implements ClothFactory{
	@Override
	public void productCloth () {
		System.out.println ("Nike工厂生产一批衣服");
	}
}
//代理类
class ProxyFactory implements ClothFactory{
	ClothFactory cf;
	//创建代理类的对象时，实际传入一个被代理类的对象
	public ProxyFactory (ClothFactory cf) {
		this.cf = cf;
	}

	@Override
	public void productCloth () {
		System.out.println ("代理类开始执行，收代理费$10000");
		cf.productCloth ();
	}
}
public class TestClothProduct {
	public static void main (String[] args) {
		NikeClothFactory nike = new NikeClothFactory ();//创建被代理类的对象
		ProxyFactory proxyFactory = new ProxyFactory (nike);//创建代理类的对象
		proxyFactory.productCloth ();//代理类的方法
	}
}
