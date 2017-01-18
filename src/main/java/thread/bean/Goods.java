package thread.bean;

/**
 * Created by sunny on 2017/1/9.
 */
public class Goods {

    private int GoodID;

    private String goodName;

    private String goodDetail;

    private boolean flag;

    public int getGoodID() {
        return GoodID;
    }

    public void setGoodID(int goodID) {
        GoodID = goodID;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(String goodDetail) {
        this.goodDetail = goodDetail;
    }

    public synchronized void produce(int goodID, String goodName, String goodDetail) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.goodName = goodName;
        this.goodDetail = goodDetail;
        this.GoodID = goodID;

        flag = true;
        this.notify();
    }

    public synchronized void consume() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(toString());
        this.flag = false;
        this.notify();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("GoodID : " + GoodID);
        buffer.append(", goodName : " + goodName);
        buffer.append(", goodDetail : " + this.goodDetail);
        return buffer.toString();
    }
}
