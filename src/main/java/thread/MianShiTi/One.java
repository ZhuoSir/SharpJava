package thread.MianShiTi;

/**
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 *
 *
 * Created by sunny on 2017/5/17.
 */
public class One {

    private boolean flag = false;

    public synchronized void sub() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("sub" + i);
        }

        flag = true;
        this.notify();
    }

    public synchronized void main() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 20; i++) {
            System.out.println("main" + i);
        }

        flag = false;
        this.notify();
    }

    public static void main(String[] args) {
        final One one = new One();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    one.sub();
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            one.main();
        }
    }
}
