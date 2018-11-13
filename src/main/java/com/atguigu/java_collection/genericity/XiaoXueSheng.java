package com.atguigu.java_collection.genericity;

import lombok.Data;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author hike97
 * @create 2018-11-12 14:57
 * @desc 泛型类
 * 字母：
 * T Type 表示类型
 * K V 表示键值中的Key Value
 * E 代表Element
 * 使用时确定类型
 * 注意
 * 	1.泛型只能使用引用类型，不能基本类型
 * 	2.泛型声名时，字母不能使用静态属性|静态方法上
 **/
@Data
public class XiaoXueSheng<T1,T2> {
	private T1 javaScore;
	private T2 oracleScore;

	public static void main (String[] args) {
		//使用时指定类型（引用类型）
		XiaoXueSheng<String, Integer> xxs = new XiaoXueSheng<> ();
		//1.安全：类型检查 编译
		xxs.setJavaScore ("优秀");
		//2.省心：
	}
	//泛型方法
	public static <T> void test(T a ){
		System.out.println (a);
	}

	public static <T extends Closeable> void test (T... a) throws IOException {
		for (T temp : a){
			temp.close ();
		}
	}
}

