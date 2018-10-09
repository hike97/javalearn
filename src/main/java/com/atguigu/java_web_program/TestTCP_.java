package com.atguigu.java_web_program;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author hike97
 * @create 2018-10-09 16:26
 * @desc
 **/
public class TestTCP_ {
	public static void main (String[] args) throws IOException {
		Socket socket = new Socket (InetAddress.getByName ("127.0.0.1"), 9090);
		OutputStream os = socket.getOutputStream ();
		//向服务端发送数据
		Scanner scanner = new Scanner (System.in);
		System.out.println ("请输入字符串：");
		String str = scanner.next ();
		os.write (str.getBytes ());
		socket.shutdownOutput ();
		//4.接收
		InputStream is = socket.getInputStream ();
		byte[] b = new byte[10];
		int len;
		while ((len=is.read (b))!=-1){
			String s = new String (b, 0, len);
			System.out.println (s);
		}
		//Guanbi
		is.close ();
		//scanner.close ();
		os.close ();
		socket.close ();
	}

	//客户端
//	@Test
//	public void client () throws IOException {
//		Socket socket = new Socket (InetAddress.getByName ("127.0.0.1"), 9090);
//		OutputStream os = socket.getOutputStream ();
//		//向服务端发送数据
//		Scanner scanner = new Scanner (System.in);
//		System.out.println ("请输入字符串：");
//		String str = scanner.next ();
//		os.write (str.getBytes ());
//		socket.shutdownOutput ();
//		//4.接收
//		InputStream is = socket.getInputStream ();
//		byte[] b = new byte[10];
//		int len;
//		while ((len=is.read (b))!=-1){
//			String s = new String (b, 0, len);
//			System.out.println (s);
//		}
//		//Guanbi
//		is.close ();
//		//scanner.close ();
//		os.close ();
//		socket.close ();
//	}

	//服务端
	@Test
	public void server () throws IOException {
		ServerSocket ss = new ServerSocket (9090);
		Socket s = ss.accept ();
		InputStream is = s.getInputStream ();
		byte[] bytes = new byte[1024];
		String str = new String ();
		int len;
		while ((len=is.read (bytes))!=-1){
			String string = new String (bytes, 0, len);
			str +=string;
		}
		String strUpper = str.toUpperCase ();
		//返回客户端
		OutputStream os = s.getOutputStream ();
		os.write (strUpper.getBytes ());

		//关闭
		os.close ();
		is.close ();
		s.close ();
		ss.close ();
	}

	@Test
	public void test_ () {
		Scanner scanner = new Scanner (System.in);
		System.out.println ("hahah:");
		String next = scanner.next ();

	}
}
