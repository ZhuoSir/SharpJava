package junit.test;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Created by sunny-chen on 2017/4/29.
 */
public class AddPencilsTest {

    String[] bag1 = {"one", "two", "three"};

    String[] bag3 = {"one", "two", "three"};

    @Test
    public void testAddPencils() {
        System.out.println("Inside testAddPencils()");
        Assert.assertArrayEquals(bag1, bag3);
    }

}
