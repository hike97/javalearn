package mianshi.neowebcode.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: hike97
 * @createTime: 2023/04/04 10:32
 * @description:
 */
public class TankMsgDecoder extends ByteToMessageDecoder {

    /**
     * 解密器
     * @param ctx     the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in      the {@link ByteBuf} from which to read data
     * @param out     the {@link List} to which decoded messages should be added
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
            //对字符串进行校验 x y  两个 int 8 个字节 如果校验不通过会将信息放到管道里 tcp粘包处理
            if (in.readableBytes() < 8) {
                return;
            }

            int x = in.readInt();
            int y = in.readInt();

            out.add(new TankMsg(x,y));
    }
}
