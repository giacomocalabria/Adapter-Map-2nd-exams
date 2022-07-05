package myTest;

// JUnit imports
import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

// Adapter package import
import myAdapter.*;

public class TestSuiteCollectionAdapterIterator {
    
    HCollection coll = null;
    HIterator iterator = null;

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
    public void beforeMethod(){
        coll = new CollectionAdapter();    
    }

    /**
     * After JUnit method. It assigns null to m2.
     * It was preferred to not call the clear method as it was target
     * of some test cases, therefore after method cancels any reference to
     * the previous pointed objects, then removed by the Garbage Collector.
     */
    @After
    public void afterMethod(){
        coll = null;
        iterator = null;
    }

    /**
     * AfterClass JUnit static method. Announces the the test end
     * and stops its timer. Prints milliseconds elapsed from the beginning.
     */
    @AfterClass
    public static void afterClassMethod(){
        System.out.println("TestSuiteListAdapterAdapter suite ended. Time elapsed " + (System.currentTimeMillis() - timeStart)  + "ms.");
    }

    //***************************** HASNEXT METHOD ******************************

    /**
     * <p><b>Summary</b>: hasNext and next methods test case.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of
     * an iterator returned from an empty list
     * calling hasNext and next. From the Sommerville:
     * "Choose inputs that force the system to generate all error messages".</p>
     * <p><b>Test Description</b>: an iterator is returned from empty
     * list. iterator.hasNext() should be false, while
     * next() should throw NoSuchElementException.</p>
     * <p><b>Pre-Condition</b>: The list is empty.</p>
     * <p><b>Post-Condition</b>: The list is still empty.</p>
     * <p><b>Expected Results</b>: hasNext returns false, NSEE is thrown.</p>
     */
    @Test (expected = NoSuchElementException.class)
    public void HasNext_Empty_False(){
        iterator = coll.iterator();
        assertEquals("Empty list iterator should not have next.", false, iterator.hasNext());
        iterator.next();
    }

    /**
     * <p><b>Summary</b>: hasNext and next methods test case.
     * List contains 1 element and the test iterate through
     * it.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of 1
     * element in the list. Therefore hasNext should return
     * true while next() should return the only number
     * in the list.</p>
     * <p><b>Test Description</b>: The number 1 is added, and an iterator
     * iterates through the list. After returning the first elements,
     * it has no more next elements.</p>
     * <p><b>Pre-Condition</b>: List contains 1, iterator has next.</p>
     * <p><b>Post-Condition</b>: List contains 1, iterator has not next.</p>
     * <p><b>Expected Results</b>: The first hasNext call returns true,
     * the second returns false.</p>
     */
    @Test
    public void HasNext_Begin1_True()
    {
        coll.add(1);
        iterator = coll.iterator();
        assertEquals("Should have next.", true, iterator.hasNext());
        iterator.next();
        assertEquals("Should not have next.", false, iterator.hasNext());
    }

}