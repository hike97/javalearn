package com.atguigu.java_io;

import org.junit.Test;

import java.io.*;

/**
 * @author hike97
 * @create 2018-10-10 10:08
 * @desc 转换流
 **/
public class TestOtherStream {
	/*
		InputStreamReader OutputStreamWriter
		编码：字符串--->字节数组
		解码：字节数组--->字符串
	 */
	@Test
	public void test_1 () throws Exception {
		//解码
		BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream (new File ("dbcp.txt")),"UTF-8"));
		//编码
		BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (new File ("dbcp_.txt")),"UTF-8"));

		String str;
		while ((str=reader.readLine ())!=null){
			writer.write (str);
			writer.newLine ();
			writer.flush ();
		}
		writer.close ();
		reader.close ();
	}

	/**
	 * 标准的输入输出流
	 * 标准的输出流：system.out
	 * 标准的输入流：system.in
	 * * 题目：
	 * 从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
	 * 直至当输入“e”或者“exit”时，退出程序。
	 */
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		System.out.println ("请输入字符串：");
		String str;
		while (true){
			str = br.readLine ();
			if (str.equalsIgnoreCase ("e")||str.equalsIgnoreCase ("exit")){
				break;
			}
			System.out.println (str.toUpperCase ());
		}
		br.close ();
	}
	//打印流 printStream 字节流 printWrite 字符流
	// 打印流：字节流：PrintStream 字符流：PrintWriter
	@Test
	public void printStreamWriter() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("print.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
		PrintStream ps = new PrintStream(fos, true);
		if (ps != null) { // 把标准输出流(控制台输出)改成文件
			System.setOut(ps);
		}
		for (int i = 0; i <= 255; i++) { // 输出ASCII字符
			System.out.print((char) i);
			if (i % 50 == 0) { // 每50个数据一行
				System.out.println(); // 换行
			}
		}
		ps.close();

	}

	//数据流：用来处理基本数据类型、String、字节数组的数据:DataInputStream DataOutputStream
	//dataInputStream中的方法：
	//八种数据类型 和 readUTF->字符串 readFuLLy(byte[])->字符数组
	@Test
	public void testData(){
		DataOutputStream dos = null;
		try {
			FileOutputStream fos = new FileOutputStream("data.txt");
			dos = new DataOutputStream(fos);

			dos.writeUTF("我爱你，而你却不知道！");
			dos.writeBoolean(true);
			dos.writeLong(1432522344);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dos != null){
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	@Test
	public void testData1(){
		DataInputStream dis = null;
		try{
			dis = new DataInputStream(new FileInputStream(new File("data.txt")));
			//			byte[] b = new byte[20];
			//			int len;
			//			while((len = dis.read(b)) != -1){
			//				System.out.println(new String(b,0,len));
			//			}
			String str = dis.readUTF();
			System.out.println(str);
			boolean b = dis.readBoolean();
			System.out.println(b);
			long l = dis.readLong();
			System.out.println(l);


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dis != null){
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
