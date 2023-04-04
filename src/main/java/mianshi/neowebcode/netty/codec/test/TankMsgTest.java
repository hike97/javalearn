package mianshi.neowebcode.netty.codec.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import mianshi.neowebcode.netty.codec.TankMsg;
import mianshi.neowebcode.netty.codec.TankMsgDecoder;
import mianshi.neowebcode.netty.codec.TankMsgEncoder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: hike97
 * @createTime: 2023/04/04 11:13
 * @description: Netty 单元测试类
 */
public class TankMsgTest    {

    @Test
    public void encode() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new TankMsgEncoder());
        TankMsg msg = new TankMsg(1, 2);
        //write and read outbound
        channel.writeOutbound(msg);
        ByteBuf buf = channel.readOutbound();

        assertEquals(1,buf.readInt());
        assertEquals(2,buf.readInt());
    }

    @Test
    public void decode() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new TankMsgDecoder());

        ByteBuf buf = Unpooled.buffer().writeInt(5).writeInt(8);

        channel.writeInbound(buf);
        TankMsg tm = channel.readInbound();
        assertEquals(5,tm.getX());
        assertEquals(8,tm.getY());
    }
}
