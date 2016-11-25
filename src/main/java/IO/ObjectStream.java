package IO;

import java.io.*;

/**
 * Created by sunny-chen on 16/11/26.
 */
public class ObjectStream {

    static final String path = "/Users/sunny-chen/Documents/JAVA/SharpJava/src/main/java/IO/file/text.txt";

    public static void main(String[] args) {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(path));
            inputStream = new ObjectInputStream(new FileInputStream(path));
            outputStream.writeObject(new student("chen", 18));
            outputStream.writeObject(new student("wang",20));
            outputStream.writeObject(new student("zhang",22));
            for (int i =0; i<3;i++) {
                System.out.println(inputStream.readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class student implements Serializable {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + "]";
        }
    }
}
