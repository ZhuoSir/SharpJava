package netty.Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handlers a server-side channel
 *
 * Created by sunny-chen on 17/1/21.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Discard the received data silently;
        System.out.println("Msg has received : " + ((ByteBuf) msg).toString());
        ((ByteBuf) msg).release();
        System.out.println("Msg has been released");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
