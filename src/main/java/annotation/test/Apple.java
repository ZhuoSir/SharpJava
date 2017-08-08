package annotation.test;

import annotation.FruitColor;
import annotation.FruitName;
import annotation.FruitProvider;

/**
 * Created by sunny on 17-6-16.
 */
public class Apple {

    @FruitName("apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.GREEN)
    private String appleColor;

    @FruitProvider(id = 3, name = "内蒙古煤矿集团", address = "内蒙古锡林郭勒盟")
    private String appleProvider;

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleName() {
        return appleName;
    }

    public void display() {
        System.out.println("水果的名字是：苹果");
    }
}
