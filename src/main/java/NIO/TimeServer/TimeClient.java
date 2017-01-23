package NIO.TimeServer;

/**
 *
 * Created by admin on 2017/1/23.
 */
public class TimeClient {

    public static void main(String[] args) {
        new Thread(new TimeClientHandler("127.0.0.1", 8000), "TimeClient-001")
                .start();
    }
}
