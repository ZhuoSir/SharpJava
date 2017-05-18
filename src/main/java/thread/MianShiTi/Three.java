package thread.MianShiTi;

/**
 * 设计四个线程,其中两个线程每次对变量i加1,另外两个线程每次对i减1
 *
 * Created by sunny on 2017/5/18.
 */
public class Three {

    private int i = 0;

    public static void main(String[] args) {
        Three three = new Three();
        Add add = three.new Add();
        Sub sub = three.new Sub();
        for (int i = 0; i < 2; i++) {
            new Thread(add, "线程" + i).start();
            new Thread(sub, "线程" + i).start();
        }
    }

    class Add implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                addOne();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Sub implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                subOne();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private synchronized void addOne() {
        i++;
        System.out.println(Thread.currentThread().getName() + "加1的值为：" + i);
    }

    private synchronized void subOne() {
        i--;
        System.out.println(Thread.currentThread().getName() + "减1的值为：" + i);
    }
}
