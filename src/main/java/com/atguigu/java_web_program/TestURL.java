package com.atguigu.java_web_program;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author hike97
 * @create 2018-10-09 16:55
 * @desc URL:统一资源定位符，一个URL的对象，对应着互联网上一个资源
 * //我们可以通过URL的对象调用其相应的方法，将此资源读取“下载”
 **/
public class TestURL {
	public static void main (String[] args) throws IOException {
		URL url = new URL ("http://localhost:8080/examples/hello.txt?a=b");//File file = new FIle("文件路径")
		/**
		 * 方法
		 */
//		System.out.println (url.getProtocol ());
//		System.out.println (url.getHost ());
//		System.out.println (url.getPort ());
//		System.out.println (url.getFile ());
//		System.out.println (url.getRef ());
//		System.out.println (url.getQuery ());

		//如何将服务端的资源读取进来:openStream
		InputStream is = url.openStream ();
		byte[] bytes = new byte[20];
		int len;
		while ((len = is.read (bytes))!=-1){
			String str = new String (bytes, 0, len);
			System.out.println (str);
		}
		is.close ();
		//如果既有数据的输入，又有数据的输出，则考虑使用URLConnection
		URLConnection urlConnection = url.openConnection ();
		InputStream uis = urlConnection.getInputStream ();
		FileOutputStream fos = new FileOutputStream (new File ("abx.txt"));
		byte[] bytes1 = new byte[20];
		int len1;
		while ((len1 = uis.read (bytes1))!=-1){
			fos.write (bytes1,0,len1);
		}
		fos.close ();
		uis.close ();
	}
}
