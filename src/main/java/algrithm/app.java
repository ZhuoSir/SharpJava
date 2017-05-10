package algrithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2017/2/22.
 */
public class app {
    public static void main(String ss[]) {
        select("abcdefghigklmnopqrstuvwxyz");
        System.out.println();
    }

    public static void select(String s) {
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= s.length(); i++) {
            select(s, i, result);
        }
    }

    public static void select(String s, int i, List<String> result) {

        if (i == 0) {
            for (int j = 0; i < result.size(); j++) {
                System.out.print(result.get(j));
            }
            System.out.println();
            return;
        }

        if (s.length() != 0) {
            result.add(s.charAt(0) + "");
            select(s.substring(1, s.length()), i - 1, result);
            result.remove(result.size() - 1);
            select(s.substring(1, s.length()), i, result);
        }
    }
}
