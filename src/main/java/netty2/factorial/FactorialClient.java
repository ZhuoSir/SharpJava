package netty2.factorial;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *
 * Created by sunny-chen on 17/1/23.
 */
public class FactorialClient {

    static final String HOST  = System.getProperty("host", "127.0.0.1");
    static final int    PORT  = Integer.parseInt(System.getProperty("port", "8322"));
    static final int    COUNT = Integer.parseInt(System.getProperty("count", "1000"));

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new FactorialClientInitializer());

            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();

            FactorialClientHandler handler = (FactorialClientHandler) future.channel().pipeline().last();

            System.err.format("Factorial of %,d is : %,d", COUNT, handler.getFactorial());
        } finally {
            group.shutdownGracefully();
        }
    }
}
