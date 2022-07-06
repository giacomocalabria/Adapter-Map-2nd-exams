package myTest;

// JUnit imports
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Adapter package import
import myAdapter.*;

public class TestSuiteSubValuesCollectionAdapter {
    
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
        HCollection coll = map1.values();
        assertEquals("Empty collection does not have size of zero.", 0, coll.size());
        assertEquals("isEmpty did not returned true.", true, coll.isEmpty());
    }

    @Test
    public void Size_1Element(){

        map1.put(1, 159);
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

}
