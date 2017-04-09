package lombok;

import lombok.bean.Demo;

/**
 * Created by sunny-chen on 2017/4/9.
 */
public class App {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.setName("chenzhuo");
        demo.setNumber(1);
        demo.setAddress("大姐夫的附加费的解放路");

        System.out.println(demo.toString());
    }
}
