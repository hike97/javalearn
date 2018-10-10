package com.atguigu.java_web_program;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hike97
 * @create 2018-10-09 15:37
 * @desc 客户端给服务器端发送文本，服务器端变成大写返回给客户端
 **/
public class TestTCP5 {
	@Test
	public void test_kehudan () throws IOException {
		Socket socket = new Socket (InetAddress.getByName ("127.0.0.1"), 9999);
		//服务器发送给客户端
		OutputStream os = socket.getOutputStream ();
		FileInputStream fis = new FileInputStream (new File ("trans.txt"));
		byte[] bytes = new byte[1024];
		int len;
		while ((len=fis.read (bytes))!=-1){
			os.write (bytes,0,len);
		}
		socket.shutdownOutput ();
		System.out.println ("客户端读取完毕");
		//服务器存储变换后的文件
		InputStream is = socket.getInputStream ();
		FileOutputStream fos = new FileOutputStream (new File ("hello.txt"));
		byte[] bytes_ = new byte[1024];
		int len_;
		while ((len_=is.read (bytes_))!=-1){
			fos.write (bytes_,0,len_);
		}
		System.out.println ("已存本地");
	}

	@Test
	public void test_fuwuduan () throws IOException {
		ServerSocket ss = new ServerSocket (9999);
		Socket s = ss.accept ();
		InputStream is = s.getInputStream ();
		OutputStream os = s.getOutputStream ();
		byte[] bytes = new byte[1024];
		int len;
		String str = null;
		while ((len = is.read (bytes))!=-1){
			str = new String (bytes,0,len);
			System.out.println ("服务器端收到的信息：" + str);

		}
		s.shutdownInput ();
		os.write (str.toUpperCase ().getBytes ());
		os.close ();
		is.close ();
		s.close ();
		ss.close ();
	}
}
