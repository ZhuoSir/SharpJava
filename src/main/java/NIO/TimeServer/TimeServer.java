package NIO.TimeServer;

/**
 *
 * Created by admin on 2017/1/23.
 */
public class TimeServer {

    static final int PORT = 8000;

    public static void main(String[] args) {
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(PORT);
        new Thread(timeServer, "MultiplexerTimeServer-001").start();
    }
}
