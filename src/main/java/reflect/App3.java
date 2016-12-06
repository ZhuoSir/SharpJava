package reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny-chen on 16/12/6.
 */
public class App3 {

    public static void main(String[] args) {
        App3 app3 = new App3();
        try {
//            app3.saveStringInIntegerArray();
//            app3.getArrayInfoByReflect();
            app3.modifyArrayLengthByReflect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在泛型为Integer的ArrayList中存放一个String类型的对象
     */
    public void saveStringInIntegerArray() throws Exception {
        List<Integer> list = new ArrayList<>();
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, "Java反射");
        System.out.println(list.get(0));
    }

    /**
     * 通过反射获取数组信息
     * */
    public void getArrayInfoByReflect() {
        int[] temp = {1, 2, 3, 4, 5};
        Class<?> demo = temp.getClass().getComponentType();
        System.out.println("数组类型：" + demo.getName());
        System.out.println("数组长度：" + Array.getLength(temp));
        System.out.println("数组的第一个元素：" + Array.get(temp, 0));
        Array.set(temp, 0, 100);
        System.out.println("修改后的数组的第一个元素为：" + Array.get(temp, 0));
    }

    /**
     * 通过反射修改数组长度
     * */
    public void modifyArrayLengthByReflect() {
        int[] temp = {1, 2, 3, 4, 5};
        int[] newTemp = (int[]) arrayIncre(temp, 10);
        print(newTemp);
        String[] atr = {"a","b","c"};
        String[] newAtr = (String[]) arrayIncre(atr,6);
        print(newAtr);
    }

    /**
     * 数组扩容
     * */
    public Object arrayIncre(Object obj, int len) {
        Class<?> arr = obj.getClass().getComponentType();
        Object newArr = Array.newInstance(arr, len);
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);
        return newArr;
    }

    /**
     * 数组打印，反射方式
     * */
    public void print(Object obj) {
        Class<?> c = obj.getClass();
        if (!c.isArray()) {
            return;
        }
        System.out.println("数组长度为：" + Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj,i) + " ");
        }
        System.out.println();
    }
}
