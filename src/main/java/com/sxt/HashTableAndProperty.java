package com.sxt;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author hike97
 * @create 2018-11-13 21:49
 * @desc hashtable和property
 **/
public class HashTableAndProperty {
	/**
	 * 一、hashtable与hashmap的区别
	 * 1.主要：hashtable线程安全，同步，效率相对低下
	 * 2.父类：hashtable是dictionary hashmap 是abstractmap
	 * 3.null:hashtable 键值不能为null
	 * 4.hashmap 键最多一个null 值duogenull
	 * 二、properties
	 * 1.作用：读写资源配置文件
	 * 2.键与值只能为字符串
	 * 3.方法
	 * 	getProperty(String key,String value)
	 * 	getProperty(String key)
	 * 	getProperty(String key,String defaultValue)
	 *
	 * 	后缀 .properties
	 * 		store(OutputStream out,String comments)
	 * 		store(Writer writer,String comments)
	 * 	load(InputStream instream)
	 * 	load(Reader reader)
	 * 	.xml
	 * 	   	storeToXML(OutputStream os , String comment):UTF-8 字符集
	 * 	   	storeToXML(OutputStream os , String comment，String encoding)
	 *		loadFromXML(InputStream in)
	 */

	public static void main (String[] args) {
		Properties pro = new Properties ();
		//存储
		pro.setProperty ("driver","oracle.jdbc.driver.OracleDriver");
		//pro.setProperty ("url","jdbc:oracle:thin:@localhost:1521:orcl");
		pro.setProperty ("user","scott");
		pro.setProperty ("pwd","tiger");
		//获取
		String url = pro.getProperty ("url", "test");
		System.out.println (url);

		//绝对路径存储到盘符
		try {
			pro.store (new FileOutputStream (new File ("e:/others/db.properties")),"db配置");
			pro.storeToXML (new FileOutputStream (new File ("e:/others/db.xml")),"db配置");
		} catch (Exception e) {
			e.printStackTrace ();
		}
		//使用相对路径
		try {
			pro.store (new FileOutputStream (new File ("db.properties")),"db配置");
			pro.storeToXML (new FileOutputStream (new File ("src/db.properties")),"db配置");
		} catch (IOException e) {
			e.printStackTrace ();
		}

	}

	/**
	 * 读取配置文件
	 * @throws Exception
	 */
	@Test
	public void test_1() throws Exception {
		Properties pro = new Properties ();
		//绝对路径
		pro.load (new FileReader ("e:/others/db.properties"));
		System.out.println (pro.getProperty ("user", "deafaultsss"));
		//读取相对路径
		pro.load (new FileReader ("src/db.properties"));
		System.out.println (pro.getProperty ("user", "deafaultsss"));
		//类路径 需要加“/”
		pro.load (HashTableAndProperty.class.getResourceAsStream ("/jdbc.properties"));
		System.out.println (pro.getProperty ("user", "deafaultsss"));
		//当前线程路径 当前项目、工程
		pro.load (Thread.currentThread ().getContextClassLoader ().getResourceAsStream ("jdbc.properties"));
		System.out.println (pro.getProperty ("user", "deafaultsss"));
	}
}
