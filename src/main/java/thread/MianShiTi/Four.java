package thread.MianShiTi;

import thread.bean.Student;

/**
 * 自己编写代码,实现生产者-消费者模型功能.内容自由发挥,只需要表达思想.
 *
 * Created by sunny on 2017/5/21.
 */
public class Four {

    static class SetStudent implements Runnable {

        private Student student;
        private int     x;

        public SetStudent(Student student) {
            this.student = student;
        }

        @Override
        public void run() {
            while(true) {
                if (x % 2 == 0) {
                    student.set("chen", 28);
                } else {
                    student.set("wang", 22);
                }
                x++;
            }
        }
    }

    static class GetStudent implements Runnable {

        private Student student;

        public GetStudent(Student student) {
            this.student = student;
        }

        @Override
        public void run() {
            while (true) {
                student.get();
            }
        }
    }

    public static void main(String[] args) {
        Student student = new Student();

        SetStudent setStudent = new SetStudent(student);
        GetStudent getStudent = new GetStudent(student);

        Thread t1 = new Thread(setStudent);
        Thread t2 = new Thread(getStudent);

        t1.start();
        t2.start();
    }
}
