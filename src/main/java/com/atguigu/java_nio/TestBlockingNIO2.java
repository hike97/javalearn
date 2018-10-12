package com.atguigu.java_nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author hike97
 * @create 2018-10-12 14:40
 * @desc
 * 线程阻塞状态模拟
 **/
public class TestBlockingNIO2 {
	//客户端
	@Test
	public void test_client () throws Exception {
		/**发送本地文件*/
		//获取socketchannel
		SocketChannel socketChannel = SocketChannel.open (new InetSocketAddress ("127.0.0.1", 9999));
		//获取文件channel
		FileChannel inChannel = FileChannel.open (Paths.get ("1.png"), StandardOpenOption.READ);
		//创建缓存区
		ByteBuffer buf = ByteBuffer.allocate (1024);
		//文件流读取
		while (inChannel.read (buf) != -1){
			buf.flip ();
			//套接字流输出
			socketChannel.write (buf);
			buf.clear ();
		}
		socketChannel.shutdownOutput ();

		/**接受服务端的反馈*/
		int len = 0;
		while ((len=socketChannel.read (buf)) !=-1){
			buf.flip ();
			System.out.println (new String (buf.array (), 0, len));
			buf.clear ();
		}
		/**关闭流*/
		inChannel.close ();
		socketChannel.close ();
	}
	//服务端
	@Test
	public void test_server () throws Exception {
		/**接收客户端传来的数据*/
		//服务器socketchannel获取
		SocketChannel sChannel = ServerSocketChannel.open ().bind (new InetSocketAddress (9999)).accept ();
		//服务器文件channel获取
		FileChannel outChannel = FileChannel.open (Paths.get ("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		//获取缓冲区
		ByteBuffer buf = ByteBuffer.allocate (1024);
		//读取客户端传来的channel
		while (sChannel.read (buf)!=-1){
				buf.flip ();
				//服务器文件channel保存到本地
				outChannel.write (buf);
				buf.clear ();
		}
		/**发送反馈给客户端*/
		buf.put ("服务端接收数据成功".getBytes ());
		buf.flip ();
		//服务器发送信息
		sChannel.write (buf);
		/**关闭流*/
		sChannel.close ();
		outChannel.close ();
	}
}
