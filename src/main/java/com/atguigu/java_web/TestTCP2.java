package com.atguigu.java_web;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hike97
 * @create 2018-10-09 14:00
 * @desc
 **/
//TCP编程例二：客户端给服务端发送信息，服务端将信息打印到控制台上，同时发送“已收到信息”给客户端
public class TestTCP2 {
	//客户端
	@Test
	public void test_c () {
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			socket = new Socket (InetAddress.getByName ("127.0.0.1"), 8989);
			os = socket.getOutputStream ();
			os.write ("我是客户端".getBytes ());
			//执行此方法 显式告诉服务端发送完毕
			socket.shutdownOutput ();
			is = socket.getInputStream ();
			byte[] b = new byte[20];
			int len;
			while ((len=is.read (b))!=-1){
				String str = new String (b,0,len);
				System.out.println (str);
			}
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				if (is!=null)
				is.close ();
				if (os !=null)
				os.close ();
				if (socket!=null)
				socket.close ();
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}

	}

	//服务端
	@Test
	public void test_s () {
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			ss = new ServerSocket (8989);
			s = ss.accept ();
			is = s.getInputStream ();
			byte[] b = new byte[20];
			int len;
			while ((len = is.read (b))!=-1){
				String str = new String (b,0,len);
				System.out.println (str);
			}
			//程序问题：InputStream为阻塞流 如果没有文件 会处于等待状态
			//所以 下边的程序无法执行
			os = s.getOutputStream ();
			os.write ("我已收到".getBytes ());
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				if (os!=null)
				os.close ();
				if (is!=null)
				is.close ();
				if (s!=null)
				s.close ();
				if (ss!=null)
				ss.close ();
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}

	}
}
