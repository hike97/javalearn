package mianshi.neowebcode.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NettyServer
 * @Description netty入门
 * @Author hike97
 * @Date 2023/3/31 17:09
 * @Version 1.0
 **/
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        try {
            //处理事件组：负责接客
            bossGroup = new NioEventLoopGroup(2);
            //负责服务
            workerGroup = new NioEventLoopGroup(4);
            //Server 启动辅助类
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup,workerGroup);

            bootstrap.channel(NioServerSocketChannel.class);
            //netty 帮我们内部处理了accept的过程
            bootstrap.childHandler(new MyChildInitializer());

            ChannelFuture future = bootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            assert bossGroup != null;
            bossGroup.shutdownGracefully();
            assert workerGroup != null;
            workerGroup.shutdownGracefully();
        }


    }
}
class MyChildInitializer extends ChannelInitializer<SocketChannel> {
    //初始化方法
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("有一个客户端连接成功");
        socketChannel.pipeline().addLast(new MyChildHandler());
    }
}

/**
 * 责任链模式 在链尾添加处理数据逻辑
 */
class MyChildHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString());
        //buf.release();
        //接收到又写了回去
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
