package mianshi.newwebcode.base.c02_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {

    private Selector selector;

    /**
     * 定义一个缓冲区，用于从通道中读取数据
     */
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public void initServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册监听连接事件
    }

    public void listen() throws IOException {
        System.out.println("服务器启动成功");
        /**
         * 轮询就绪的Key
         */
        while (true) {
            /**
             * 获取就绪的Key，将阻塞直到有就绪的key
             */
            this.selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    /**
                     * 处理连接请求
                     */
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //为此socket注册读事件
                    socketChannel.register(this.selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                } else if (key.isReadable()) {
                    /**
                     * 处理读请求
                     */
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    int readLen = socketChannel.read(buffer);
                    if (readLen > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String content = new String(bytes, "UTF-8");
                        buffer.clear();
                        System.out.println("服务器接收到消息：" + content);
                        //回写消息
                        socketChannel.write(ByteBuffer.wrap(content.getBytes()));
                    } else if (readLen < 0) {
                        //关闭客户端
                        key.cancel();
                        socketChannel.close();
                        System.out.println("客户端断开连接");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.initServer(9999);
        server.listen();
    }

}

