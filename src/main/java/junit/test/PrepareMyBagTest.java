package junit.test;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Created by sunny-chen on 2017/4/29.
 */
public class PrepareMyBagTest {

   String[] bag1 = {"one", "two", "three"};

   String[] bag2 = {"one", "two", "three"};

   @Test
   public void testPrepareMyBag() {
       System.out.println("Inside testPrepareMyBag()");
       Assert.assertArrayEquals(bag1, bag2);
   }

}
