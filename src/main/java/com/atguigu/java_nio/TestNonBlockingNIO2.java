package com.atguigu.java_nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author hike97
 * @create 2018-10-12 16:35
 * @desc UDPNIO
 **/
public class TestNonBlockingNIO2 {
	@Test
	public void test_send() throws Exception {
		DatagramChannel dc = DatagramChannel.open ();
		dc.configureBlocking (false);
		ByteBuffer buf = ByteBuffer.allocate (1024);
		Scanner scan = new Scanner (System.in);
		while (scan.hasNext ()){
			String str = scan.next ();
			buf.put ((new Date ().toString ()+":\n"+str).getBytes ());
			buf.flip ();
			dc.send (buf,new InetSocketAddress ("127.0.0.1",9898));
			buf.clear ();
		}
		dc.close ();
	}

	public static void main (String[] args) throws Exception {
		new TestNonBlockingNIO2 ().test_send ();
	}
	@Test
	public void test_receive () throws Exception {
		DatagramChannel dc = DatagramChannel.open ();
		dc.configureBlocking (false);
		dc.bind (new InetSocketAddress (9898));
		Selector selector = Selector.open ();
		dc.register (selector, SelectionKey.OP_READ);
		while (selector.select ()>0){
			Iterator<SelectionKey> it = selector.selectedKeys ().iterator ();
			while (it.hasNext ()){
				SelectionKey sk = it.next ();

				if (sk.isReadable ()){
					ByteBuffer buf = ByteBuffer.allocate (1024);
					dc.receive (buf);
					buf.flip ();
					System.out.println (new String (buf.array (), 0, buf.limit ()));
					buf.clear ();
				}
			}
			it.remove ();
		}
	}
}
