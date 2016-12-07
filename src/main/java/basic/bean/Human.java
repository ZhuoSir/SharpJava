package basic.bean;

/**
 * Created by sunny-chen on 16/12/7.
 */
public class Human extends Animal {

    static {
        System.out.println("human loaded...");
    }

    {
        System.out.println("human sayhello...");
    }

    public Human() {
        System.out.println("human init...");
    }

    @Override
    public void sleep() {
        System.out.println("human sleeping...");
    }
}
