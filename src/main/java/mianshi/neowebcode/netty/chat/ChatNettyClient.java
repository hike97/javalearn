package mianshi.neowebcode.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;
import lombok.Data;
import mianshi.neowebcode.netty.codec.TankMsg;
import mianshi.neowebcode.netty.codec.TankMsgEncoder;

/**
 * @ClassName Client
 * @Description 聊天工具客户端
 * @Author hike97
 * @Date 2023/4/1 15:44
 * @Version 1.0
 **/
@Data
public class ChatNettyClient {

    private Channel channel = null;
    public void connect() {
        NioEventLoopGroup workerGroup = null;
        try {
            workerGroup = new NioEventLoopGroup(1);
            //辅助启动类
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    //将channel 赋值给成员变量
                    channel = socketChannel;
                    //加入自己的处理器
                    socketChannel.pipeline()
                            //message 写消息
                            .addLast(new TankMsgEncoder())
                            .addLast(new ChannelInboundHandlerAdapter() {

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ctx.writeAndFlush( new TankMsg(5,8));
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf buf = null;
                            try {
                                //将接收的字节转为String byteBuf 用的是直接内存 无法回收
                                buf = (ByteBuf) msg;
                                //生成一个可读的字节数组
                                byte[] bytes = new byte[buf.readableBytes()];
                                buf.getBytes(buf.readerIndex(),bytes);
                                String str = new String(bytes);
                                System.out.println("接收到消息："+str);
                                //更新消息方法
                                ClientFrame.INSTANCE.updateText(str);
                                //byteBuf 使用的是引用计数法
                                System.out.println("byteBuf引用计数："+buf.refCnt());
                            } finally {
                                if (buf != null){
                                    //利用工具类释放资源
                                    ReferenceCountUtil.release(buf);
                                }
                            }

                        }

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            cause.printStackTrace();
                            ctx.close();
                        }
                    });
                }
            });
            //sync 是阻塞的
            ChannelFuture future = b.connect("localhost", 8888).sync();
            System.out.println("connect to server");
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("频道关闭");
            assert workerGroup != null;
            workerGroup.shutdownGracefully();
        }
    }

    public void send(String text) {
        channel.writeAndFlush(Unpooled.copiedBuffer(text.getBytes()));
    }

    /**
     * 关闭服务器发送特殊符号
     */
    public void closeConnection() {
        send("__bye__");
        //触发closeFuture
        channel.close();
    }
}
