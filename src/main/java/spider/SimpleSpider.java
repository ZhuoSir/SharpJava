package spider;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by sunny-chen on 2017/6/3.
 */
public class SimpleSpider implements Runnable {

    private final int poolSize = 1;

    private ThreadPoolExecutor threadPoolExecutor;

    private Long counter = 0L;

    private final Logger log = Logger.getLogger(SimpleSpider.class);

    private URL url;

    private URLConnection conn;

    private String urlPath;

    private final String pathRegex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";

    private final String localTxt = "/Users/sunny-chen/Documents/JAVA/SharpJava/src/main/java/spider/file/simpleTest.txt";

    public SimpleSpider(String urlPath) {
        this.urlPath = urlPath;
        try {
            url = new URL(urlPath);
            conn = url.openConnection();

            threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader reader  = null;
        PrintWriter writer  = null;
        Pattern pattern = Pattern.compile(pathRegex);

        try {
            writer  = new PrintWriter(new FileWriter(localTxt), true);
            reader  = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String buff;
            while ((buff = reader.readLine()) != null) {
                Matcher bufMatcher = pattern.matcher(buff);
                while (bufMatcher.find()) {
                    writer.write(bufMatcher.group() + "\n");
                    log.info(Thread.currentThread().getName() + "成功爬取url：" + bufMatcher.group());
                    counter++;

                    threadPoolExecutor.execute(new SimpleSpider(bufMatcher.group()));
                }
            }

            log.info(Thread.currentThread().getName() + "共爬取" + counter + "个url。");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            writer.close();
        }
    }
}
