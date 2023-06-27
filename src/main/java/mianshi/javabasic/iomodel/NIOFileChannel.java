package mianshi.javabasic.iomodel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: hike97
 * @createTime: 2023/06/05 23:28
 * @description: TODO
 */
public class NIOFileChannel {
    public static void main(String[] args) throws Exception {
        write();
//        read();
//        copy();
        //通过transferFrom copy
        copyTransferFrom();
    }

    private static void write() throws Exception {
        String str = "Hello,Java菜鸟程序员";
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("hello.txt");
        //获取通道
        FileChannel channel = fileOutputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        //写入byteBuffer
        byteBuffer.put(str.getBytes());
        //切换模式
        byteBuffer.flip();
        //写入通道
        channel.write(byteBuffer);
        //关闭
        channel.close();
        fileOutputStream.close();
    }

    public static void read() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit())); //Hello,Java菜鸟程序员
        channel.close();
        fileInputStream.close();
    }

    public static void copy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("world.txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        while (inChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            //清空重置
            byteBuffer.clear();
        }
        fileOutputStream.close();
        fileInputStream.close();
    }

    public static void copyTransferFrom() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("world.txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        //从哪拷贝,从几开始到几结束 对应的还有transferTo()方法.
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        outChannel.close();
        inChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
    }
}
