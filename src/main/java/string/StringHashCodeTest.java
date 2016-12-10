package string;

/**
 * Created by sunny on 2016/12/10.
 */
public class StringHashCodeTest {

    // 使用System.out.println(obj.hashCode)输出时对象的哈希码；
    // 而非内存地址。在java中是不可能获得对象的真正的内存地址的；
    // 因为java中堆是由JVM管理的不能直接操作；
    // 只能说此时打印出的hash码表示了该对象在java虚拟机中的内存地址；
    // java虚拟机会根据该hashCode最终在真正的堆空间中给该对象分配一个地址；
    // 但是该地址不能通过java的API获取；

    // String变量链接新的字符串时会改变hashCode，变量是在JVM中"链接--断开"；
    // StringBuffer 变量链接新的字符串不会改变hashCode，因为变量的堆地址不会变；
    // StringBuilder 变量链接新的字符串不会改变hashCode，因为变量的堆地址不会变；
    public static void main(String[] args) {
        String str1 = "win this game";
        System.out.println("str1 的hashCode: " + str1.hashCode());

        str1 = str1 + " and next game";
        System.out.println("str1 的hashCode: " + str1.hashCode());

        StringBuffer sb1 = new StringBuffer("win this game");
        System.out.println("sb1 的hashCode: " + sb1.hashCode());
        System.out.println("sb1 的string的hashCode: " + sb1.toString().hashCode());

        StringBuffer sb2 = sb1.append(" and next game");
        System.out.println("sb2 的hashCode: " + sb2.hashCode());
        sb2.insert(2, "chen");
        System.out.println("sb2 的hashCode: " + sb2.hashCode());
        System.out.println("sb2: " + sb2.toString());

        StringBuilder sbd1 = new StringBuilder("win this game");
        System.out.println("sbd1 的hashCode: " + sbd1.hashCode());
        sbd1.append(" and next game");
        System.out.println("sbd1 的hashCode: " + sbd1.hashCode());
    }
}
