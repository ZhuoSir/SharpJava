package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 *
 * Created by sunny on 17-6-16.
 */
@Target(ElementType.TYPE)
public @interface Table {

    public String tableName() default "className";

}
