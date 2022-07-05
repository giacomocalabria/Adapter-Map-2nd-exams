package myTest;

// JUnit imports
import org.junit.*;
import static org.junit.Assert.assertEquals;

// Adapter package import
import myAdapter.*;

public class TestSuiteSetAdapter {
    
    HSet set1 = null;
    HSet set2 = null;

    static long timeStart = 0;
    /**
     * BeforeClass JUnit static method. Announces the the test begin
     * and starts its timer.
     */
    @BeforeClass
    public static void beforeClassMethod()
    {
        System.out.println("TestSuiteListAdapterAdapter suite started.");
        timeStart = System.currentTimeMillis();
    }

    /**
     * Before JUnit method. It initializes the map used in this suite.
     */
    @Before
    public void beforeMethod()
    {
        set1 = new SetAdapter();    
        set2 = new SetAdapter();    
    }

    /**
     * After JUnit method. It assigns null to m2.
     * It was preferred to not call the clear method as it was target
     * of some test cases, therefore after method cancels any reference to
     * the previous pointed objects, then removed by the Garbage Collector.
     */
    @After
    public void afterMethod()
    {
        set1 = null;
        set2 = null;
    }

    /**
     * AfterClass JUnit static method. Announces the the test end
     * and stops its timer. Prints milliseconds elapsed from the beginning.
     */
    @AfterClass
    public static void afterClassMethod()
    {
        System.out.println("TestSuiteListAdapterAdapter suite ended. Time elapsed " + (System.currentTimeMillis() - timeStart)  + "ms.");
    }

    // ****************************** SIZE METHOD *****************************

    /**
     * <p><b>Summary</b>: size method test case. 
     * The test case asserts that an empty map should have a size of zero and
     * isEmpty call returning true. The map is not modified since its creation.</p>
     * 
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 0 size (empty). From the 
     * Sommerville: "Test with sequences of zero lenght." Test based on the trivial but possible
     * state of an empty map.</p>
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map is still empty.</p>
     * <p><b>Expected Results</b>: The size method returns 0 and the isEmpty method returns true.</p>
     */
    @Test
    public void Size_Empty_0(){

        assertEquals("Empty list does not have size of zero.", 0, set1.size());
        assertEquals("isEmpty did not returned true.", true, set1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size method test case. 
     * The test case asserts that a map with one element should have a size of 1 and
     * isEmpty call returning false. The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 1 size and not being empty. From the
     * Sommerville: "Test software with sequences which have only a single value"</p>
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map contains entry 1:159 .</p>
     * <p><b>Expected Results</b>: The size method returns 1 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_1Element(){

        set1.add(159);
        assertEquals("Empty list does not have size of one.", 1, set1.size());
        assertEquals("isEmpty did not returned false.", false, set1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size method test case. 
     * The test case asserts that a map with five elements should have a size of 5 and
     * isEmpty call returning false. The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 5 size and not being empty. From the
     * Sommerville: "Test software with sequences which have only a single value"</p>
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map contains five elements .</p>
     * <p><b>Expected Results</b>: The size method returns 5 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_5Element(){

        for(int i = 0; i < 5; i++){
            set1.add(i+16);
        }
        assertEquals("Empty list does not have size of one.", 5, set1.size());
        assertEquals("isEmpty did not returned false.", false, set1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size method test case. 
     * The test case asserts that a map with 160 elements should have a size of 160 and
     * isEmpty call returning false. The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 160 size and not being empty. From the
     * Sommerville: "Test software with sequences which have only a single value"</p>
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map contains 160 elements .</p>
     * <p><b>Expected Results</b>: The size method returns 160 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_160Element(){

        for(int i = 0; i < 160; i++){
            set1.add(i+45);
        }
        assertEquals("Empty list does not have size of one.", 160, set1.size());
        assertEquals("isEmpty did not returned false.", false, set1.isEmpty());
    }


}
