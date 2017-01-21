package netty.others;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import netty.bean.UnitTime;

/**
 *
 * Created by sunny-chen on 17/1/21.
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        UnitTime time = (UnitTime) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeInt((int) time.value());
        ctx.write(encoded, promise);
    }
}
