package mianshi.neo_webcode.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

/**
 * @ClassName NettyClient
 * @Description netty 客户端
 * @Author hike97
 * @Date 2023/4/1 0:06
 * @Version 1.0
 **/
public class NettyClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(1);
        //辅助启动类
        Bootstrap b =  new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                //加入自己的处理器
                socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println(msg.toString());
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        cause.printStackTrace();
                        ctx.close();
                        System.out.println("产生异常,上下文关闭触发future.channel.closeFuture()");
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) {
                        ByteBuf buf = Unpooled.copiedBuffer("mashibing".getBytes());
                        ctx.writeAndFlush(buf);
                    }
                });
            }
        });
        //sync 是阻塞的
        ChannelFuture future = b.connect("localhost", 8888).sync();

        /*
         * 在这行代码中，future是一个ChannelFuture对象，通过调用channel方法获得了对应的Channel对象。然后，通过closeFuture方法获取到
         * 一个新的ChannelFuture对象，它代表了Channel的关闭操作。最后，通过调用sync方法等待closeFuture对象的完成，并阻塞当前线程直到操作完成。
         * 因此，这行代码的含义是等待Channel关闭操作完成，防止程序提前退出或者引发异常，确保程序正常退出并释放资源。
         */
        future.channel().closeFuture().sync();
        System.out.println("频道关闭");
        workerGroup.shutdownGracefully();
        System.out.println("工作组关闭");
    }
}
