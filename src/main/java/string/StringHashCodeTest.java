package string;

/**
 * Created by sunny on 2016/12/10.
 */
public class StringHashCodeTest {

    // 使用System.out.println
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
