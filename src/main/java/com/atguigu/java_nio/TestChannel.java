package com.atguigu.java_nio;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.SortedMap;

/**
 * @author hike97
 * @create 2018-10-11 16:00
 * @desc 通道
 * 一、用于源节点与目标节点的连接。在java NIO中负责缓冲区中数据的传输。
 * 		channel 本身不存储数据，因此需要配合缓冲区进行传输。
 * 二、通道的主要实现类
 * 		java.nio.channel接口：
 * 			|--FileChannel
 * 			|--SocketChannel
 * 			|--ServerSocketChannel
 * 		    |--DatagramChannel
 * 三、获取通道
 * 1.java支持通道的类提供了getChannel()方法
 * 		本地IO:
 * 		fileInputStream/fileOutputStream
 * 	    RandomAccessFile
 *
 * 	    网络IO:
 * 	    socket serversocket datagramsocket
 * 	2.jdk1.7 中的NIO2针对各个通道提供了静态方法open()
 * 	3.jdk1.7 中的NIO2的Files 工具类的newByteChannel()
 *四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 * 五、分散（scatter）与聚集（gather）
	 * 分散读取（scattering reads）:将通道中的数据分散到多个缓冲区中
	 * 聚集写入（gathering writes）:将多个缓冲区中的数据聚集到通道中
 * 六、Charset
 * 编码：字符串->字节数组
 * 解码：字节数组->字符串
 **/
interface channel {
	void test();
}
class countUtils{
	Instant getNow(){
		return Instant.now ();
	}

	void getEnd(Instant start){
		Instant end = Instant.now ();
		Duration duration = Duration.between (start, end);
		System.out.println ("该方法用时："+duration.toMillis ()+"毫秒");
	}

}

class Myinvokemethod_ implements InvocationHandler{
	Object obj;

	public void setObj (final Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke (final Object proxy, final Method method, final Object[] args) throws Throwable {
		countUtils utils = new countUtils ();
		Instant now = utils.getNow ();
		Object invoke = method.invoke (obj, args);
		utils.getEnd (now);
		return invoke;
	}
}
class Proxy_{
	public static Object getproxy(Object object){
		Myinvokemethod_ myinvokemethod_ = new Myinvokemethod_ ();
		myinvokemethod_.setObj (object);
		return Proxy.newProxyInstance (object.getClass ().getClassLoader (),
				object.getClass ().getInterfaces ()
				,myinvokemethod_);
	}
}
class Test1 implements channel{
	//1.利用通道为完成文件的复制(非直接缓冲区)
	@Override
	public void test () {
		//1)获取通道
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = new FileInputStream ("E:\\动漫\\提线木偶.mp4").getChannel ();
			outChannel = new FileOutputStream ("D:\\1.mp4").getChannel ();
			//2)分配指定大小的缓冲区
			ByteBuffer buf = ByteBuffer.allocate (1024);
			//3)将通道中的数据存入缓冲区中
			while (inChannel.read (buf)!=-1){
				buf.flip ();//切换读取数据的模式
				//4)将缓冲区中的数据写入通道中
				outChannel.write (buf);
				buf.clear ();//清空缓冲区
			}
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			if (outChannel!=null){
				try {
					outChannel.close ();
				} catch (IOException e) {
					e.printStackTrace ();
				}
			}
			if (inChannel !=null){
				try {
					inChannel.close ();
				} catch (IOException e) {
					e.printStackTrace ();
				}
			}
		}
	}
}
class Test2 implements channel{
	//2.使用直接缓冲区完成文件的复制（内存映射文件）

	/**StandardOpenOption.CREATE  不存在创建 存在覆盖
	 * StandardOpenOption.CREATE_NEW 不存在创建 存在 就报错
	 * @throws Exception
	 */
	@Override
	public void test () {
		FileChannel in = null;
		FileChannel out = null;
		try {
			in = FileChannel.open (Paths.get ("E:","动漫","提线木偶.mp4"), StandardOpenOption.READ);
			out = FileChannel.open (Paths.get ("D:\\1.mp4"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);

			MappedByteBuffer inMappedBuf = in.map (FileChannel.MapMode.READ_ONLY, 0, in.size ());
			MappedByteBuffer outMappedBuf = out.map (FileChannel.MapMode.READ_WRITE, 0, in.size ());

			//直接对缓冲区进行数据的读写操作
			byte[] dst = new byte[inMappedBuf.limit ()];
			inMappedBuf.get (dst);
			outMappedBuf.put (dst);
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				in.close ();
				out.close ();
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}


	}
}
public class TestChannel {
	//字符集
	@Test
	public void test_6() throws CharacterCodingException {

		Charset cs1 = Charset.forName ("GBK");
		//获取编码器
		CharsetEncoder ce = cs1.newEncoder ();
		//获取解码器
		CharsetDecoder cd = cs1.newDecoder ();

		CharBuffer cBuf = CharBuffer.allocate (1024);
		cBuf.put ("尚硅谷威武！");
		cBuf.flip ();
		//编码
		ByteBuffer bBuf = ce.encode (cBuf);
		for (int i = 0; i <12 ; i++) {
			System.out.println (bBuf.get ());
		}
		//解码
		bBuf.flip ();
		CharBuffer charBuffer = cd.decode (bBuf);
		System.out.println (charBuffer.toString ());
		System.out.println ("---------------------");
		bBuf.flip ();
		Charset cs2 = Charset.forName ("GBK");
		CharBuffer decode = cs2.decode (bBuf);
		System.out.println (decode.toString ());



	}
	@Test
	public void test_5() {
		SortedMap<String, Charset> map = Charset.availableCharsets ();
		map.forEach ((k,v)->{
			System.out.println (k+"=>"+v);
		});
	}
	//分散和聚集
	@Test
	public void test_4() throws Exception {
		RandomAccessFile raf1 = new RandomAccessFile ("1.txt", "rw");

		//1.获取通道
		FileChannel channel1 = raf1.getChannel ();
		//2.分配指定大小的缓冲区
		ByteBuffer buf1 = ByteBuffer.allocate (100);
		ByteBuffer buf2 = ByteBuffer.allocate (1024);
		//3.分散读取
		ByteBuffer[] bufs = {buf1,buf2};
		channel1.read (bufs);
		//切换读取数据模式
		for (ByteBuffer byteBuffer : bufs) {
			 byteBuffer.flip ();
		}
		System.out.println (new String (bufs[0].array (), 0, bufs[0].limit ()));
		System.out.println ("---------------------");
		System.out.println (new String (bufs[1].array (), 0, bufs[1].limit ()));

		RandomAccessFile raf2 = new RandomAccessFile ("2.txt", "rw");
		FileChannel channel2 = raf2.getChannel ();
		channel2.write (bufs);
	}
	@Test
	public void test_3 () throws IOException {
		//直接缓冲区
		FileChannel in = FileChannel.open (Paths.get ("E:","动漫","提线木偶.mp4"), StandardOpenOption.READ);
		FileChannel out = FileChannel.open (Paths.get ("D:\\1.mp4"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
		//in.transferTo (0,in.size (),out);
		out.transferFrom (in,0,in.size ());
		in.close ();
		out.close ();

	}
	@Test
	public void test_2()  {//该方法用时：756毫秒
		channel c = (channel) Proxy_.getproxy (new Test2 ());
		c.test ();
	}
	@Test
	public void test_1 () {//该方法用时：12655毫秒
		channel c = (channel) Proxy_.getproxy (new Test1 ());
		c.test ();
	}
}
