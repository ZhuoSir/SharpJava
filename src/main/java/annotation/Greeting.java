package annotation;

import java.lang.annotation.Inherited;

/**
 *
 * Created by sunny on 17-6-16.
 */
@Inherited
public @interface Greeting {

    public enum FontColor{  BLUE, RED, GREEN }

    String name();

    FontColor fontColor() default FontColor.GREEN;
}
