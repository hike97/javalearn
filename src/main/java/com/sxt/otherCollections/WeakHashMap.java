package com.sxt.otherCollections;

/**
 * @author hike97
 * @create 2018-11-14 13:46
 * @desc
 **/

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.EnumMap;
import java.util.IdentityHashMap;

/**
 * 引用分类
 * --强引用：StrongReference 引用指向对象 gc运行时不回收
 * --软引用：SoftReference gc运行时可能回收（jvm内存不够时）
 * --弱引用：WeakReference gc 运行时立即回收
 * --虚引用：phantomRference 类似无引用，主要跟踪对象被回收的状态，
 * 			不能单独使用
 */
public class WeakHashMap {
	/**
	 * 字符串常量池，共享（不能回收）
	 */
	@Test
	public void test_weak () {
		//字符串常量池
		String str = "hello boy ";
		//弱引用管理对象
		WeakReference<String> wr = new WeakReference<String> (str);
		System.out.println ("gc运行前：" + wr.get ());
		//断开引用
		str = null;
		//通知回收
		System.gc ();
		System.runFinalization ();
		System.out.println ("gc运行后：" + wr.get ());
	}
	/**
	 * 字符串常量池，非共享（能回收）
	 */
	@Test
	public void test_weak02 () {
		//字符串常量池
		String str = new String ("hello boy ");
		//弱引用管理对象
		WeakReference<String> wr = new WeakReference<String> (str);
		System.out.println ("gc运行前：" + wr.get ());
		//断开引用
		str = null;
		//通知回收
		System.gc ();
		System.runFinalization ();
		System.out.println ("gc运行后：" + wr.get ());
	}

	/**
	 * 常量不回收
	 */
	@Test
	public void test_weakHashMap () {
		java.util.WeakHashMap<String, String> map = new java.util.WeakHashMap<> ();
		map.put ("AAA","aaa");
		map.put ("BBB","bbb");
		map.put ("AAA",new String ("aaa"));
		map.put ("BBB",new String ("aaa"));
		//gc 运行 已被回收
		System.gc ();
		System.runFinalization ();

		System.out.println (map.size ());
	}

	/**
	 * identityHashMap 键比较地址去重
	 */
	@Test
	public void test_identiyhashmapCeshi() {
		IdentityHashMap<String, String> map = new IdentityHashMap<> ();
		//常量池中的“a”
		map.put ("a","a1");
		map.put ("a","a2");
		System.out.println (map.size ());
		map.put (new String ("a"),"a3");
		map.put (new String ("a"),"a4");
		System.out.println (map.size ());
	}

	/**
	 * enum map 测试
	 */
	@Test
	public void test_enummap () {
		EnumMap<Season, String> map = new EnumMap<Season, String> (Season.class);
		//存放值
		map.put (Season.A,"A");
		map.put (Season.B,"B");
		map.put (Season.C,"C");
		map.put (Season.D,"D");

		System.out.println (map.size ());

	}
	/**
	 * enumMap
	 */
	enum Season{
		A,B,C,D
	}
}
