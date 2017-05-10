package socket.TimeServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * Created by sunny on 2017/1/23.
 */
public class TimeClient {

    static final int PORT = 8080;

    public static void main(String[] args) {
        Socket         socket = null;
        BufferedReader in     = null;
        PrintWriter    out    = null;

        try {
            socket = new Socket("127.0.0.1", PORT);
            in     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out    = new PrintWriter(socket.getOutputStream(), true);

            out.println("QUERY TIME ORDER");
            System.out.println("Send order to server succeed.");

            String resp = in.readLine();
            if (null != resp) {
                System.out.println("Now is : " + resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.close();
            }

            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
