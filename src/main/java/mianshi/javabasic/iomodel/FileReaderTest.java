package mianshi.javabasic.iomodel;

import org.junit.Test;

import java.io.*;

/**
 * @Author hike97
 * @Description
 * @create 2020-11-18 10:19
 * @Modified By:
 **/
public class FileReaderTest {



	@Test
	public void test01 () throws IOException {
		File file = new File ("1.txt");
		System.out.println (file.getAbsoluteFile ());
		FileReader fr = new FileReader (file);
//		int data = fr.read ();
//		while (data!=-1){
//			System.out.print ((char) data);
//			data = fr.read ();
//		}
		/**
		 * 法一：
		 */
		//		int data;
//		while ((data=fr.read ())!=-1){
//			System.out.print ((char) data);
//		}
		/**
		 * 法二：
		 */
		char[] buff = new char[5];
		int len;
		while ((len = fr.read (buff)) != -1){
			//不要写成 buff.length
//			for (int i = 0; i < len; i++) {
//				System.out.print (buff[i]);
//			}
			String str = new String (buff, 0, len);
			System.out.println (str);
		}
		fr.close ();

	}

	/**
	 * 转换流
	 * 字节 字节数组 -->字符数组 字符串 解码
	 * 字符数组 字符串 -->字节 字节数组 编码
	 * 打印流
	 *  printStream
	 *  printWriter
	 */
	@Test
	public void test () throws IOException {
		FileOutputStream fos = new FileOutputStream ("Asccii.txt");
		PrintStream ps = new PrintStream (fos, true);
		if (ps !=null){
			System.setOut (ps);
		}
		for (int i = 0; i < 255; i++) {
			System.out.print ((char)i);
			if (i%50 ==0){
				System.out.println ();
			}
		}
		ps.close ();
		fos.close ();
	}
}
