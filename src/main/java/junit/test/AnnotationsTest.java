package junit.test;

import org.junit.*;

import java.util.ArrayList;

/**
 *
 * Created by sunny-chen on 2017/4/29.
 */
public class AnnotationsTest {

    private ArrayList testList;

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        System.out.println("@BeforeClass: onceExecutedBeforeAll");
    }

    @Before
    public void executedBeforeEach() {
        testList = new ArrayList();
        System.out.println("@Before: executeBeforeEach");
    }

    @AfterClass
    public static void onceExecutedAfterAll() {
        System.out.println("@AfterClass: onceExecutedAfterAll");
    }

    @After
    public void executedAfterEach() {
        testList.clear();
        System.out.println("@After: executedAfterEach");
    }

    @Test
    public void OneItemCollection() {
        testList.add("oneItem");
        Assert.assertEquals(1, testList.size());
        System.out.println("@Test: OneItemArrayList");
    }

    @Test
    public void EmptyCollection() {
        Assert.assertTrue(testList.isEmpty());
        System.out.println("@Test: EmptyArrayList");
    }

    @Ignore
    public void executionIgnored() {
        System.out.println("@Ignore: This execution is ignored");
    }
}
