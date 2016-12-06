package reflect;

/**
 * Created by sunny-chen on 16/12/6.
 */
public class App2 {

    public static void main(String[] args) {
        MyInvocationHandler demo = new MyInvocationHandler();
        Subject sub = (Subject) demo.bind(new SubjectImpl());
        String info = sub.sayHello("chen","i love u");
        System.out.println(info);
    }
}
