package annotation;

import java.lang.annotation.*;

/**
 * Created by sunny on 17-6-16.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
