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
 * <strong> Class TestSuiteCollectionAdapter </strong>
 * <p>
 * <br><br><strong>Summary</strong>: The TestSuiteCollectionAdapter class check
 * with its test that the CollectionAdapter's Methods, defined in HCollection interface, works properly.
 * 
 * <br><br><strong>Test Suite Design</strong>: This class contains different test cases 
 * for each method of the HCollection interface.
 * 
 * Test cases include inspection test, modification test and iterator test. 
 * 
 * Each method is tested apart from others.
 * 
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

    /**
     * <p><b>Summary</b>: size method test case. 
     * The test case asserts that an empty map should have an empty values collection
     * witch should have a size of zero and isEmpty call returning true. 
     * The map and the collection is not modified since its creation.</p>
     * 
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 0 size (empty). From the 
     * Sommerville: "Test with sequences of zero lenght." Test based on the trivial but possible
     * state of an empty map and collection.</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map
     * and on the values collection.</p>
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
     * <p><b>Summary</b>: size method test case. 
     * The test case asserts that a map with one element should have an relative values
     * collection with one element wich should have size of 1 and isEmpty call returning false. The map is modified before the asserts.</p>
     * 
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 1 size and not being empty. From the
     * Sommerville: "Test software with sequences which have only a single value"</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map and on the 
     * values collection.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map contains entry 1:159 .</p>
     * <p><b>Expected Results</b>: The size method returns 1 and the isEmpty method returns false.</p>
     */

    @Test
    public void Size_1Element(){

        map1.put(1, 159);
        assertEquals("Empty map does not have size of zero.", 1, map1.size());
        assertEquals("isEmpty did not returned true.", false, map1.isEmpty());
        
        HCollection coll = map1.values();
        assertEquals("Empty map does not have size of one.", 1, coll.size());
        assertEquals("isEmpty did not returned false.", false, coll.isEmpty());
    }

    @Test
    public void Size_5Element(){

        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }

        HCollection coll = map1.values();
        assertEquals("Empty map does not have size of one.", 5, coll.size());
        assertEquals("isEmpty did not returned false.", false, coll.isEmpty());
    }

    @Test
    public void Size_160Element(){

        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        HCollection coll = map1.values();
        assertEquals("Empty map does not have size of one.", 160, coll.size());
        assertEquals("isEmpty did not returned false.", false, coll.isEmpty());
    }

    // ********************* CONTAINS METHOD ******************************

    @Test
    public void Contains_EmptyColl(){
        HCollection coll = map1.values();
        assertEquals("The map contains key 'ci' even if it is empty.", false, coll.contains("ci"));
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

    //****************************** EQUALS METHOD ****************************

    @Test
    public void Equals_1(){
        map1.put(1,1);
        map2.put(1,1);
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();

        assertTrue(coll1.equals(coll2));
    }

    @Test
    public void Equals_Empty_True(){
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        assertTrue(coll1.equals(coll2));
        assertTrue(coll2.equals(coll1));
    }

    @Test
    public void Equals_Reflective(){
        HCollection coll1 = map1.values();
        assertTrue(coll1.equals(coll1));    // Coll is empty
        for(int i = 0; i < 10; i++){
            map1.put(i,i);
        }
        coll1 = map1.values();
        assertTrue(coll1.equals(coll1));    // Coll is not empty, should return true anyways
        for(int i = 0; i < 1000; i++){
            map1.put(i,i);
        }
        coll1 = map1.values();
        assertTrue(coll1.equals(coll1));    // Coll is not empty, should return true anyways
    }

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
     * list, which is a limit case.</p>
     * <p><b>Test Description</b>: Calls clear on the list, then it should be
     * equal to another empty list.</p>
     * <p><b>Pre-Condition</b>: List is empty.</p>
     * <p><b>Post-Condition</b>: List is still empty.</p>
     * <p><b>Expected Results</b>: List is equal to another empty list.</p>
     */
    @Test
    public void Clear_Empty(){
        HCollection coll = map1.values();
        coll.clear();
        assertEquals("List should be empty.", true, coll.isEmpty());
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
    public void Clear_1Element(){
        map1.put(1,1);
        HCollection coll = map1.values();
        coll.clear();
        assertEquals("List should be empty.", true, coll.isEmpty());
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
    public void Clear_0To1000(){
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        HCollection coll = map1.values();
        coll.clear();
        assertEquals("List should be empty.", true, coll.isEmpty());
    }

    // ******************* HASHCODE METHOD ************************************

    /**
     * <p><b>Summary</b>: hashCode test case.
     * Tests the behaviour of hashCode method with different
     * configurations.</p>
     * <p><b>Test Case Design</b>: The same operations are applied to map 1 and 2,
     * so they must have the same elements each time, therefore they are equals.
     * If they are equals they must have the same hashCode.</p>
     * <p><b>Test Description</b>: Different configurations have been tested:
     * empty, {1,1}, {"ciao",164}, {"ciao",0:10}</p>
     * <p><b>Pre-Condition</b>: Maps have same hashCode and they are equal.</p>
     * <p><b>Post-Condition</b>: Maps have same hashCode and they are equal.</p>
     * <p><b>Expected Results</b>: Maps have same hashCode and they are equal.</p>
     */
    @Test
    public void HashCode_Prop(){
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        // Empty map case
        assertEquals("maps should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());

        // One element case
        map1.put(1,1);
        map2.put(1,1);
        coll1 = map1.values();
        coll2 = map2.values();
        assertEquals("maps should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());

        map1.put("ciao",164);
        map2.put("ciao",164);
        coll1 = map1.values();
        coll2 = map2.values();
        assertEquals("maps should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());

        for(int i = 0; i < 10; i++){
            map1.put("ciao" + i,i + 164);
            map2.put("ciao" + i,i + 164);
        }
        coll1 = map1.values();
        coll2 = map2.values();
        assertEquals("maps should be equal.", true, coll1.equals(coll2));
        assertEquals("Hash codes should be equal.", coll1.hashCode(), coll2.hashCode());
    }

    //************************ ADD & ADDALL METHOD **********************************

    @Test (expected = UnsupportedOperationException.class)
    public void Add(){
        HCollection coll1 = map1.values();
        coll1.add(15);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void AddAll(){
        HCollection coll1 = map1.values();
        HCollection coll2 = map2.values();
        coll1.addAll(coll2);
    }

    //***************************** REMOVE METHOD **********************************

    @Test (expected = NullPointerException.class)
    public void Remove_EmptyNullKey_NPException(){
        HCollection coll1 = map1.values();
        coll1.remove(null);
    }

    @Test (expected = NullPointerException.class)
    public void Remove_NullKey_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HCollection coll1 = map1.values();
        coll1.remove(null);
    }

    @Test
    public void Remove_Empty(){
        HCollection coll1 = map1.values();
        assertFalse(coll1.remove(156));
    }

    @Test
    public void Remove_ReturnOldValue(){
        map1.put(44, 987);
        HCollection coll1 = map1.values();
        assertTrue(coll1.remove(987));
        assertFalse(coll1.remove(987));
    }

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
     * <p><b>Test Description</b>: Calls remove 450 times on an map containing
     * 450 mappings, making it empty.</p>
     * <p><b>Pre-Condition</b>: Map contains 450 mappings.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: Map is empty, obviusly its size is 0.</p>
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
        assertEquals("map should be empty.", true, coll1.isEmpty());
    }

    //************************* TOARRAY METHOD *********************************

    /**
     * <p><b>Summary</b>: toArray method test case. 
     * The test case asserts that an empty list
     * should return an empty array on a toArray call.
     * The list is not modified
     * since its creation.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of
     * a toArray call returning an empty array. From the 
     * Sommerville: "Test with sequences of zero lenght."</p>
     * <p><b>Test Description</b>: Test based on the trivial but possible
     * state of an empty list.</p>
     * <p><b>Pre-Condition</b>: The list is empty.</p>
     * <p><b>Post-Condition</b>: The list is still empty.</p>
     * <p><b>Expected Results</b>: The toArray method returns an empty
     * array and therefore its lenght is 0.</p>
     */
    @Test
    public void ToArray_Empty_EmptyArray(){
        HCollection coll1 = map1.values();
        Object[] arr = coll1.toArray();
        assertEquals("Empty list did not return empty array.", arr.length, 0);
    }

    /**
     * <p><b>Summary</b>: toArray method test case.
     * The test case asserts that, after many insertion, an array returned from a
     * toArray call must match the expected list.</p>
     * <p><b>Test Case Design</b>: The test inserts five element and then
     * checks if the list elements matches the inserted elements.
     * From the Sommerville: "Use sequences of different sizes in different tests.". Small
     * size tested here.</p>
     * <p><b>Test Description</b>: Inserts five 1 to the map. Then assertArrayEquals
     * is called.</p>
     * <p><b>Pre-Condition</b>: The list is empty.</p>
     * <p><b>Post-Condition</b>: The list contains {1,1,1,1,1}.</p>
     * <p><b>Expected Results</b>: list1.toArray() returns
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
     * The test adds one element to the list and then call
     * toArray method.</p>
     * <p><b>Test Case Design</b>: Test focuses on toArray behaviour when
     * it has only one element, which is a limit case. From the Sommerville: "Test software
     * with sequences which have only a single value."</p>
     * <p><b>Test Description</b>: Adds one to the list, calls toArray method
     * and checks the array's first element and its size.</p>
     * <p><b>Pre-Condition</b>: The list is empty.</p>
     * <p><b>Post-Condition</b>: The list has one element {1654}.</p>
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
     * The test adds element from 0 (included) to 10 (excluded) to the list and checks the array.</p>
     * <p><b>Test Case Design</b>: The test checks the method behaviour when the
     * argument size is not enough for containing the list's elements, which a
     * special case.</p>
     * <p><b>Test Description</b>: Adds elements from 0 (included) to 10 (excluded) to the list. arr contains the result
     * of toArray method, but exception is being thrown.</p>
     * <p><b>Pre-Condition</b>: The list is empty, arr is empty.</p>
     * <p><b>Post-Condition</b>: The list has 10 elements, arr is still empty.</p>
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
    public void Iterator_HasNext_Emtpy(){
        HIterator iter = map1.values().iterator();
        assertEquals("Empty collection iterator should not have next.", false, iter.hasNext());
        iter.next();
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
    public void Iterator_HasNext_Begin1_True()
    {
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
     * <p><b>Test Case Design</b>: Tests if for a list a remove method
     * throws HIllegalStateException, as no prev or next has been 
     * called, or remove or add have been called after the last call to
     * next or previous</p>
     * <p><b>Test Description</b>: remove is invoked by an iterator instance.</p>
     * <p><b>Pre-Condition</b>: List has 1 element.</p>
     * <p><b>Post-Condition</b>: List still has 1 element.</p>
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
