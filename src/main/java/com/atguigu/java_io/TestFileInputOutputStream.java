package com.atguigu.java_io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author hike97
 * @create 2018-10-09 20:37
 * @desc
 * 1.流的分类
	 * 按照流向的不同：输入输出流
	 * 按照处理数据单位的不同：字节流 字符流（处理的文本文件）
	 * 按照角色的不同：节点流 处理流
 * 2.IO的体系
	 * 抽象基类        节点流（文件流）
	 * InputStream    FileInputStream
 	 * OutputStream   FileOutputStream
 * 	   Reader         FileReader
 * 	   Writer         FileWriter
 **/
public class TestFileInputOutputStream {

	@Test
	public void testCopyFile(){
		long start = System.currentTimeMillis();
		//		String src = "C:\\Users\\shkstart\\Desktop\\1.avi";
		//		String dest = "C:\\Users\\shkstart\\Desktop\\2.avi";
		String src = "dbcp.txt";
		String dest = "dbcp2.txt";
		copyFile(src,dest);
		long end = System.currentTimeMillis();
		System.out.println("花费的时间为：" + (end - start));//3198
	}

	// 实现文件复制的方法
	public void copyFile(String src, String dest) {
		// 1.提供读入、写出的文件
		File file1 = new File(src);
		File file2 = new File(dest);
		// 2.提供相应的流
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file1);
			fos = new FileOutputStream(file2);
			// 3.实现文件的复制
			byte[] b = new byte[1024];
			int len;
			while ((len = fis.read(b)) != -1) {
				// fos.write(b);//错误的写法两种： fos.write(b,0,b.length);
				fos.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	// 从硬盘读取一个文件，并写入到另一个位置。（相当于文件的复制）
	@Test
	public void testFileInputOutputStream() {
		// 1.提供读入、写出的文件
		File file1 = new File("C:\\Users\\shkstart\\Desktop\\1.jpg");
		File file2 = new File("C:\\Users\\shkstart\\Desktop\\2.jpg");
		// 2.提供相应的流
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file1);
			fos = new FileOutputStream(file2);
			// 3.实现文件的复制
			byte[] b = new byte[20];
			int len;
			while ((len = fis.read(b)) != -1) {
				// fos.write(b);//错误的写法两种： fos.write(b,0,b.length);
				fos.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	@Test
	public void test_ () throws IOException {
		File file = new File ("hello.txt");
		FileOutputStream fos = new FileOutputStream (file);
		fos.write (new String("you can you up no can no bb").getBytes ());
	}
	@Test
	public void test_FileInputStream2 () throws IOException {
		File file = new File ("D:\\io\\helloworld.txt");
		FileInputStream fis = new FileInputStream (file);
		byte[] b = new byte[5];//读取到的数据要写入的数组。
		int len;
		while ((len = fis.read (b))!=-1){
//			for (int i = 0; i <len ; i++) {
//				System.out.print ((char) b[i]);
//			}
//			for (int i = 0; i <b.length ; i++) {
//				System.out.print ((char) b[i]);
//			}
			String str = new String (b, 0, len);
			System.out.println (str);
		}
		fis.close ();
	}

	@Test
	public void test_testFileInputStream1 () throws IOException {
		File file = new File ("D:\\io\\helloworld.txt");
		FileInputStream fis = new FileInputStream (file);
//		int b = fis.read ();
//		while (b!=-1){
//			System.out.print ((char) b);
//			b = fis.read ();
//		}
		int b;
		while ((b = fis.read ())!=-1){
			System.out.print ((char) b);
		}
		fis.close ();
	}
}
