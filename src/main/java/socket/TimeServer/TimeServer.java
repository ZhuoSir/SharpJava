package socket.TimeServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sunny on 2017/1/23.
 */
public class TimeServer {

    static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("The time server is start in port : " + PORT);

            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (null != serverSocket) {
                System.out.println("The time server close");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
