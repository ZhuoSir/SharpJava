package net;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * Created by sunny on 2017/5/11.
 */
public class TestProtocal {

    public static void main(String[] args) {
        testProtocal("file://ect/passwd");
    }

    public static void testProtocal(String urlStr) {
        try {
            URL url = new URL(urlStr);
            System.out.println(url.getProtocol() + " is supported.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            String pro = urlStr.substring(0, urlStr.indexOf(":"));
            System.out.println(pro + " is not supported.");
        }
    }
}
