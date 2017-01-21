package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.Handler.TimeClientHandler;
import netty.others.TimeDecoder;

/**
 * Created by sunny-chen on 17/1/21.
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = 8000;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                }
            });

            ChannelFuture future = b.connect(host, port).sync();

            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
