package annotation;

import java.lang.annotation.*;

/**
 * Created by sunny on 17-6-16.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    public enum Color { BLUE, RED, GREEN };

    Color fruitColor() default Color.GREEN;
}
