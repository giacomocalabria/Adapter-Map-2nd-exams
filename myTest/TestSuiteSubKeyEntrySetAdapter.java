package myTest;

// JUnit imports
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

// Adapter package import
import myAdapter.*;

public class TestSuiteSubKeyEntrySetAdapter {
    HMap map1 = null;
    HMap map2 = null;

    static long timeStart = 0;
    /**
     * BeforeClass JUnit static method. Announces the the test begin
     * and starts its timer.
     */
    @BeforeClass
    public static void beforeClassMethod(){
        System.out.println("TestSuitemapAdapterAdapter suite started.");
        timeStart = System.currentTimeMillis();
    }

    /**
     * Before JUnit method. It initializes the map used in this suite.
     */
    @Before
    public void beforeMethod(){
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
    public void afterMethod(){
        map1 = null;

    }

    /**
     * AfterClass JUnit static method. Announces the the test end
     * and stops its timer. Prints milliseconds elapsed from the beginning.
     */
    @AfterClass
    public static void afterClassMethod(){
        System.out.println("TestSuitemapAdapterAdapter suite ended. Time elapsed " + (System.currentTimeMillis() - timeStart)  + "ms.");
    }

    //***************************** SIZE METHOD *******************************

    @Test
    public void Size_Empty(){
        HSet key = map1.keySet();
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 0, entry.size());
        assertEquals("isEmpty did not returned false.", true, entry.isEmpty());
        assertEquals("Empty map does not have size of one.", 0, key.size());
        assertEquals("isEmpty did not returned false.", true, key.isEmpty());
    }

    @Test
    public void Size_1Element(){

        map1.put(1, 159);
        HSet key = map1.keySet();
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 1, entry.size());
        assertEquals("isEmpty did not returned false.", false, entry.isEmpty());
        assertEquals("Empty map does not have size of one.", 1, key.size());
        assertEquals("isEmpty did not returned false.", false, key.isEmpty());
    }

    @Test
    public void Size_5Element(){

        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }

        HSet key = map1.keySet();
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 5, entry.size());
        assertEquals("isEmpty did not returned false.", false, entry.isEmpty());
        assertEquals("Empty map does not have size of one.", 5, key.size());
        assertEquals("isEmpty did not returned false.", false, key.isEmpty());
    }

    @Test
    public void Size_160Element(){

        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        HSet key = map1.keySet();
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 160, entry.size());
        assertEquals("isEmpty did not returned false.", false, entry.isEmpty());
        assertEquals("Empty map does not have size of one.", 160, key.size());
        assertEquals("isEmpty did not returned false.", false, key.isEmpty());
    }

    // ********************* CONTAINS METHOD ******************************

    @Test
    public void Contains_EmptyColl(){
        HSet key = map1.keySet();
        HSet entry = map1.entrySet();
        assertEquals("The map contains key 'ci' even if it is empty.", false, key.contains("ci"));
        assertEquals("The map contains key 'ci' even if it is empty.", false, entry.contains(new Object()));
    }

    @Test
    public void Contains_1(){
        HCollection coll = map1.values();
        assertEquals("The map contains key 'ci' even if it is empty.", false, coll.contains("ci"));
        map1.put("mio","ci");
        coll = map1.values();
        assertEquals("The map does not contains key 'ci' even if it should.", true, coll.contains("ci"));
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
            map1.put(i,i);
        
        HCollection coll = map1.values();
        for (int i = 25; i < 50; i++)
        {
            assertEquals("The list should NOT include " + i, false, coll.contains(i));
        }
        for (int i = 50; i < 100; i++)
        {
            assertEquals("The list should include " + i, true, coll.contains(i));
        }
        for (int i = 100; i < 125; i++)
        {
            assertEquals("The list should NOT include " + i, false, coll.contains(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void Contains_Null_NPException(){
        HCollection coll = map1.values();
        coll.contains(null);
    }
}
