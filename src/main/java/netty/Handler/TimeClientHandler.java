package netty.Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.bean.UnitTime;

import java.util.Date;

/**
 *
 * Created by sunny-chen on 17/1/21.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

//    private ByteBuf byteBuf;
//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        byteBuf = ctx.alloc().buffer(4);
//    }
//
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        byteBuf.release();
//        byteBuf = null;
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf m = (ByteBuf) msg;
//        byteBuf.writeBytes(m);
//        m.release();
//
//        if (byteBuf.readableBytes() >= 4) {
//            long currentTimeMillis = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        }

        UnitTime m = (UnitTime) msg;
        System.out.println(m);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
