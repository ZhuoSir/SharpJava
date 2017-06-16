package annotation;

import java.lang.annotation.*;

/**
 *
 * Created by sunny on 17-6-16.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    public String name() default "fieldName";

    public String setFuncName() default "fieldName";

    public String getFuncName() default "fieldName";

    public boolean defaultValue() default false;

}
