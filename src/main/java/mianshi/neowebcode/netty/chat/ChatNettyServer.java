package mianshi.neowebcode.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import mianshi.neowebcode.netty.codec.TankMsg;
import mianshi.neowebcode.netty.codec.TankMsgDecoder;

/**
 * @ClassName ChatNettyServer
 * @Description 聊天工具服务端
 * @Author hike97
 * @Date 2023/4/1 15:53
 * @Version 1.0
 **/
public class ChatNettyServer {

    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * server启动方法
     */
    public  void serverStart() {
        NioEventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        try {
            //处理事件组：负责接客
            bossGroup = new NioEventLoopGroup(2);
            //负责服务
            workerGroup = new NioEventLoopGroup(4);
            //Server 启动辅助类
            ServerBootstrap bootstrap = new ServerBootstrap();

            ChannelFuture future = bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    //加入解析器
                                    .addLast(new TankMsgDecoder())
                                    .addLast(new ChannelInboundHandlerAdapter() {

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    clients.add(ctx.channel());
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                    TankMsg tm = (TankMsg) msg;
                                    ServerFrame.INSTANCE.updateClientMsg(tm.toString());
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    cause.printStackTrace();
                                    //如果发生异常把channel去掉
                                    clients.remove(ctx.channel());
                                    ctx.close();
                                }
                            });
                        }
                    }).bind(8888).sync();
            ServerFrame.INSTANCE.updateServerMsg("server started!");
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
