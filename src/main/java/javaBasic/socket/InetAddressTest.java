package javaBasic.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author hike97
 * @Description 网络编程
 * @create 2020-11-18 17:43
 * @Modified By:
 **/
public class InetAddressTest {
	public static void main (String[] args) throws UnknownHostException {
		InetAddress name = InetAddress.getByName ("www.baidu.com");
		System.out.println (name);
		InetAddress hostName = InetAddress.getByName ("127.0.0.1");
		System.out.println (hostName);
		InetAddress host = InetAddress.getLocalHost ();
		System.out.println (host);
	}
}
