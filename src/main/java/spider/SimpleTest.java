package spider;

/**
 * 简单的网络爬虫样例
 *
 * Created by sunny-chen on 2017/6/3.
 */
public class SimpleTest {

    public static void main(String[] args) {
        new Thread(new SimpleSpider("http://www.sina.com.cn/")).start();
    }

}
