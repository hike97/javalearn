package com.atguigu.java_web;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hike97
 * @create 2018-10-09 10:31
 * @desc socket
 **/
//客户端给服务端发送信息。服务端输出此信息到控制台上
//网络编程实际上就是Socket的编程
public class TestTCP {

	//客户端
	@Test
	public void test_client () throws IOException {
		Socket socket = null;
		BufferedWriter os = null;
		try {
			//创建socket对象
			 socket = new Socket (InetAddress.getByName ("127.0.0.1"),9090);
			 //os = socket.getOutputStream ();
			 os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			 os.write ("我是客户端,请多关照");
		} catch (Exception e) {
			e.printStackTrace ();
		}finally {
			//关闭相应的流
			if (os !=null)
				os.close ();
			if (socket!=null)
			socket.close ();
		}

	}
	//服务端
	@Test
	public void test_server () {
		ServerSocket ss = null;
		Socket s = null;
		BufferedReader is = null;
		try {
			//1.创建一个ServerSocket的对象，通过构造器指明自身的端口号
			ss = new ServerSocket (9090);
			//2.调用其accept()方法，返回一个socket的对象
			s = ss.accept ();
			//3.调用ocket对象的getInputStream()获取一个从客户端发送过来的输入流
			is = new BufferedReader (new InputStreamReader (s.getInputStream(),"UTF-8"));
//			byte[] b = new byte[20];
//			int len;
			String str;
			while ((str = is.readLine ()) != null){
				System.out.print (str);
			}
			System.out.println ("收到来自于:" + s.getInetAddress ());
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				if (is!=null)
					is.close ();
				if (s!=null)
					s.close ();
				if (ss !=null)
				ss.close ();
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}

	}
}
