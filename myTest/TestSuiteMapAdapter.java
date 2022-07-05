package myTest;

// JUnit imports
import org.junit.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

// Adapter package import
import myAdapter.*;

// Other imports
import java.util.Arrays;

public class TestSuiteMapAdapter {
    
    HMap map1 = null;
    HMap map2 = null;

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
        map1 = new MapAdapter();    
        map2 = new MapAdapter();    
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
        map1 = null;
        map2 = null;
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

        assertEquals("Empty list does not have size of zero.", 0, map1.size());
        assertEquals("isEmpty did not returned true.", true, map1.isEmpty());
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
    public void Size_OneElement_1(){

        map1.put(1, 159);
        assertEquals("Empty list does not have size of one.", 1, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
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
    public void Size_5Element_5(){

        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }
        assertEquals("Empty list does not have size of one.", 5, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
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
    public void Size_160Element_160(){

        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        assertEquals("Empty list does not have size of one.", 160, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
    }

    // ************************** CONTAINS KEY & VALUE METHOD **************************************

    @Test
    public void Contains_1Key_and2(){

        assertEquals("The map contains key 'ci' even if it is empty.", false, map1.containsKey("ci"));
        map1.put("ci",15);
        assertEquals("The map does not contains key 'ci' even if it should.", true, map1.containsKey("ci"));
        assertEquals("The map should not contain key 'ao'.", false, map1.containsKey("ao"));
        map1.put("ao",465);
        assertEquals("The map does not contains key 'ci' even if it should.",true,  map1.containsKey("ci"));
        assertEquals("The map does not contains key 'ao' even if it should.",true, map1.containsKey("ao"));
    }

    @Test
    public void Contains_1Value_and2(){

        assertEquals("The map contains value 154 even if it is empty.", false, map1.containsValue(154));
        map1.put("mio",154);
        assertEquals("The map does not contains value 154 even if it should.", true, map1.containsValue(154));
        assertEquals("The map should not contain value 15.", false, map1.containsValue(115));
        map1.put("amio",15);
        assertEquals("The map does not contains value 154 even if it should.",true,  map1.containsValue(154));
        assertEquals("The map does not contains value 15 even if it should.",true, map1.containsValue(15));
    }

    @Test
    public void Contains_Key_50to100()
    {
        for (int i = 50; i < 100; i++)
            map1.put(i,i+164);
        for (int i = 25; i < 50; i++)
        {
            assertEquals("The map should NOT include " + i, false, map1.containsKey(i));
        }
        for (int i = 50; i < 100; i++)
        {
            assertEquals("The map should include " + i, true, map1.containsKey(i));
        }
        for (int i = 100; i < 125; i++)
        {
            assertEquals("The map should NOT include " + i, false, map1.containsKey(i));
        }
    }

    @Test
    public void Contains_Value_50to100()
    {
        for (int i = 50; i < 100; i++)
            map1.put(i+44,i);
        for (int i = 25; i < 50; i++)
        {
            assertEquals("The map should NOT include " + i, false, map1.containsValue(i));
        }
        for (int i = 50; i < 100; i++)
        {
            assertEquals("The map should include " + i, true, map1.containsValue(i));
        }
        for (int i = 100; i < 125; i++)
        {
            assertEquals("The map should NOT include " + i, false, map1.containsValue(i));
        }
    }

    @Test
    public void Contains_KeyAndValue_1and3(){
        assertEquals("The map contains entry 654:15 even if it is empty.", false, map1.containsValue(15) && map1.containsKey(654));
        map1.put(654,15);

        assertEquals("The map does not contains entry 654:15 even if it should.", true, map1.containsValue(15) && map1.containsKey(654));
        assertEquals("The map contains entry 155:44 even if it is empty.", false, map1.containsValue(44) && map1.containsKey(155));

        map1.put(654,155);
        map1.put(155,44);

        assertEquals("The map does not contains entry 155:44 even if it should.", true, map1.containsValue(44) && map1.containsKey(155));
    }

    //****************************** EQUALS METHOD ****************************

    

}
