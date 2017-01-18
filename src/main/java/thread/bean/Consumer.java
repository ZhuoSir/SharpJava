package thread.bean;

/**
 * Created by sunny on 2017/1/9.
 */
public class Consumer implements Runnable {

    private Goods goods;

    public Consumer() {
    }

    public Consumer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            this.goods.consume();
        }
    }
}
