package junit.suite;

import junit.test.AddPencilsTest;
import junit.test.PrepareMyBagTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * Created by sunny-chen on 2017/4/29.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({PrepareMyBagTest.class, AddPencilsTest.class})
public class SuitTest {



}
