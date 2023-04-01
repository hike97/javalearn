package mianshi.neo_webcode.base.c02_nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws Exception {
        //创建一个socketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //连接远程服务端，非阻塞模式下这里会直接返回false
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        //等待连接完成
        while (!socketChannel.finishConnect()) {
            //可以在等待过程中做其他事
            System.out.println("正在连接中...");
        }
        System.out.println("连接成功");
        //发送一个请求消息到服务端
        String requestMsg = "Hello, NIO!";
        ByteBuffer writeBuffer = ByteBuffer.wrap(requestMsg.getBytes());
        socketChannel.write(writeBuffer);

        //接收服务端响应的数据
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readNum = socketChannel.read(readBuffer);
        while (readNum != -1) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            System.out.println("收到来自服务端的响应：" + new String(bytes));
            readBuffer.clear();
            //继续读取数据
            readNum = socketChannel.read(readBuffer);
        }
        //关闭连接
        socketChannel.close();
    }
}


