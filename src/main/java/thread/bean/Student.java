package thread.bean;

/**
 *
 * Created by sunny on 2017/5/21.
 */
public class Student {

    private String name;

    private int age;

    boolean flag;

    public synchronized void set(String name, int age) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name = name;
        this.age  = age;

        flag = true;
        this.notify();
    }

    public synchronized void get() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + "--" + age);

        this.flag = false;
        this.notify();
    }
}
