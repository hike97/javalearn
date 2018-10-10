package com.atguigu.java_io;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author hike97
 * @create 2018-10-10 13:39
 * @desc 支持随机访问
 **/
/*
	1.即可以充当一个输入流，又可以充当一个输出流
	2.支持从文件的开头读取，写入
	3.支持从任意位置的读取，写入（插入）
 */
public class TestRandomAccessFile {
	//实现插入的效果：在d字符后面插入“xy”
	@Test
	public void test4(){
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(new File("hello1.txt"),"rw");

			raf.seek(4);
			byte[] b = new byte[10];
			int len;
			StringBuffer sb = new StringBuffer ();
			while ((len = raf.read (b) )!= -1){
				sb.append (new String (b,0,len));
			}
			raf.seek (4);
			raf.write ("hahah".getBytes ());
			raf.write (sb.toString ().getBytes ());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(raf != null){
				try {
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	//实现插入的效果：在d字符后面插入“xy”
	@Test
	public void test3(){
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(new File("hello1.txt"),"rw");

			raf.seek(4);
			String str = raf.readLine();//efg123456
			//			long l = raf.getFilePointer();
			//			System.out.println(l);

			raf.seek(4);
			raf.write("xy".getBytes());
			raf.write(str.getBytes());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(raf != null){
				try {
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	//实际上实现的是覆盖的效果
	@Test
	public void test_2 () throws Exception {
		RandomAccessFile raf = new RandomAccessFile (new File ("hello_rw.txt"),"rw");
		raf.seek (3);
		raf.write ("xy".getBytes ());
		raf.close ();
	}
	//进行文件的读，写
	@Test
	public void test_1() throws Exception {
		RandomAccessFile raf1 = new RandomAccessFile (new File ("hello.txt"),"r");
		RandomAccessFile raf2 = new RandomAccessFile (new File ("hello_rw.txt"),"rw");
		byte[] b = new byte[20];
		int len;
		while ((len = raf1.read (b) )!=-1){
			raf2.write (b,0,len);
		}
		raf1.close ();
		raf2.close ();
	}
}
