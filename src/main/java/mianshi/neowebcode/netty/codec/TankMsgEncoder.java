package mianshi.neowebcode.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: hike97
 * @createTime: 2023/04/04 10:18
 * @description: 加密解密器
 */
public class TankMsgEncoder extends MessageToByteEncoder<TankMsg> {

    /**
     * 加密方法
     * @param context           the {@link ChannelHandlerContext} which this {@link MessageToByteEncoder} belongs to
     * @param tankMsg           the message to encode
     * @param byteBuf           the {@link ByteBuf} into which the encoded message will be written
     */
    @Override
    protected void encode(ChannelHandlerContext context, TankMsg tankMsg, ByteBuf byteBuf) {
        byteBuf.writeInt(tankMsg.x);
        byteBuf.writeInt(tankMsg.y);
    }
}
