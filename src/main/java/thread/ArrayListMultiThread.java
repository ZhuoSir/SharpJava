package thread;

import java.util.ArrayList;

/**
 *
 * Created by sunny-chen on 17/1/8.
 */
public class ArrayListMultiThread implements Runnable{

    static ArrayList<Integer> list = new ArrayList<>(2000);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        try {
            startAndJoin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startAndJoin() throws InterruptedException {
        Thread thread1 = new Thread(new ArrayListMultiThread());
        Thread thread2 = new Thread(new ArrayListMultiThread());

        thread1.start();
        thread2.start();

        // 主线程等待thread1执行结束
        thread1.join();
        // 主线程等待thread2执行结束
        thread2.join();

        System.out.println(list.size());
    }
}
