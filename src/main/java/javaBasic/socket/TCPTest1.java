package javaBasic.socket;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author hike97
 * @Description
 * @create 2020-11-19 10:25
 * @Modified By:
 **/
public class TCPTest1 {
	public static void main (String[] args) {
		System.out.println ("您好，我是客户端".getBytes ().length);
	}

	@Test
	public void testClient () throws IOException {
			//创建socket
			InetAddress inet = InetAddress.getByName ("127.0.0.1");
			Socket socket = new Socket (inet, 8889);
			//获取输出流
			OutputStream os = socket.getOutputStream ();
//			os.write ("您好，我是客户端".getBytes ());
			System.out.println ("开始上传服务器图片~~~");
			FileInputStream fis = new FileInputStream ("happy.jpg");
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read (buffer)) != -1) {
				os.write (buffer,0,len);
			}
		    socket.shutdownOutput ();
			//接受服务器反馈
			InputStream sis = socket.getInputStream ();
			len = sis.read (buffer);
			System.out.println (new String (buffer, 0, len));
			//关闭流
		    os.close ();
		    socket.close ();

	}

	@Test
	public void testServer () throws IOException {
		//创建 serverSocket
		Socket socket = new ServerSocket (8889).accept ();
		InputStream sis = socket.getInputStream ();
		//接受客户端信息
		/**
		 * 不推荐 下面代码 容易乱码
		 */
//		byte[] buffer = new byte[1024];
//		int len;
//		while ((len = sis.read (buffer)) != -1) {
//			String str = new String (buffer, 0, len);
//			System.out.println (str);
//		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream ();
		//创建图片字节流
		FileOutputStream fos = new FileOutputStream("happyServer.jpg");
		//往字节流里写图片数据
		byte[] buffer = new byte[1024];
		int len;
		while ((len = sis.read (buffer)) != -1) {
//			baos.write (buffer,0,len);
			fos.write(buffer,0,len);
		}
//		System.out.println (baos.toString ());
		System.out.println ("收到了来自于：" + socket.getInetAddress () + "的消息");
		//获取输出流，准备给客户端发送消息
		OutputStream out = socket.getOutputStream();
		out.write("上传成功".getBytes());
		out.close ();
		fos.close ();
		baos.close ();
		sis.close ();
		socket.close ();

	}
}
