package myTest;

// JUnit imports
import org.junit.*;
import static org.junit.Assert.assertEquals;

// Adapter package import
import myAdapter.*;

public class TestSuiteCollectionAdapter {
    
    HCollection coll1 = null;
    HCollection coll2 = null;

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
        coll1 = new CollectionAdapter();    
        coll2 = new CollectionAdapter();    
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
        coll1 = null;
        coll2 = null;
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

        assertEquals("Empty list does not have size of zero.", 0, coll1.size());
        assertEquals("isEmpty did not returned true.", true, coll1.isEmpty());
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

        coll1.add(159);
        assertEquals("Empty list does not have size of one.", 1, coll1.size());
        assertEquals("isEmpty did not returned false.", false, coll1.isEmpty());
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
            coll1.add(i+16);
        }
        assertEquals("Empty list does not have size of one.", 5, coll1.size());
        assertEquals("isEmpty did not returned false.", false, coll1.isEmpty());
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
            coll1.add(i+45);
        }
        assertEquals("Empty list does not have size of one.", 160, coll1.size());
        assertEquals("isEmpty did not returned false.", false, coll1.isEmpty());
    }

    //******************************* CONTAINS METHOD ***************************

    @Test
    public void Contains_EmptyColl(){
        assertEquals("The map contains key 'ci' even if it is empty.", false, coll1.contains("ci"));
    }

    @Test
    public void Contains_1and2(){

        assertEquals("The map contains key 'ci' even if it is empty.", false, coll1.contains("ci"));
        coll1.add("ci");
        assertEquals("The map does not contains key 'ci' even if it should.", true, coll1.contains("ci"));
        assertEquals("The map should not contain key 'ao'.", false, coll1.contains("ao"));
        coll1.add("ao");
        assertEquals("The map does not contains key 'ci' even if it should.",true, coll1.contains("ci"));
        assertEquals("The map does not contains key 'ao' even if it should.",true, coll1.contains("ao"));
    }

    /**
     * <p><b>Summary</b>: contains method test case.
     * <p><b>Test Case Design</b>: The test case checks in various situation its internal state
     * with contains, changing through execution.</p>
     * <p><b>Test Description</b>: Numbers from 50 (included) to 100 (excluded) are added, then checks if
     * elements from 25 to 125 are contained in the list in 3 different steps.
     * <ul>
     * <li>{25:50} not contained (beginning).</li>
     * <li>{50:100} contained (middle).</li>
     * <li>{100:125} not contained (ending).</li>
     * </ul>
     * <p><b>Pre-Condition</b>: The list is empty.</p>
     * <p><b>Post-Condition</b>: The list is not empty.</p>
     * <p><b>Expected Results</b>: The list contains the right elements during
     * execution. In particular:
     * <ul>
     * <li>{25:50} not contained (beginning).</li>
     * <li>{50:100} contained (middle).</li>
     * <li>{100:125} not contained (ending).</li>
     * </ul>
     */
    @Test
    public void Contains_50to100(){
        for (int i = 50; i < 100; i++)
            coll1.add(i);
        for (int i = 25; i < 50; i++)
        {
            assertEquals("The list should NOT include " + i, false, coll1.contains(i));
        }
        for (int i = 50; i < 100; i++)
        {
            assertEquals("The list should include " + i, true, coll1.contains(i));
        }
        for (int i = 100; i < 125; i++)
        {
            assertEquals("The list should NOT include " + i, false, coll1.contains(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void Contains_Null_NPException(){
        coll1.contains(null);
    }

    //********************** CLEAR METHOD **************************************

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on an already empty
     * list, which is a limit case.</p>
     * <p><b>Test Description</b>: Calls clear on the list, then it should be
     * equal to another empty list.</p>
     * <p><b>Pre-Condition</b>: List is empty.</p>
     * <p><b>Post-Condition</b>: List is still empty.</p>
     * <p><b>Expected Results</b>: List is equal to another empty list.</p>
     */
    @Test
    public void Clear_Empty()
    {
        coll1.clear();
        assertEquals("List should be empty.", true, coll1.equals(coll2));
        assertEquals("List should be empty.", true, coll1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a list containing 0.</p>
     * <p><b>Test Description</b>: Calls clear on the list, then it should be
     * equal to another empty list.</p>
     * <p><b>Pre-Condition</b>: List contains 0.</p>
     * <p><b>Post-Condition</b>: List is empty.</p>
     * <p><b>Expected Results</b>: List is equal to another empty list.</p>
     */
    @Test
    public void Clear_1Element()
    {
        coll1.add(0);
        coll1.clear();
        assertEquals("List should be empty.", true, coll1.equals(coll2));
        assertEquals("List should be empty.", true, coll1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a list containing {0:1000}.</p>
     * <p><b>Test Description</b>: Calls clear on the list, then it should be
     * equal to another empty list.</p>
     * <p><b>Pre-Condition</b>: List contains {0:1000}.</p>
     * <p><b>Post-Condition</b>: List is empty.</p>
     * <p><b>Expected Results</b>: List is equal to another empty list.</p>
     */
    @Test
    public void Clear_0To1000()
    {
        for(int i = 0; i < 1000; i ++){
            coll1.add(i);
        }
        coll1.clear();
        assertEquals("List should be empty.", true, coll1.equals(coll2));
        assertEquals("List should be empty.", true, coll1.isEmpty());
    }
}