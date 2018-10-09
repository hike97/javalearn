package com.atguigu.java_web_program;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author hike97
 * @create 2018-10-09 16:08
 * @desc
 **/
public class TestUDP {

	@Test
	public void test_send () {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket ();
			byte[] bytes = "我是要发送的数据".getBytes ();
			//创建一个数据报：每一个数据报不能大于64k，
			// 都记录着数据信息，发送端的ip 端口号，
			// 以及要发送到的接收端的IP,端口号
			DatagramPacket packet = new DatagramPacket (bytes, 0, bytes.length, InetAddress.getByName ("127.0.0.1"), 9090);
			ds.send (packet);
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			if (ds!=null)
			ds.close ();
		}

	}

	@Test
	public void test_receive () {
		DatagramSocket ds = null;
		String str = null;
		try {
			ds = new DatagramSocket (9090);
			byte[] bytes = new byte[1024];
			DatagramPacket packet = new DatagramPacket (bytes, 0, bytes.length);
			ds.receive (packet);

			str = new String (packet.getData (), 0, packet.getLength ());
			System.out.println (str);
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			if (ds!=null)
				ds.close ();
		}

	}
}
