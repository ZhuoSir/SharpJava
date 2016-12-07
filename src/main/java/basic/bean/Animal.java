package basic.bean;

/**
 * Created by sunny-chen on 16/12/7.
 */
public abstract class Animal implements Physiology {

    static {
        System.out.println("animal loaded...");
    }

    {
        System.out.println("animal say hello...");
    }

    public Animal() {
        System.out.println("animal init...");
    }

    @Override
    public void eat() {
        System.out.println("animal eating...");
    }

    @Override
    public void sleep() {
        System.out.println("animal sleelping...");
    }

    @Override
    public void drink() {
        System.out.println("animal drinking...");
    }

    @Override
    public void play() {
        System.out.println("animal playing...");
    }
}
