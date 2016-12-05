package reflect;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sunny on 2016/12/5.
 */
public class App {

    public static void main(String[] args) {
        DemoInterface demo = new DemoClass();
//        printDetail(demo);
//        createObjByReflect(DemoClass.class.getName());
//        createObjByReflectConstructor(demo.getClass().getName());
//        getAllProperties(demo.getClass().getName());
        invokeMethod(demo.getClass().getName());
    }

    public static void printDetail(DemoInterface demo) {
        System.out.println("对象名：" + demo.getClass().getName());
        System.out.println("类型名：" + demo.getClass().getTypeName());
        System.out.println("规范名称：" + demo.getClass().getCanonicalName());
        System.out.println("所在包：" + demo.getClass().getPackage());
        System.out.println("简单名称：" + demo.getClass().getSimpleName());
        List<Class<?>> interfaceList = Arrays.asList(demo.getClass().getInterfaces());
        for (Class<?> inter : interfaceList) {
            System.out.println("实现了：" + inter.getName() + "接口");
            Method[] methods = inter.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                System.out.println("方法：" + method.getName() + "；");
            }
        }
        Class<?> superClass = demo.getClass().getSuperclass();
        System.out.println("父类：" + superClass.getName());
        Field[] fields = demo.getClass().getFields();
        Field[] declaredFields = demo.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            System.out.println(fields[i].getName());         //public 公共可以访问的属性
        }
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println(declaredFields[i].getName()); //private protected public属性，但是不包括继承属性
        }
        Constructor<?> con[] = demo.getClass().getConstructors();
        for (int i = 0; i < con.length; i++) {
            System.out.println("构造函数：" + con[i]);
        }
    }

    public static void createObjByReflect(String name) {
        Class<?> demo = null;
        try {
            demo = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DemoClass object = null;
        try {
            object = (DemoClass) demo.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        object.setName("chen");
        object.setResult(1);
        object.setTitle("win this game");
        System.out.println(object.toString());
    }

    public static void createObjByReflectConstructor(String name) {
        Class<?> demo = null;
        try {
            demo = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DemoClass demoClass1 = null;
        DemoClass demoClass2 = null;
        DemoClass demoClass3 = null;
        DemoClass demoClass4 = null;
        DemoClass demoClass5 = null;
        Constructor<?> con[] = demo.getConstructors();
        try {
            demoClass1 = (DemoClass) con[0].newInstance();
            demoClass2 = (DemoClass) con[1].newInstance(1);
            demoClass4 = (DemoClass) con[2].newInstance(2, "wang");
            demoClass3 = (DemoClass) con[3].newInstance("chen");
            demoClass5 = (DemoClass) con[4].newInstance(3, "li", "win this game");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(demoClass1);
        System.out.println(demoClass2);
        System.out.println(demoClass3);
        System.out.println(demoClass4);
        System.out.println(demoClass5);
    }

    public static void getAllProperties(String name) {
        Class<?> demo = null;
        try {
            demo = Class.forName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }
        System.out.println("===============实现的接口或者父类，公共可访问的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = demo.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + filed1[j].getName() + ";");
        }
    }

    public static void invokeMethod(String name) {
        Class<?> demo = null;
        try {
            demo = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Method method = demo.getMethod("sayHello");
            method.invoke(demo.newInstance());
            method = demo.getMethod("add", int.class, int.class);
            double result = (double) method.invoke(demo.newInstance(), 1, 2);
            System.out.println("method 返回：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
