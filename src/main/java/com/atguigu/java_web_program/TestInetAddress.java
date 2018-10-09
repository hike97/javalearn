package com.atguigu.java_web_program;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author hike97
 * @create 2018-10-09 9:53
 * @desc InetAddress:位于java.net包下
 **/
/*
	网络通信的第一个要素：IP地址。通过IP地址
 */
public class TestInetAddress {
	//inetaddress代表IP地址。一个InetAddress的对象就代表着一个IP地址
	public static void main (String[] args) throws UnknownHostException {
		//getbyname 创建一个InetAddress对象
		InetAddress inet = InetAddress.getByName ("www.baidu.com");
		System.out.println (inet);
		//getHostName（）域名 getHostAddress() IP
		System.out.println (inet.getHostName ());
		System.out.println (inet.getHostAddress ());
		//获取本机IP
		InetAddress host = InetAddress.getLocalHost ();
		System.out.println (host);
		System.out.println (host.getHostName ());
	}
}
