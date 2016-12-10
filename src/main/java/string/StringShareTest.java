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
        long begin = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str = str + "i";
        }
        long end = System.currentTimeMillis();
        long time1 = end - begin;
        System.out.println("1. String + time = " + time1);

        long begin2 = System.currentTimeMillis();
        String str2 = "";
        for (int i = 0; i < 10000; i++) {
            str2 = str2.concat(i + "");
        }
        long end2 = System.currentTimeMillis();
        long time2 = end2 - begin2;
        System.out.println("2. String concat time = " + time2);

        long begin3 = System.currentTimeMillis();
        StringBuilder str3 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            str3.append("" + i);
        }
        long end3 = System.currentTimeMillis();
        long time3 = end3 - begin3;
        System.out.println("3. StringBuilder append time = " + time3);

        long begin4 = System.currentTimeMillis();
        StringBuffer sf = new StringBuffer("");
        for (int i = 0; i < 10000; i++) {
            sf.append("" + i);
        }
        long end4 = System.currentTimeMillis();
        long time4 = end4 - begin4;
        System.out.println("4. StringBuffer append time = " + time4);
    }
}
