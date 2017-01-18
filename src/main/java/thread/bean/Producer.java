package thread.bean;

/**
 * Created by sunny on 2017/1/9.
 */
public class Producer implements Runnable {

    private Goods goods;

    private int count = 0;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    public Producer() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public void run() {
        while (true) {
            if (count % 2 == 0) {
                goods.produce(count, "goods" + count, "吆西大大滴好啊" + goods);
            } else {
                goods.produce(count, "goods" + count, "日本人不是个东西" + goods);
            }
            count++;
        }
    }
}
