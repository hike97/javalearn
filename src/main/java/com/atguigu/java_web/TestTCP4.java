package com.atguigu.java_web;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hike97
 * @create 2018-10-09 15:14
 * @desc socket练习
 **/
public class TestTCP4 {
	@Test
	public void test_client () throws IOException {
		Socket socket = new Socket (InetAddress.getByName ("127.0.0.1"), 8888);
		InputStream is = socket.getInputStream ();
		FileOutputStream fos = new FileOutputStream (new File ("haha.png"));
		byte[] bytes = new byte[1024];
		int len;
		while ((len=is.read (bytes))!=-1){
			fos.write (bytes,0,len);
		}
		System.out.println ("已收到");
		fos.close ();
		is.close ();
		socket.close ();
	}

	@Test
	public void test_server () throws IOException {
		ServerSocket ss = new ServerSocket (8888);
		Socket s = ss.accept ();
		OutputStream os = s.getOutputStream ();
		FileInputStream fis = new FileInputStream (new File ("2.png"));
		byte[] bytes = new byte[1024];
		int len;
		while ((len=fis.read (bytes))!=-1){
			os.write (bytes,0,len);
		}
		//发送完毕
		s.shutdownOutput ();
		os.close ();
		fis.close ();
		s.close ();
		ss.close ();
	}
}
