package annotation.test;

import annotation.FruitColor;
import annotation.FruitName;
import annotation.FruitProvider;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 *
 * Created by sunny on 17-6-16.
 */
public class testApp {

    @Test
    public void testApple() {
        String strFruitName = "水果名称：";
        String strFruitColor = "水果颜色：";
        String strFruitProvicer = "供应商信息：";

        Field[] fields = Apple.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName += fruitName.value();
                System.out.println(strFruitName);
            }

            else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor += fruitColor.fruitColor();
                System.out.println(strFruitColor);
            }

            else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvicer +=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }
}
