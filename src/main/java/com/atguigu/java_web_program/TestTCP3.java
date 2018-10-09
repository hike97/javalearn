package com.atguigu.java_web_program;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hike97
 * @create 2018-10-09 14:36
 * @desc
 **/
//从客户端发送文件给服务端，服务端保存到本地。
	//并返回“发送成功”给客户端
		//并关闭相应的连接
//如下的程序
public class TestTCP3 {

	@Test
	public void test_client () {
		Socket socket = null;
		OutputStream os = null;
		FileInputStream fis = null;
		InputStream is = null;
		try {
			socket = new Socket (InetAddress.getByName ("127.0.0.1"),9898);
			os = socket.getOutputStream ();
			fis = new FileInputStream (new File ("1.png"));
			byte[] b = new byte[1024];
			int len;
			while ((len=fis.read (b))!=-1){
				os.write (b,0,len);
			}
			//发送完毕
			socket.shutdownOutput ();
			//接收
			is = socket.getInputStream ();
			byte[] b1 = new byte[1024];
			int len1;
			while ((len1=is.read (b1))!=-1){
				String str = new String (b1, 0, len1);
				System.out.println (str);
			}
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				if (is !=null)
				is.close ();
				if (os !=null)
				os.close ();
				if (fis !=null)
				fis.close ();
				if (socket!=null)
				socket.close ();
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}

	}

	@Test
	public void test_server () {
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		FileOutputStream fos = null;
		OutputStream os = null;
		try {
			ss = new ServerSocket (9898);
			s = ss.accept ();
			is = s.getInputStream ();
			fos = new FileOutputStream (new File ("3.png"));
			byte[] b = new byte[1024];
			int len;
			while ((len=is.read (b))!=-1){
				fos.write (b,0,len);
			}
			System.out.println ("收到来自于:" + s.getInetAddress ().getHostAddress ());
			//4.发送信息
			os = s.getOutputStream ();
			os.write ("您发送的图片我已接收成功！".getBytes ("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				if (os!=null)
				os.close ();
				if (fos!=null)
				fos.close ();
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
