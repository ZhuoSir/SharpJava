package string;

/**
 * Created by sunny on 2016/12/10.
 */
public class StringShareTest {

    // String类由于java的共享设计，在修改变量时使其反复改变栈中的对于堆得引用地址，所以性能低；
    // StringBuffer 和 StringBuilder 类设计时改变其值，其堆内存的地址不变，避免了反复修改栈引用的地址，其性能高；
    // 其中StringBuilder 是专门类似于StringBuffer类的非线性安全类，即StringBuffer是线性安全的，适合于多线程操作；
    // StringBuilder 是线性不安全类，适合于单线程操作，其性能比StringBuffer略高;
    public static void main(String[] args) {

    }
}
