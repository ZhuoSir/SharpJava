package thread.MianShiTi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 *
 * Created by sunny on 2017/5/17.
 */
public class Two {

    private boolean flag = false;

    Lock lock = new ReentrantLock();

    Condition con = lock.newCondition();

    public void sub() {
        lock.lock();

        try {
            while (flag) {
                try {
                    con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 10; i++) {
                System.out.println("sub" + i);
            }

            flag = true;
            con.signal();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void main() {
        lock.lock();

        try {
            while (!flag) {
                try {
                    con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 20; i++) {
                System.out.println("main" + i);
            }

            flag = false;
            con.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Two two = new Two();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    two.sub();
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            two.main();
        }
    }
}
