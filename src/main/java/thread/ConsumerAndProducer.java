package thread;

import thread.bean.Consumer;
import thread.bean.Goods;
import thread.bean.Producer;

/**
 * Created by sunny on 2017/1/9.
 */
public class ConsumerAndProducer {

    public static void main(String[] args) {
        Goods goods = new Goods();

        Consumer consumer = new Consumer(goods);
        Producer producer = new Producer(goods);

        Thread thread1 = new Thread(consumer);
        Thread thread2 = new Thread(producer);

        thread1.start();
        thread2.start();
    }

}
