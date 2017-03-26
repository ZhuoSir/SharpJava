package basic;

import basic.bean.Animal;
import basic.bean.Human;

/**
 * Created by sunny-chen on 16/12/7.
 */
public class InitApp {

    public static void main(String[] args) {
        Animal human = new Human();

        human.drink();
    }
}
