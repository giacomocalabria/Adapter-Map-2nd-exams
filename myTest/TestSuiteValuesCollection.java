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

/**
 * <strong> Class TestSuiteValuesCollection </strong>
 * <p>
 * <br><br><strong>Summary</strong>: The TestSuiteValuesCollection check
 * with its test that the values's Methods, defined in HCollection interface, works properly.
 * 
 * <br><br><strong>Test Suite Design</strong>: This class contains different test cases 
 * for each method of the HCollection interface.
 * 
 * Test cases include inspection test, modification test and iterator test. 
 * 
 * Each method is tested apart from others.</p>
 * 
 * @version 1.0
 * @see TestSuiteMapAdapter
 * @author Giacomo Calabria
 */

public class TestSuiteValuesCollection {
    
    HMap map1 = null;
    HMap map2 = null;

    static long timeStart = 0;
    /**
     * BeforeClass JUnit static method. Announces the the test begin
     * and starts its timer.
     */
    @BeforeClass
    public static void beforeClassMethod(){
        System.out.println("TestSuiteValuesCollection suite started.");
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
        System.out.println("TestSuiteValuesCollection suite ended. Time elapsed " + (System.currentTimeMillis() - timeStart)  + "ms.");
    }

    // ****************************** SIZE ISEMPTY METHOD *************************

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that an empty map should have an empty values collection
     * witch should have a size of zero and isEmpty call returning true. 
     * The map and the collection is not modified since its creation.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 0 size (empty).</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map
     * and on the values Collection.</p>
     * <p><b>Pre-Condition</b>: The map and the values collection is empty.</p>
     * <p><b>Post-Condition</b>: The map and the values collection is still empty.</p>
     * <p><b>Expected Results</b>: The size method returns 0 and the isEmpty method returns true.</p>
     */
    @Test
    public void Size_Empty(){
        assertEquals("Empty map does not have size of zero.", 0, map1.size());
        assertEquals("isEmpty did not returned true.", true, map1.isEmpty());
        HCollection coll = map1.values();
        assertEquals("Empty collection does not have size of zero.", 0, coll.size());
        assertEquals("isEmpty did not returned true.", true, coll.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map with one element should have an relative values
     * collection with one element wich should have size of 1 and isEmpty call returning false. The map is modified before the asserts.</p>
     * 
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 1 size and not being empty.</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map and on the 
     * values collection.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and coll contains entry 1=159 .</p>
     * <p><b>Expected Results</b>: The size method returns 1 and the isEmpty method returns false.</p>
     */

    @Test
    public void Size_1Element(){

        map1.put(1, 159);
        assertEquals("Empty map does not have size of one.", 1, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
        
        HCollection coll = map1.values();
        assertEquals("Empty collection does not have size of one.", 1, coll.size());
        assertEquals("isEmpty did not returned false.", false, coll.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map and its relative values collection with five mappings
     * should have a size of 5 and isEmpty call returning false. 
     * The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 5 size and not being empty. </p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the collection.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the collection contains five mappings.</p>
     * <p><b>Expected Results</b>: The size method returns 5 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_5Element(){
        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }

        HCollection coll = map1.values();
        assertEquals("Empty collection does not have size of one.", 5, coll.size());
        assertEquals("isEmpty did not returned false.", false, coll.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map and its relative values collection with five mappings
     * should have a size of 160 and isEmpty call returning false. 
     * The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 160 size and not being empty. </p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the collection.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the collection contains five mappings.</p>
     * <p><b>Expected Results</b>: The size method returns 160 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_160Element(){

        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        HCollection coll = map1.values();
        assertEquals("Empty collection does not have size of one.", 160, coll.size());
        assertEquals("isEmpty did not returned false.", false, coll.isEmpty());
    }

    // ********************* CONTAINS METHOD ******************************

    /**
     * <p><b>Summary</b>: contains method test case.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of invoking the method in an empty map
     * wich should always return false. </p>
     * <p><b>Test Description</b>: value 'ci' is tested to be present in the map as value. </p>
     * <p><b>Pre-Condition</b>: The map is empty. </p>
     * <p><b>Post-Condition</b>: The map is unchanged, still empty.</p>
     * <p><b>Expected Results</b>: contains returns false.</p>
     */
    @Test
    public void Contains_EmptyColl(){
        HCollection coll = map1.values();
        assertEquals("The collection contains 'ci' even if it is empty.", false, coll.contains("ci"));
    }

    /**
     * <p><b>Summary</b>: contains method test case.</p>
     * <p><b>Test Case Design</b>: Test the case of invoking the method before put and after put mappings on the map</p>
     * <p><b>Test Description</b>: an values is tested to be present in the collection before and after put the mappings in the map. </p>
     * <p><b>Pre-Condition</b>: The map and the collection is empty. </p>
     * <p><b>Post-Condition</b>: The map and the collection contains a mapping: "mio"="ci" </p>
     * <p><b>Expected Results</b>: contains return false when the value is not present, true otherwhise. </p>
     */
    @Test
    public void Contains_1(){
        HCollection coll = map1.values();
        assertEquals("The collection contains 'ci' even if it is empty.", false, coll.contains("ci"));
        map1.put("mio","ci");
        coll = map1.values();
        assertEquals("The collection does not contains 'ci' even if it should.", true, coll.contains("ci"));
    }

    /**
     * <p><b>Summary</b>: contains method test case.
     * <p><b>Test Case Design</b>: The test case checks in various situation its internal state
     * with contains.</p>
     * <p><b>Test Description</b>: Numbers from 50 (included) to 100 (excluded) are added, then checks if
     * elements from 25 to 125 are contained in the collection in 3 different steps.
     * <ul>
     * <li>{25:50} not contained (beginning).</li>
     * <li>{50:100} contained (middle).</li>
     * <li>{100:125} not contained (ending).</li>
     * </ul>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the collection is not empty.</p>
     * <p><b>Expected Results</b>: The collection contains the right elements during execution.
     */
    @Test
    public void Contains_50to100(){
        for (int i = 50; i < 100; i++)
            map1.put(i,i);
        
        HCollection coll = map1.values();
        for (int i = 25; i < 50; i++){
            assertEquals("The collection should NOT include " + i, false, coll.contains(i));
        }
        for (int i = 50; i < 100; i++){
            assertEquals("The collection should include " + i, true, coll.contains(i));
        }
        for (int i = 100; i < 125; i++){
            assertEquals("The collection should NOT include " + i, false, coll.contains(i));
        }
    }

    /**
     * <p><b>Summary</b>: contains method test case.
     * Calling the contains method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a contains method invoke with null key.</p>
     * <p><b>Test Description</b>: contains method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Post-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test(expected = NullPointerException.class)
    public void Contains_Null_NPException(){
        HCollection coll = map1.values();
        coll.contains(null);
    }

    //****************************** EQUALS METHOD ****************************
    /** 
     * <p><b>Summary</b>: equals method test case.</p>
     * <p><b>Test Case Design</b>: equals method is tested with an equal. The returned
     *  values should be true.</p>
     * <p><b>Test Description</b>: Maps is initialized, then equals invoke are asserted</p>
     * <p><b>Pre-Condition</b>: maps contains 1=1</p>
     * <p><b>Post-Condition</b>: Maps and Collections is unchanged.</p>
     * <p><b>Expected Results</b>: The two Collections are equal</p>
     */
    @Test
    public void Equals_1(){
        map1.put(1,1);
        map2.put(1,1);
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();

        assertTrue("The two collection should be equal",coll1.equals(coll2));
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The test case the method behaviour with 2 empty Collection.</p>
     * <p><b>Test Case Design</b>: When both Collections are empty the equals
     * method should return true because an empty Collection is equal to an
     * empty Collection.</p>
     * <p><b>Test Description</b>: Single assert, coll1.equals(coll2) invoked.</p>
     * <p><b>Pre-Condition</b>: Both maps and Collections are empty.</p>
     * <p><b>Post-Condition</b>: Both Collections are empty.</p>
     * <p><b>Expected Results</b>: equals returns true. </p>
     */
    @Test
    public void Equals_Empty_True(){
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        assertTrue("Two empty collection should be equal",coll1.equals(coll2));
        assertTrue("Two empty collection should be equal",coll2.equals(coll1));
    }

     /**
     * <p><b>Summary</b>: equals method test case.
     * The reflective property of equal method is tested.</p>
     * <p><b>Test Case Design</b>: equals method should be reflective,
     * therefore x.equals(x) should always return true. </p>
     * <p><b>Test Description</b>: The test invokes v.equals(v) when
     * coll is empty, when it has 10 elements and when it has 1000 elements.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the collection has 1000 elements. </p>
     * <p><b>Expected Results</b>: The values collection equals itself</p>
     */
    @Test
    public void Equals_Reflective(){
        HCollection coll = map1.values();
        assertTrue(coll.equals(coll));    // Coll is empty
        for(int i = 0; i < 10; i++){
            map1.put(i,i);
        }
        coll = map1.values();
        assertTrue(coll.equals(coll));    // Coll is not empty, should return true anyways
        for(int i = 0; i < 1000; i++){
            map1.put(i,i);
        }
        coll = map1.values();
        assertTrue(coll.equals(coll));    // Coll is not empty, should return true anyways
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The transitive property of equal method is tested.</p>
     * <p><b>Test Case Design</b>: equals method should be transitive</p>
     * <p><b>Test Description</b>: The test invokes coll1.equals(coll2) and coll2.equals(coll3)
     * and coll1.equals(coll3)</p>
     * <p><b>Pre-Condition</b>: The tree maps contains the 30 same mappings</p>
     * <p><b>Post-Condition</b>: The maps and collection is unchanged</p>
     * <p><b>Expected Results</b>: Equals has transitive property.</p>
     */
    @Test
    public void Equals_Transitive(){
        HMap map3 = new MapAdapter();
        for(int i = 0; i < 30; i++){
            map1.put(i, i);
            map2.put(i, i);
            map3.put(i, i);
        }
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        HCollection coll3 = map3.values();

        assertTrue(coll1.equals(coll2));
        assertTrue(coll2.equals(coll3));
        assertTrue("Transitive property is not met.", coll1.equals(coll3));
    }

    //********************** CLEAR METHOD **************************************

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on an already empty
     * collection, which is a limit case.</p>
     * <p><b>Test Description</b>: Calls clear on the collection,, then it should be empty</p>
     * <p><b>Pre-Condition</b>: map and collection is empty.</p>
     * <p><b>Post-Condition</b>: maps and collection is still empty.</p>
     * <p><b>Expected Results</b>: collection is Empty</p>
     */
    @Test
    public void Clear_Empty(){
        HCollection coll = map1.values();
        coll.clear();
        assertEquals("collection should be empty.", true, coll.isEmpty());
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a collection containing 1.</p>
     * <p><b>Test Description</b>: Calls clear on the collection, then it should be empty</p>
     * <p><b>Pre-Condition</b>: The map contains 1=1, the collection contains 1</p>
     * <p><b>Post-Condition</b>: The map and collection is empty.</p>
     * <p><b>Expected Results</b>: collection is Empty</p>
     */
    @Test
    public void Clear_1toEmtpy(){
        map1.put(1,1);
        HCollection coll = map1.values();
        coll.clear();
        assertEquals("collection should be empty.", true, coll.isEmpty());
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a collection containing {0:1000}.</p>
     * <p><b>Test Description</b>: Calls clear on the collection, then it should be empty</p>
     * <p><b>Pre-Condition</b>: The map and the collection contains 1000 elements</p>
     * <p><b>Post-Condition</b>: The map and the collection is empty.</p>
     * <p><b>Expected Results</b>: collection is empty</p>
     */
    @Test
    public void Clear_1000toEmtpy(){
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        HCollection coll = map1.values();
        coll.clear();
        assertEquals("collection should be empty.", true, coll.isEmpty());
    }

    // ******************* HASHCODE METHOD ************************************

    /**
     * <p><b>Summary</b>: hashCode test case.
     * Tests the behaviour of hashCode method with different
     * configurations.</p>
     * <p><b>Test Case Design</b>: The same operations are applied to map 1 and 2,
     * so they must have the same elements each time, and so they must have
     * the same values collection each time, therefore they are equals.
     * If they are equals they must have the same hashCode.</p>
     * <p><b>Test Description</b>: Different configurations have been tested:
     * empty, 1=1, "ciao"=164, "ciao"=0:10</p>
     * <p><b>Pre-Condition</b>: Collections have same hashCode and they are equal.</p>
     * <p><b>Post-Condition</b>: Collections have same hashCode and they are equal.</p>
     * <p><b>Expected Results</b>: Collections have same hashCode and they are equal.</p>
     */
    @Test
    public void HashCode_Prop(){
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        // Empty map case
        assertEquals("Collections should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());

        // One element case
        map1.put(1,1);
        map2.put(1,1);
        coll1 = map1.values();
        coll2 = map2.values();
        assertEquals("Collections should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());

        map1.put("ciao",164);
        map2.put("ciao",164);
        coll1 = map1.values();
        coll2 = map2.values();
        assertEquals("Collections should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());

        for(int i = 0; i < 10; i++){
            map1.put("ciao" + i,i + 164);
            map2.put("ciao" + i,i + 164);
        }
        coll1 = map1.values();
        coll2 = map2.values();
        assertEquals("Collections should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());
    }

    //************************ ADD & ADDALL METHOD **********************************

    /**
     * <p><b>Summary</b>: add method test case.</p>
     * <p><b>Test Case Design</b>: The methoud throws
     * UnsupportedOperationException.</p>
     * <p><b>Test Description</b>: add is invoked.</p>
     * <p><b>Pre-Condition</b>: coll1 is empty.</p>
     * <p><b>Post-Condition</b>: coll1 is empty.</p>
     * <p><b>Expected Results</b>: The add method is not supported.
     * UnsupportedOperationException is thrown.</p>
     */
    @Test (expected = UnsupportedOperationException.class)
    public void Add(){
        HCollection coll1 = map1.values();
        coll1.add(15);
    }
    
    /**
     * <p><b>Summary</b>: addAll method test case.</p>
     * <p><b>Test Case Design</b>: The methoud throws
     * UnsupportedOperationException.</p>
     * <p><b>Test Description</b>: addAll is invoked.</p>
     * <p><b>Pre-Condition</b>: coll1 is empty.</p>
     * <p><b>Post-Condition</b>: coll1 is empty.</p>
     * <p><b>Expected Results</b>: The addAll method is not supported.
     * UnsupportedOperationException is thrown.</p>
     */
    @Test (expected = UnsupportedOperationException.class)
    public void AddAll(){
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        coll1.addAll(coll2);
    }

    //***************************** REMOVE METHOD **********************************

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a remove method invoke with null key.</p>
     * <p><b>Test Description</b>: remove method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Post-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Remove_EmptyNull_NPException(){
        HCollection coll1 = map1.values();
        coll1.remove(null);
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a remove method invoke with null key.</p>
     * <p><b>Test Description</b>: remove method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and Collection are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Remove_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        coll1.remove(null);
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with a valid key in an empty values collection should
     * return always false</p>
     * <p><b>Test Case Design</b>: The test invokes remove method on an emtpy collection
     * wich is a limit case in wich the remove method should always return false</p>
     * <p><b>Test Description</b>: remove method gets called in an empty collection. </p>
     * <p><b>Pre-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Post-Condition</b>: Map and Collection is still empty.</p>
     * <p><b>Expected Results</b>: remove method should be return false</p>
     */
    @Test
    public void Remove_Empty(){
        HCollection coll1 = map1.values();
        assertFalse(coll1.remove(156));
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Tests the remove method feature that
     * returns the old value and after call remove method the collection
     * does not contains the key</p>
     * <p><b>Test Description</b>: Put an mappings into the map. Then assert that
     * he remove the 987 values. Then assert that an second remove with the same 
     * key return null, because its not present yet. </p>
     * <p><b>Pre-Condition</b>: Map has 44=987 mappings and values collection has 987</p>
     * <p><b>Post-Condition</b>: Collection is empty. </p>
     * <p><b>Expected Results</b>:remove method works properly</p>
     */
    @Test
    public void Remove_ReturnOldValue(){
        map1.put(44, 987);
        HCollection coll1 = map1.values();
        assertTrue(coll1.remove(987));
        assertFalse(coll1.remove(987));
    }
    
    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Tests the remove method
	 * in a case where the collection does not contain the key.</p>
     * <p><b>Test Description</b>: remove is invoked with 2 different uncontained values.</p>
     * <p><b>Pre-Condition</b>: Map contains "ciao"="bello". Values collection contains "bello" </p>
     * <p><b>Post-Condition</b>: Map and collection is unchanged.</p>
     * <p><b>Expected Results</b>: removes returns false.</p>
     */
    @Test
    public void Remove_NotPresent(){
        map1.put("ciao","bello");
        HCollection coll1 = map1.values();
        assertFalse(coll1.remove(44));
        assertFalse(coll1.remove("aa"));
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Removes all the elements through remove method
     * to test its behaviour. Note that the limit case of removing the last element
     * is tested too.</p>
     * <p><b>Test Description</b>: Calls remove 450 times on an collection containing
     * 450 elements, making it empty.</p>
     * <p><b>Pre-Condition</b>: Map contains 450 mappings. Values collection contains also 450 elements</p>
     * <p><b>Post-Condition</b>: Map and collection is empty.</p>
     * <p><b>Expected Results</b>: collection is empty, obviusly its size is 0.</p>
     */

    @Test
    public void Remove_450ToEmpty(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        for(int i = 0; i < 450; i++){
            assertTrue(coll1.remove((i+654)*i));
        }
        assertEquals("Size should be 0", 0, coll1.size());
        assertEquals("collection should be empty.", true, coll1.isEmpty());
    }

    //************************* REMOVEALL METHOD *******************************

    /**
     * <p><b>Summary</b>: removeAll method test case.
     * Calling the removeAll method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a removeAll method invoke with null key.</p>
     * <p><b>Test Description</b>: removeAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Post-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void RemoveAll_EmptyNull_NPException(){
        HCollection coll1 = map1.values();
        coll1.removeAll(null);
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.
     * Calling the removeAll method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a removeAll method invoke with null key.</p>
     * <p><b>Test Description</b>: removeAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and Collection are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void RemoveAll_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        coll1.removeAll(null);
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.</p>
     * <p><b>Test Case Design</b>: removeAll method called with
     * empty collection as an argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the values collection and then calls removeAll with an empty collection. 
     * Therefore the maps should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The maps and coll1 contains 45 elements.</p>
     * <p><b>Post-Condition</b>: The maps and coll1 are unchaged.</p>
     * <p><b>Expected Results</b>: The maps are unchanged, therefore removeAll
     * returns false, coll1 is unchanged.</p>
     */
    @Test
    public void RemoveAll_EmptyCollArg(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection collE = map2.values();
        assertTrue(collE.isEmpty());
        assertFalse(coll1.isEmpty());
        assertFalse("removeAll did not remove anything, therefore it should return false.", coll1.removeAll(collE));
        assertFalse(map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.</p>
     * <p><b>Test Case Design</b>: removeAll method called in an
     * empty collection with an nonempty collection as argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the values collection and then calls removeAll on an empty collection. 
     * Therefore the maps should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The maps and coll1 contains 45 elements. collE is empty</p>
     * <p><b>Post-Condition</b>: The maps and coll1 are unchaged. collE is empty</p>
     * <p><b>Expected Results</b>: The maps are unchanged, therefore removeAll
     * returns false, coll1 is unchanged. collE is still empty</p>
     */
    @Test
    public void RemoveAll_EmptyColl(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection collE = map2.values();
        assertTrue(collE.isEmpty());
        assertFalse(coll1.isEmpty());
        assertFalse(map1.isEmpty());
        assertFalse("removeAll did not remove anything, therefore it should return false.", collE.removeAll(coll1));
        assertFalse(map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.</p>
     * <p><b>Test Case Design</b>: removeAll method called between two collections. coll2 includes
     * all elements of the coll1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are all in common with map1 then generates
     * the values collection and then calls removeAll with coll2 as argument. 
     * Therefore the map1 should be changed and map2 should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and coll1 contains 100 elements. coll2 contains 10 elements</p>
     * <p><b>Post-Condition</b>: The map1 and coll1 are unchaged. coll2 is unchanged</p>
     * <p><b>Expected Results</b>: The map1 and coll1 are changed, therefore removeAll
     * returns true, coll2 is unchanged. </p>
     */

    @Test
    public void RemoveAll_10to20Remove100(){
        for(int i = 0; i < 100; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 10; i < 20; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        assertEquals(10, coll2.size());
        assertEquals(100, coll1.size());
        assertTrue(coll1.removeAll(coll2));
        assertEquals(10, coll2.size());
        assertEquals(90, coll1.size());
    }

    //************************* RETAINALL METHOD *******************************

    /**
     * <p><b>Summary</b>: retainAll method test case.
     * Calling the retainAll method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a retainAll method invoke with null key.</p>
     * <p><b>Test Description</b>: retainAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Post-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void RetainAll_EmptyNull_NPException(){
        HCollection coll1 = map1.values();
        coll1.retainAll(null);
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.
     * Calling the retainAll method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a retainAll method invoke with null key.</p>
     * <p><b>Test Description</b>: retainAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and Collection are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void ReretainAll_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        coll1.retainAll(null);
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.</p>
     * <p><b>Test Case Design</b>: retainAll method called with
     * empty collection as an argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the values collection and then calls retainAll with an empty collection. 
     * Therefore the maps should be changed.</p>
     * <p><b>Pre-Condition</b>: The map and coll1 contains 45 elements. collE is empty</p>
     * <p><b>Post-Condition</b>: The map and coll1 are chaged. collE is still empty</p>
     * <p><b>Expected Results</b>: The map and coll1 changed, therefore retainAll
     * returns true, coll1 is changed.</p>
     */
    @Test
    public void RetainAll_EmptyCollArg(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection collE = map2.values();
        assertTrue(collE.isEmpty());
        assertFalse(coll1.isEmpty());
        assertTrue("retainAll did not retain anything, therefore it should return false.", coll1.retainAll(collE));
        assertTrue(map1.isEmpty());
        assertTrue(coll1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.</p>
     * <p><b>Test Case Design</b>: retainAll method called in an
     * empty collection with an nonempty collection as argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the values collection and then calls retainAll on an empty collection. 
     * Therefore the maps should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The maps and coll1 contains 45 elements. collE is empty</p>
     * <p><b>Post-Condition</b>: The maps and coll1 are unchaged. collE is still empty</p>
     * <p><b>Expected Results</b>: The maps are unchanged, therefore retainAll
     * returns false, coll1 is unchanged.</p>
     */
    @Test
    public void RetainAll_EmptyColl(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection collE = map2.values();
        assertTrue(collE.isEmpty());
        assertFalse(coll1.isEmpty());
        assertFalse(map1.isEmpty());
        assertFalse("retainAll did not retain anything, therefore it should return false.", collE.retainAll(coll1));
        assertTrue(collE.isEmpty());
        assertFalse(coll1.isEmpty());
        assertFalse(map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.</p>
     * <p><b>Test Case Design</b>: retainAll method called between two collections. coll2 includes
     * all elements of the coll1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are all in common with map1 then generates
     * the values collection and then calls retainAll with coll2 as argument. 
     * Therefore the map1 should be changed and map2 should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and coll1 contains 100 elements.
     * map2 and coll2 contains 10 elements</p>
     * <p><b>Post-Condition</b>: The map1 and coll1 are chaged. map2 and coll2 is unchanged</p>
     * <p><b>Expected Results</b>: The map1 and coll1 are changed, therefore retainAll
     * returns true, coll2 and map2 is unchanged. </p>
     */
    @Test
    public void RetainAll_10to20Retain100(){
        for(int i = 0; i < 100; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 10; i < 20; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        
        assertEquals(10, coll2.size());
        assertEquals(100, coll1.size());
        assertEquals(100,map1.size());
        assertEquals(10,map2.size());
        
        assertTrue(coll1.retainAll(coll2));
        
        assertEquals(10, coll2.size());
        assertEquals(10, coll1.size());
        assertEquals(10,map1.size());
        assertEquals(10,map2.size());
    }

    //************************** CONTAINSALL METHOD  *****************************

    /**
     * <p><b>Summary</b>: containsAll method test case.
     * Calling the containsAll method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a containsAll method invoke with null key.</p>
     * <p><b>Test Description</b>: containsAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Post-Condition</b>: Map and Collection is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void ContainsAll_EmptyNull_NPException(){
        HCollection coll1 = map1.values();
        coll1.containsAll(null);
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.
     * Calling the containsAll method with null key in this values collection should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a containsAll method invoke with null key.</p>
     * <p><b>Test Description</b>: containsAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Collection contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and Collection are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void ContainsAll_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        coll1.containsAll(null);
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.
     * The method tests if an empty collection contains the elements
     * of another collection, which is obviusly false.</p>
     * <p><b>Test Case Design</b>: The test case tests the limit case of
     * checking an empty collection containing something.</p>
     * <p><b>Test Description</b>: The coll2 contains 45 elements.
     * The containsAll method obviously should return false for
     * any coll's content as the collection is empty.</p>
     * <p><b>Pre-Condition</b>: The coll1 is empty. The coll2 contains 45 elements</p>
     * <p><b>Post-Condition</b>: The colle1 is still empty. The coll2 is unchanged</p>
     * <p><b>Expected Results</b>: The containsAll method return false.</p>
     */
    @Test
    public void ContainsAll_Empty_False(){
        HCollection coll1 = map1.values();
        for(int i = 0; i < 45; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HCollection coll2 = map2.values();

        assertEquals("The method should return false because the collection is empty.", false, coll1.containsAll(coll2)); 
    }

   /**
     * <p><b>Summary</b>: containsAll method test case.
     * The method tests if an empty collection contains the elements
     * of another collection.</p>
     * <p><b>Test Case Design</b>: The test case tests the limit case of
     * checking an empty collection containing an empty collection, which is true, 
     * as the empty subset is the subset of every set, therefore even of the
     * empty set. The tested case is a limit case of containsAll.</p>
     * <p><b>Test Description</b>: The collection is empty.
     * The containsAll method obviously should return true for
     * any coll's content, because the empty subset is the
     * subset of every set.</p>
     * <p><b>Pre-Condition</b>: The collections is empty.</p>
     * <p><b>Post-Condition</b>: The collections is empty.</p>
     * <p><b>Expected Results</b>: The containsAll method return true.</p>
     */
    @Test
    public void ContainsAll_BothEmpty_False(){
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        assertEquals("The method should return true because the collection is empty.", true, coll1.containsAll(coll2)); 
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.</p>
     * <p><b>Test Case Design</b>: containsAll method called between two collections. coll2 includes
     * all elements of the coll1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are all in common with map1 then generates
     * the values collection and then calls containsAll with coll2 as argument. 
     * Therefore the maps and coll should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and coll1 contains 100 elements.
     * map2 and coll2 contains 10 elements</p>
     * <p><b>Post-Condition</b>: the maps and coll should be unchanged.</p>
     * <p><b>Expected Results</b>: the maps and coll should be unchanged. containsAll should return
     * true as coll1 contains coll2 elements. </p>
     */
    @Test
    public void ContainsAll_10to20contains100(){
        for(int i = 0; i < 100; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 10; i < 20; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        
        assertTrue(coll1.containsAll(coll2));
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.</p>
     * <p><b>Test Case Design</b>: containsAll method called between two collections. coll2 includes
     * elements different from the elements of the coll1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are not in common with map1 then generates
     * the values collection and then calls containsAll with coll2 as argument. 
     * Therefore the maps and coll should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and coll1 contains 100 elements.
     * map2 and coll2 contains 10 elements not present in coll1</p>
     * <p><b>Post-Condition</b>: the maps and coll should be unchanged.</p>
     * <p><b>Expected Results</b>: the maps and coll should be unchanged. containsAll should return
     * false as coll1 not contains coll2 elements. </p>
     */
    @Test
    public void ContainsAll_10Notcontains100(){
        for(int i = 0; i < 100; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 290; i < 300; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        
        assertFalse(coll1.containsAll(coll2));
    }

    //************************* TOARRAY METHOD *********************************

    /**
     * <p><b>Summary</b>: toArray method test case. 
     * The test case asserts that an empty collection
     * should return an empty array on a toArray call.
     * The collection is not modified
     * since its creation.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of
     * a toArray call returning an empty array. From the 
     * Sommerville: "Test with sequences of zero lenght."</p>
     * <p><b>Test Description</b>: Test based on the trivial but possible
     * state of an empty collection.</p>
     * <p><b>Pre-Condition</b>: The collection is empty.</p>
     * <p><b>Post-Condition</b>: The collection is still empty.</p>
     * <p><b>Expected Results</b>: The toArray method returns an empty
     * array and therefore its lenght is 0.</p>
     */
    @Test
    public void ToArray_Empty_EmptyArray(){
        HCollection coll1 = map1.values();
        Object[] arr = coll1.toArray();
        assertEquals("Empty collection did not return empty array.", arr.length, 0);
    }

    /**
     * <p><b>Summary</b>: toArray method test case.
     * The test case asserts that, after many insertion, an array returned from a
     * toArray call must match the expected collection.</p>
     * <p><b>Test Case Design</b>: The test inserts five element and then
     * checks if the collection elements matches the inserted elements.
     * From the Sommerville: "Use sequences of different sizes in different tests.". Small
     * size tested here.</p>
     * <p><b>Test Description</b>: Inserts five 1 to the map. Then assertArrayEquals
     * is called.</p>
     * <p><b>Pre-Condition</b>: The collection is empty.</p>
     * <p><b>Post-Condition</b>: The collection contains {1,1,1,1,1}.</p>
     * <p><b>Expected Results</b>: collection1.toArray() returns
     * [1, 1, 1, 1, 1].</p>
     */
    @Test
    public void ToArray_11111_True(){
        for(int i = 0; i < 5; i++)
            map1.put(i,1);
        HCollection coll1 = map1.values();
        Integer[] arr = {1,1,1,1,1};
        assertArrayEquals("Arrays do not match.", arr , coll1.toArray());
    }

    /**
     * <p><b>Summary</b>: toArray test case.
     * The test adds one element to the collection and then call
     * toArray method.</p>
     * <p><b>Test Case Design</b>: Test focuses on toArray behaviour when
     * it has only one element, which is a limit case. From the Sommerville: "Test software
     * with sequences which have only a single value."</p>
     * <p><b>Test Description</b>: Adds one to the collection, calls toArray method
     * and checks the array's first element and its size.</p>
     * <p><b>Pre-Condition</b>: The collection is empty.</p>
     * <p><b>Post-Condition</b>: The collection has one element {1654}.</p>
     * <p><b>Expected Results</b>: The element is stored correctly in the
     * array returned from the method (the array is [1]) and its size is 1.</p>
     */
    @Test
    public void ToArray_ArrayDest_1(){
        Object[] arr = new Object[1];
        map1.put(1,1654);
        HCollection coll1 = map1.values();
        coll1.toArray(arr);
        assertEquals("The array size should be 1.", 1, arr.length);
        assertEquals("The element should be 1654.", 1654, arr[0]);
    }

    /**
     * <p><b>Summary</b>: toArray(HCollection) method test case.</p>
     * <p><b>Test Case Design</b>: The test checks the method behaviour when the
     * argument is null, which a special case.</p>
     * <p><b>Test Description</b>: If the specified array is null excpetion is being thrown. <p>
     * <p><b>Pre-Condition</b>: The map is empty</p>
     * <p><b>Post-Condition</b>: The map is still empty.</p>
     * <p><b>Expected Results</b>: NullPointerExceptio is thrown.</p>
     */

    @Test (expected = NullPointerException.class)
    public void ToArrat_DestNull_NPException(){
        HCollection coll1 = map1.values();
        coll1.toArray(null);
    }

    /**
     * <p><b>Summary</b>: toArray(HCollection) method test case.
     * The test adds element from 0 (included) to 10 (excluded) to the collection and checks the array.</p>
     * <p><b>Test Case Design</b>: The test checks the method behaviour when the
     * argument size is not enough for containing the collection's elements, which a
     * special case.</p>
     * <p><b>Test Description</b>: Adds elements from 0 (included) to 10 (excluded) to the collection. arr contains the result
     * of toArray method, but exception is being thrown.</p>
     * <p><b>Pre-Condition</b>: The collection is empty, arr is empty.</p>
     * <p><b>Post-Condition</b>: The collection has 10 elements, arr is still empty.</p>
     * <p><b>Expected Results</b>: HIllegalArgumentException is thrown.</p>
     */

    @Test (expected = IllegalArgumentException.class)
    public void ToArray_DestSmaller(){
        map1.put(1,1);
        map1.put(2,3);
        Object[] arr = new Object[1];
        HCollection coll1 = map1.values();
        coll1.toArray(arr);
    }

    // ************************** BACKED FEATURE *************************

    @Test
    public void Backed_ClearPut(){
        HCollection coll = map1.values();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Collection should be empty.", true, coll.isEmpty());
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("Collection should be not empty.", false, coll.isEmpty());
        coll.clear();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Collection should be empty.", true, coll.isEmpty());
        
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("Collection should be not empty.", false, coll.isEmpty());
        map1.clear();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Collection should be empty.", true, coll.isEmpty());
    }

    @Test
    public void Backed_putAllRemove(){
        HCollection coll = map1.values();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Collection should be empty.", true, coll.isEmpty());
        for(int i = 0; i < 1000; i ++){
            map2.put(i,i);
        }
        map1.putAll(map2);
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("Collection should be not empty.", false, coll.isEmpty());
        
        HIterator iter = coll.iterator();
        while(iter.hasNext())
            coll.remove(iter.next());
        
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Collection should be empty.", true, coll.isEmpty());
        
        map1.putAll(map2);

        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("Collection should be not empty.", false, coll.isEmpty());

        for(int i = 0; i < 1000; i ++){
            map1.remove(i);
        }

        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Collection should be empty.", true, coll.isEmpty());
    }

    @Test
    public void Backed_PutRemove(){
        HCollection coll = map1.values();
        assertFalse(coll.contains(156));
        map1.put(15,156);
        assertTrue(coll.contains(156));
        map1.remove(15);
        assertFalse(coll.contains(156));
        map1.put(17,44);
        assertTrue(map1.containsValue(44));
        assertTrue(coll.contains(44));
        coll.remove(44);
        assertFalse(map1.containsValue(44));
        assertFalse(coll.contains(44));
    }

    @Test
    public void Backed_IteratorRemove(){
        HCollection coll = map1.values();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Collection should be empty.", true, coll.isEmpty());
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("Collection should be not empty.", false, coll.isEmpty());

        assertEquals("Map should be not empty.", 1000, map1.size());
        assertEquals("Collection should be not empty.", 1000, coll.size());
        
        HIterator iter = coll.iterator();
        while(iter.hasNext()){
            iter.next();
            iter.remove();
        }
        assertEquals("Map should be not empty.", 0, map1.size());
        assertEquals("Collection should be not empty.", 0, coll.size());

        assertEquals("Map should be not empty.", true, map1.isEmpty());
        assertEquals("Collection should be not empty.", true, coll.isEmpty());
    }

    @Test
    public void Backed_removeAll(){

    }

    @Test
    public void Backed_retainAll(){

    }

    //****************************** ITERATOR METHOD *******************************

    /**
     * <p><b>Summary</b>: hasNext and next methods test case.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of
     * an iterator returned from an empty collection
     * calling hasNext and next. From the Sommerville:
     * "Choose inputs that force the system to generate all error messages".</p>
     * <p><b>Test Description</b>: an iterator is returned from empty
     * collection. iterator.hasNext() should be false, while
     * next() should throw NoSuchElementException.</p>
     * <p><b>Pre-Condition</b>: The collection is empty.</p>
     * <p><b>Post-Condition</b>: The collection is still empty.</p>
     * <p><b>Expected Results</b>: hasNext returns false, NSEE is thrown.</p>
     */
    @Test (expected = NoSuchElementException.class)
    public void Iterator_HasNext_Emtpy(){
        HIterator iter = map1.values().iterator();
        assertEquals("Empty collection iterator should not have next.", false, iter.hasNext());
        iter.next();
    }

    /**
     * <p><b>Summary</b>: hasNext and next methods test case.
     * collection contains 1 element and the test iterate through
     * it.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of 1
     * element in the collection. Therefore hasNext should return
     * true while next() should return the only number
     * in the collection.</p>
     * <p><b>Test Description</b>: The number 1 is added, and an iterator
     * iterates through the collection. After returning the first elements,
     * it has no more next elements.</p>
     * <p><b>Pre-Condition</b>: collection contains 1, iterator has next.</p>
     * <p><b>Post-Condition</b>: collection contains 1, iterator has not next.</p>
     * <p><b>Expected Results</b>: The first hasNext call returns true,
     * the second returns false.</p>
     */
    @Test
    public void Iterator_HasNext_Begin1_True(){
        map1.put(1,1);
        HIterator iter = map1.values().iterator();
        assertEquals("Should have next.", true, iter.hasNext());
        iter.next();
        assertEquals("Should not have next.", false, iter.hasNext());
    }

    @Test
    public void Iterator_Next_1(){
        map1.put(1,1);
        HIterator iter = map1.values().iterator();
        assertEquals("Should have 1 as next.", 1, iter.next());
        assertEquals("Should not have next.", false, iter.hasNext());
    }

    @Test (expected = IllegalStateException.class)
    public void Iterator_Remove_Empty_HISE(){
        HIterator iter = map1.values().iterator();
        assertEquals("Should not have next.", false, iter.hasNext());
        iter.remove();
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Test should throw an exception.</p>
     * <p><b>Test Case Design</b>: Tests if for a collection a remove method
     * throws HIllegalStateException, as no prev or next has been 
     * called, or remove or add have been called after the last call to
     * next or previous</p>
     * <p><b>Test Description</b>: remove is invoked by an iterator instance.</p>
     * <p><b>Pre-Condition</b>: collection has 1 element.</p>
     * <p><b>Post-Condition</b>: collection still has 1 element.</p>
     * <p><b>Expected Results</b>: HIllegalStateException thrown.</p>
     */
    @Test (expected = IllegalStateException.class)
    public void Remove_OneElement_HISE(){
        map1.put(1,1);
        HIterator iter = map1.values().iterator();
        iter.remove(); /* Exception throw as no prev or next has been
                            called, or remove or add have been called after
                            the last call to
                            next or previous*/
    }

    //***************** TEST COLLECTION CONSEGNA ******************************

    @Test
	/**
	 * @safe.precondition head con almeno un elemento
	 * @safe.postcondition temp che e' un array contenete gli elementi di head
	 * @safe.summary verifico che temp contenga gli elementi di head
	 */
	public void to_array() {
        map1.put("Collection Adapter","Collection Adapter");
        HCollection head = map1.values();
		Object[] temp = head.toArray();
		boolean size = (temp.length == 1);
		boolean content = (temp[0].equals("Collection Adapter"));
		assertTrue(size && content);
	}
    
    /**
	 * @safe.precondition head con almeno un elemento, a array di destinazione
	 * @safe.postcondition a contiene gli elementi di head
	 * @safe.summary verifico che head contenga gli elementi di head, facendo il confronto con il suo contenuto
	 */
    @Test
    public void to_array_a(){
        map1.put("Collection Adapter","Collection Adapter");
        HCollection head = map1.values();
        Object[] a = new Object[10];
		a = head.toArray(a);
		boolean content = (a[0].equals("Collection Adapter"));
		assertTrue(content);
    }

    @Test
	/**
	 * @safe.precondition ct con almeno un elemento
	 * @safe.postcondition ct senza l'elemento o
	 * @safe.summary test remove(Object o), rimuovo da ct l'elemento o e verifico che la dimensione di ct sia cambiata
	 */
	public void remove_o(){
        map1.put(1,"Collection Adapter");
        map1.put(2,"aaa");
        map1.put(3,"bbb");
        map1.put(4,"ccc");
        
        HCollection ct = map1.values();
		Object o = "Collection Adapter";
		boolean ris = ct.remove(o);
		assertTrue(ris);
	}

    @Test
	/**
	 * @safe.precondition ct con almeno un elemento
	 * @safe.postcondition ct senza gli elementi di Collection c
	 * @safe.summary test removeAll(Collection c), rimuovo da ct tutti gli elementi di c
	 */
	public void remove_all_c() {
        map1.put(1,"Collection Adapter");
        map1.put(2,"aaa");
        map1.put(3,"bbb");
        map1.put(4,"ccc");
        
        HCollection ct = map1.values();
        map2.put(2,"aaa");
        map2.put(3,"bbb");
        map2.put(4,"ccc");
        
        HCollection param = map2.values();
		boolean ris = ct.removeAll(param);
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition ct con almeno un elemento
	 * @safe.postcondition ct intersecato a Collection c
	 * @safe.summary modifica ct facendogli contenere alla fine solo gli elementi presenti anche in c
	 */
	public void retain_all_c() {
        map1.put(1,"Collection Adapter");
        map1.put(2,"aaa");
        map1.put(3,"bbb");
        map1.put(4,"ccc");
        
        HCollection ct = map1.values();
        map2.put(2,"aaa");
        map2.put(3,"bbb");
        map2.put(4,"ccc");
        
        HCollection param = map2.values();
		boolean ris = ct.retainAll(param);
		assertTrue(ris);
	}

    @Test
	/**
	 * @safe.precondition ct contiene tutti gli elementi presenti nella Collection c
	 * @safe.postcondition ris e' true
	 * @safe.summary test metodo containsAll(Collection c) e verifico che gli elementi di c siano presenti in ct
	 */
	public void containsAll_c() {
        map1.put(1,"Collection Adapter");
        map1.put(2,"aaa");
        map1.put(3,"bbb");
        map1.put(4,"ccc");
        
        HCollection ct = map1.values();
        map2.put(2,"aaa");
        map2.put(3,"bbb");
        map2.put(4,"ccc");
        
        HCollection param = map2.values();
		boolean ris = ct.containsAll(param);
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition due oggetti uguali
	 * @safe.postcondition ris vale true
	 * @safe.summary test metodo equals(Object o) e controllo se sono uguali
	 */
	public void equals_o() {
        map1.put(1,"Collection Adapter");
        map1.put(2,"aaa");
        map1.put(3,"bbb");
        map1.put(4,"ccc");
        
        HCollection ct = map1.values();
        map2.put(1,"Collection Adapter");
		map2.put(2,"aaa");
        map2.put(3,"bbb");
        map2.put(4,"ccc");
        
        HCollection temp = map2.values();
		
		boolean ris = ct.equals(temp);
		assertTrue(ris);
	}

}


    /**
     * <p><b>Summary</b>:</p>
     * <p><b>Test Case Design</b>:</p>
     * <p><b>Test Description</b>:</p>
     * <p><b>Pre-Condition</b>:</p>
     * <p><b>Post-Condition</b>:</p>
     * <p><b>Expected Results</b>:</p>
     */

    /**
     * <p><b>Summary</b>:</p>
     * <p><b>Test Suite Design</b>:</p>
     * 
     */