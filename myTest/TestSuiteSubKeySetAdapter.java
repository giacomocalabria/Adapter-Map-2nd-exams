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

public class TestSuiteSubKeySetAdapter {
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
        assertEquals("Empty map does not have size of one.", 0, key.size());
        assertEquals("isEmpty did not returned false.", true, key.isEmpty());
    }

    @Test
    public void Size_1Element(){

        map1.put(1, 159);
        HSet key = map1.keySet();
        assertEquals("Empty map does not have size of one.", 1, key.size());
        assertEquals("isEmpty did not returned false.", false, key.isEmpty());
    }

    @Test
    public void Size_5Element(){

        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }

        HSet key = map1.keySet();
        assertEquals("Empty map does not have size of one.", 5, key.size());
        assertEquals("isEmpty did not returned false.", false, key.isEmpty());
    }

    @Test
    public void Size_160Element(){

        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        HSet key = map1.keySet();
        assertEquals("Empty map does not have size of one.", 160, key.size());
        assertEquals("isEmpty did not returned false.", false, key.isEmpty());
    }

    // ********************* CONTAINS METHOD ******************************

    @Test
    public void Contains_EmptyColl(){
        HSet key = map1.keySet();
        assertEquals("The map contains key 'ci' even if it is empty.", false, key.contains("ci"));
    }

    @Test
    public void Contains_1(){
        HSet key = map1.keySet();
        assertEquals("The map contains key 15 even if it is empty.", false, key.contains(15));
        map1.put(15,15);
        assertEquals("The map does not contains key 15 even if it should.", true, key.contains(15));
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
        
        HSet key = map1.keySet();
        for (int i = 25; i < 50; i++){
            assertEquals("The list should NOT include " + i, false, key.contains(i));
        }
        for (int i = 50; i < 100; i++){
            assertEquals("The list should include " + i, true, key.contains(i));
        }
        for (int i = 100; i < 125; i++){
            assertEquals("The list should NOT include " + i, false, key.contains(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void Contains_Key_Null_NPException(){
        HSet key = map1.keySet();
        key.contains(null);
    }

    //****************************** EQUALS METHOD ****************************

    @Test
    public void Equals_1(){
        map1.put(1,1);
        map2.put(1,1);
        HSet key1 = map1.keySet();
        HSet key2 = map2.keySet();

        assertTrue(key1.equals(key2));
    }

    @Test
    public void Equals_Empty_True(){
        HSet key1 = map1.keySet();
        HSet key2 = map2.keySet();
        assertTrue(key1.equals(key2));
        assertTrue(key2.equals(key1));
    }

    @Test
    public void Equals_Reflective(){
        HSet key1 = map1.keySet();

        assertTrue(key1.equals(key1));    // Set is empty
        
        for(int i = 0; i < 10; i++){
            map1.put(i,i);
        }
        
        assertTrue(key1.equals(key1));    // Set is not empty, should return true anyways
        
        for(int i = 0; i < 1000; i++){
            map1.put(i,i);
        }
        
        assertTrue(key1.equals(key1));    // Set is empty
    }

    @Test
    public void Equals_Transitive(){
        HMap map3 = new MapAdapter();
        for(int i = 0; i < 30; i++){
            map1.put(i, i);
            map2.put(i, i);
            map3.put(i, i);
        }
        HSet key1 = map1.keySet();
        HSet key2 = map2.keySet();
        HSet key3 = map3.keySet();

        assertTrue(key1.equals(key2));
        assertTrue(key2.equals(key3));
        assertTrue("Transitive property is not met.", key1.equals(key3));
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
        HSet key = map1.keySet();
        key.clear();
        assertEquals("List should be empty.", true, key.isEmpty());
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
        HSet key = map1.keySet();
        key.clear();
        assertEquals("List should be empty.", true, key.isEmpty());
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
        HSet key = map1.keySet();
        key.clear();
        assertEquals("List should be empty.", true, key.isEmpty());
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
    public void HashCode_Prop_Key(){
        HSet key1 = map1.keySet();
        HSet key2 = map2.keySet();
        // Empty map case
        assertEquals("maps should be equal.", true, key1.equals(key2));
        assertEquals("Hash codes should be equal.", key1.hashCode(), key2.hashCode());

        // One element case
        map1.put(1,1);
        map2.put(1,1);
        key1 = map1.keySet();
        key2 = map2.keySet();
        assertEquals("maps should be equal.", true, key1.equals(key2));
        assertEquals("Hash codes should be equal.", key1.hashCode(), key2.hashCode());

        map1.put("ciao",164);
        map2.put("ciao",164);
        key1 = map1.keySet();
        key2 = map2.keySet();
        assertEquals("maps should be equal.", true, key1.equals(key2));
        assertEquals("Hash codes should be equal.", key1.hashCode(), key2.hashCode());

        for(int i = 0; i < 10; i++){
            map1.put("ciao" + i,i + 164);
            map2.put("ciao" + i,i + 164);
        }
        key1 = map1.keySet();
        key2 = map2.keySet();
        assertEquals("maps should be equal.", true, key1.equals(key2));
        assertEquals("Hash codes should be equal.", key1.hashCode(), key2.hashCode());
    }

    //************************ ADD & ADDALL METHOD **********************************

    @Test (expected = UnsupportedOperationException.class)
    public void Add_key(){
        HSet key1 = map1.keySet();
        key1.add(15);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void AddAll_key(){
        HSet key1 = map1.keySet();
        HSet key2 = map2.keySet();
        key1.addAll(key2);
    }

    //***************************** REMOVE METHOD **********************************

    @Test (expected = NullPointerException.class)
    public void Remove_EmptyNullKey_NPException(){
        HSet key1 = map1.keySet();
        key1.remove(null);
    }

    @Test (expected = NullPointerException.class)
    public void Remove_NullKey_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.keySet();
        key1.remove(null);
    }

    @Test
    public void Remove_Empty(){
        HSet key1 = map1.keySet();
        assertFalse(key1.remove(156));
    }

    @Test
    public void Remove_2Times(){
        map1.put(44, 987);
        HSet key1 = map1.keySet();
        assertTrue(key1.remove(44));
        assertFalse(key1.remove(44));
    }

    @Test
    public void Remove_NotPresent(){
        map1.put("ciao","bello");
        HSet key1 = map1.keySet();
        assertFalse(key1.remove(44));
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
        HSet key1 = map1.keySet();
        for(int i = 0; i < 450; i++){
            assertTrue(key1.remove(i*i*i));
        }
        assertEquals("Size should be 0", 0, key1.size());
        assertEquals("map should be empty.", true, key1.isEmpty());
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
        HSet key1 = map1.keySet();
        Object[] arr = key1.toArray();
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
     * <p><b>Post-Condition</b>: The list contains {1}.</p>
     * <p><b>Expected Results</b>: list1.toArray() returns [1].</p>
     */
    @Test
    public void ToArray_1_True(){
        for(int i = 0; i < 5; i++)
            map1.put(1,i);
        HSet key1 = map1.keySet();
        Integer[] arr = {1};
        assertArrayEquals("Arrays do not match.", arr , key1.toArray());
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
        map1.put(145,1654);
        HSet key1 = map1.keySet();
        key1.toArray(arr);
        assertEquals("The array size should be 1.", 1, arr.length);
        assertEquals("The element should be 145.", 145, arr[0]);
        
        Object[] arr1 = new Object[1];
        HSet entry1 = map1.entrySet();
        entry1.toArray(arr1);
        HMap.HEntry em = new MapEntryAdapter(145);
        em.setValue(1654);
        assertEquals("The array size should be 1.", 1, arr1.length);
        assertEquals("The element should be 1654.", em, arr1[0]);
    }

    /**
     * <p><b>Summary</b>: toArray(HSet) method test case.</p>
     * <p><b>Test Case Design</b>: The test checks the method behaviour when the
     * argument is null, which a special case.</p>
     * <p><b>Test Description</b>: If the specified array is null excpetion is being thrown. <p>
     * <p><b>Pre-Condition</b>: The map is empty</p>
     * <p><b>Post-Condition</b>: The map is still empty.</p>
     * <p><b>Expected Results</b>: NullPointerExceptio is thrown.</p>
     */

    @Test (expected = NullPointerException.class)
    public void ToArray_DestNullKey_NPException(){
        HSet key1 = map1.keySet();
        key1.toArray(null);
    }
    /**
     * <p><b>Summary</b>: toArray(HSet) method test case.</p>
     * <p><b>Test Case Design</b>: The test checks the method behaviour when the
     * argument is null, which a special case.</p>
     * <p><b>Test Description</b>: If the specified array is null excpetion is being thrown. <p>
     * <p><b>Pre-Condition</b>: The map is empty</p>
     * <p><b>Post-Condition</b>: The map is still empty.</p>
     * <p><b>Expected Results</b>: NullPointerExceptio is thrown.</p>
     */

    @Test (expected = NullPointerException.class)
    public void ToArray_DestNullEntry_NPException(){
        HSet entry1 = map1.entrySet();
        entry1.toArray(null);
    }

    /**
     * <p><b>Summary</b>: toArray(HSet) method test case.
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
        HSet key1 = map1.keySet();
        key1.toArray(arr);
    }

    // ************************** BACKED FEATURE *************************

    @Test
    public void Backed_ClearPut(){
        HSet coll = map1.keySet();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, coll.isEmpty());
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("set should be not empty.", false, coll.isEmpty());
        coll.clear();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, coll.isEmpty());
        
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("set should be not empty.", false, coll.isEmpty());
        map1.clear();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, coll.isEmpty());
    }

    @Test
    public void Backed_putAllRemove(){
        HSet coll = map1.keySet();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, coll.isEmpty());
        for(int i = 0; i < 1000; i ++){
            map2.put(i,i);
        }
        map1.putAll(map2);
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("set should be not empty.", false, coll.isEmpty());
        
        HIterator iter = coll.iterator();
        while(iter.hasNext())
            coll.remove(iter.next());
        
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, coll.isEmpty());
        
        map1.putAll(map2);

        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("set should be not empty.", false, coll.isEmpty());

        for(int i = 0; i < 1000; i ++){
            map1.remove(i);
        }

        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, coll.isEmpty());
    }

    @Test
    public void Backed_PutRemove(){
        HSet key1 = map1.keySet();
        assertFalse(key1.contains(156));
        map1.put(15,156);
        assertTrue(key1.contains(15));
        map1.remove(15);
        assertFalse(key1.contains(15));
        map1.put(17,44);
        assertTrue(map1.containsKey(17));
        assertTrue(key1.contains(17));
        key1.remove(17);
        assertFalse(map1.containsValue(17));
        assertFalse(key1.contains(17));
    }

    @Test
    public void Backed_KeyIteratorRemove(){

        // SET
        HSet key = map1.keySet();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, key.isEmpty());
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("set should be not empty.", false, key.isEmpty());

        assertEquals("Map should be not empty.", 1000, map1.size());
        assertEquals("set should be not empty.", 1000, key.size());
        
        HIterator iter = key.iterator();
        while(iter.hasNext()){
            iter.next();
            iter.remove();
        }

        assertEquals("Map should be empty.", 0, map1.size());
        assertEquals("set should be empty.", 0, key.size());
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, key.isEmpty());
    }

    @Test
    public void Backed_EntryIteratorRemove(){
        HSet entry = map1.entrySet();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, entry.isEmpty());
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        assertEquals("Map should be not empty.", false, map1.isEmpty());
        assertEquals("set should be not empty.", false, entry.isEmpty());

        assertEquals("Map should be not empty.", 1000, map1.size());
        assertEquals("set should be not empty.", 1000, entry.size());
        
        HIterator iter = entry.iterator();
        while(iter.hasNext()){
            iter.next();
            iter.remove();     
        }
        
        assertEquals("set should be empty.", 0, entry.size());
        assertEquals("Map should be empty.", 0, map1.size());
        
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("set should be empty.", true, entry.isEmpty());
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
        HIterator iter = map1.keySet().iterator();
        assertEquals("Empty set iterator should not have next.", false, iter.hasNext());
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
        HIterator iter = map1.keySet().iterator();
        assertEquals("Should have next.", true, iter.hasNext());
        iter.next();
        assertEquals("Should not have next.", false, iter.hasNext());
    }

    @Test
    public void Iterator_Next_1(){
        map1.put(1,1);
        HIterator iter = map1.keySet().iterator();
        assertEquals("Should have 1 as next.", 1, iter.next());
        assertEquals("Should not have next.", false, iter.hasNext());
    }

    @Test (expected = IllegalStateException.class)
    public void Iterator_Remove_Empty_HISE(){
        HIterator iter = map1.keySet().iterator();
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
        HIterator iter = map1.keySet().iterator();
        iter.remove(); /* Exception throw as no prev or next has been
                            called, or remove or add have been called after
                            the last call to
                            next or previous*/
    }
}
