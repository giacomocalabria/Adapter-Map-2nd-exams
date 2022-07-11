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
 * <strong> Class TestSuiteEntrySet </strong>
 * <p>
 * <br><br><strong>Summary</strong>: The TestSuiteEntrySet provides test on entrySet set methods.
 * It offer different type of test in differente case scanario, in order to test their correct behaviour.
 * There are several section for each method (or group of feature) contains 
 * the test cases ideated by me. 
 * 
 * Note that all the test contained in the TestSet.java file 
 * assigned by the Professor, are just included in this test suite in JUnit format.
 * 
 * In the TestSet.java file there are some test with add method (unsupported in entrySet) and some test of
 * the iterator removal, witch are included in the last section of this test suite. Also toString method is tested.
 * 
 * <br><br><strong>Test Suite Design</strong>: This test suite contains fine-grained different test cases 
 * for each method of the HSet interface in order to individuate errors in HSet methods
 * 
 * Test cases include inspection test, modification test and iterator test. In the test suite there are
 * many test cases focusing on limit and special cases,
 * invalid arguments and etc.
 * 
 * Each method is tested apart from others.</p>
 * 
 * @version 1.0
 * @see TestSuiteMapAdapter
 * @see MapAdapter
 * @see MapEntryAdapter
 * @author Giacomo Calabria
 */

public class TestSuiteEntrySet {
    HMap map1 = null;
    HMap map2 = null;

    static long timeStart = 0;
    /**
     * BeforeClass JUnit static method. Announces the the test begin
     * and starts its timer.
     */
    @BeforeClass
    public static void beforeClassMethod(){
        System.out.println("TestSuiteEntrySet suite started.");
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
        System.out.println("TestSuiteEntrySet suite ended. Time elapsed " + (System.currentTimeMillis() - timeStart)  + "ms.");
    }

    //***************************** SIZE METHOD *******************************

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that an empty map should have an empty entrySet set
     * witch should have a size of zero and isEmpty call returning true. 
     * The map and the set is not modified since its creation.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 0 size (empty).</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map
     * and on the entrySet set.</p>
     * <p><b>Pre-Condition</b>: The map and the entrySet set is empty.</p>
     * <p><b>Post-Condition</b>: The map and the entrySet set is still empty.</p>
     * <p><b>Expected Results</b>: The size method returns 0 and the isEmpty method returns true.</p>
     */
    @Test
    public void Size_Empty(){
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 0, entry.size());
        assertEquals("isEmpty did not returned false.", true, entry.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map with one element should have an relative entrySet
     *  with one element wich should have size of 1 and isEmpty call
     *  returning false. The map is modified before the asserts.</p>
     * 
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 1 size and not being empty.</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map and on the entrySet.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and set contains entry 1=159 .</p>
     * <p><b>Expected Results</b>: The size method returns 1 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_1Element(){
        map1.put(1, 159);
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 1, entry.size());
        assertEquals("isEmpty did not returned false.", false, entry.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map and its relative entrySetwith five mappings
     * should have a size of 5 and isEmpty call returning false. 
     * The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 5 size and not being empty. </p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the set.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the set contains five mappings.</p>
     * <p><b>Expected Results</b>: The size method returns 5 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_5Element(){
        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 5, entry.size());
        assertEquals("isEmpty did not returned false.", false, entry.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map and its relative entrySet with five mappings
     * should have a size of 160 and isEmpty call returning false. 
     * The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 160 size and not being empty. </p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the setn.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the set contains five mappings.</p>
     * <p><b>Expected Results</b>: The size method returns 160 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_160Element(){
        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        HSet entry = map1.entrySet();
        assertEquals("Empty map does not have size of one.", 160, entry.size());
        assertEquals("isEmpty did not returned false.", false, entry.isEmpty());
    }

    // ********************* CONTAINS METHOD ******************************

    /**
     * <p><b>Summary</b>: contains method test case.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of invoking the method in an empty set
     * wich should always return false. </p>
     * <p><b>Test Description</b>: value 'ci' is tested to be present in the set. </p>
     * <p><b>Pre-Condition</b>: The map and the set is empty. </p>
     * <p><b>Post-Condition</b>: The map and the set is unchanged, still empty.</p>
     * <p><b>Expected Results</b>: contains returns false.</p>
     */
    @Test
    public void Contains_EmptyColl(){
        HSet entry = map1.entrySet();
        HMap.HEntry em = new MapEntryAdapter(156);
        em.setValue(11);
        assertEquals("The map contains entry em even if it is empty.", false, entry.contains(em));
    }

    /**
     * <p><b>Summary</b>: contains method test case.</p>
     * <p><b>Test Case Design</b>: Test the case of invoking the method before put and after put mappings on the map</p>
     * <p><b>Test Description</b>: an values is tested to be present in the collection
     *  before and after put the mappings in the map. </p>
     * <p><b>Pre-Condition</b>: The map and the collection is empty. </p>
     * <p><b>Post-Condition</b>: The map and the collection contains a mapping: "mio"="ci" </p>
     * <p><b>Expected Results</b>: contains return false when the value is not present, true otherwhise. </p>
     */
    @Test
    public void Contains_1(){
        HMap.HEntry em = new MapEntryAdapter(15);
        em.setValue(15);

        HSet entry = map1.entrySet();
        assertEquals("The map contains entry em even if it is empty.", false, entry.contains(em));
        
        map1.put(15,15);
        assertEquals("The map contains entry em even if it is empty.", true, entry.contains(em));
    }

    /**
     * <p><b>Summary</b>: contains method test case.
     * <p><b>Test Case Design</b>: The test case checks in various situation its internal state
     * with contains.</p>
     * <p><b>Test Description</b>: Numbers from 50 (included) to 100 (excluded) are added, then checks if
     * elements from 25 to 125 are contained in the set in 3 different steps.
     * <ul>
     * <li>{25:50} not contained (beginning).</li>
     * <li>{50:100} contained (middle).</li>
     * <li>{100:125} not contained (ending).</li>
     * </ul>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the set is not empty.</p>
     * <p><b>Expected Results</b>: The set contains the right elements during execution.
     */
    @Test
    public void Contains_50to100(){
        for (int i = 50; i < 100; i++)
            map1.put(i,i);
        HSet entry = map1.entrySet();
        for (int i = 25; i < 50; i++){
            HMap.HEntry em = new MapEntryAdapter(i);
            em.setValue(i);
            assertEquals("The list should NOT include " + i, false, entry.contains(em));
        }
        for (int i = 50; i < 100; i++){
            HMap.HEntry em = new MapEntryAdapter(i);
            em.setValue(i);
            assertEquals("The list should include " + i, true, entry.contains(em));
        }
        for (int i = 100; i < 125; i++){
            HMap.HEntry em = new MapEntryAdapter(i);
            em.setValue(i); 
            assertEquals("The list should NOT include " + i, false, entry.contains(em));
        }
    }

    /**
     * <p><b>Summary</b>: contains method test case.
     * Calling the contains method with null key in this entrySet should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a contains method invoke with null key.</p>
     * <p><b>Test Description</b>: contains method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and set is empty.</p>
     * <p><b>Post-Condition</b>: Map and set is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test(expected = NullPointerException.class)
    public void Contains_Entry_Null_NPException(){
        HSet entry = map1.entrySet();
        entry.contains(null);
    }

    //****************************** EQUALS METHOD ****************************

    /** 
     * <p><b>Summary</b>: equals method test case.</p>
     * <p><b>Test Case Design</b>: equals method is tested with an equal. The returned
     *  values should be true.</p>
     * <p><b>Test Description</b>: Maps is initialized, then equals invoke are asserted</p>
     * <p><b>Pre-Condition</b>: maps contains 1=1</p>
     * <p><b>Post-Condition</b>: Maps and sets are unchanged.</p>
     * <p><b>Expected Results</b>: The two sets are equal</p>
     */
    @Test
    public void Equals_1(){
        map1.put(1,1);
        map2.put(1,1);
        HSet entry1 = map1.entrySet();
        HSet entry2 = map2.entrySet();

        assertTrue(entry1.equals(entry2));
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The test case the method behaviour with 2 empty set.</p>
     * <p><b>Test Case Design</b>: When both sets are empty the equals
     * method should return true because an empty set is equal to an
     * empty set.</p>
     * <p><b>Test Description</b>: Single assert, {@code key1.equals(key2)} invoked.</p>
     * <p><b>Pre-Condition</b>: Both maps and sets are empty.</p>
     * <p><b>Post-Condition</b>: Both sets are empty.</p>
     * <p><b>Expected Results</b>: equals returns true. </p>
     */
    @Test
    public void Equals_Empty_True(){
        HSet entry1 = map1.entrySet();
        HSet entry2 = map2.entrySet();
        assertTrue(entry1.equals(entry2));
        assertTrue(entry2.equals(entry1));
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The reflective property of equal method is tested.</p>
     * <p><b>Test Case Design</b>: equals method should be reflective,
     * therefore x.equals(x) should always return true. </p>
     * <p><b>Test Description</b>: The test invokes {@code key1.equals(key1)} when
     * key is empty, when it has 10 elements and when it has 1000 elements.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map and the set has 1000 elements. </p>
     * <p><b>Expected Results</b>: The entrySet equals itself</p>
     */
    @Test
    public void Equals_Reflective(){
        HSet entry1 = map1.entrySet();

        assertTrue(entry1.equals(entry1));    // Set is empty
        
        for(int i = 0; i < 10; i++){
            map1.put(i,i);
        }
        
        assertTrue(entry1.equals(entry1));    // Set is not empty, should return true anyways
        
        for(int i = 0; i < 1000; i++){
            map1.put(i,i);
        }
        
        assertTrue(entry1.equals(entry1));    // Set is empty
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The transitive property of equal method is tested.</p>
     * <p><b>Test Case Design</b>: equals method should be transitive</p>
     * <p><b>Test Description</b>: The test invokes key1.equals(key2) and key2.equals(key3)
     * and key1.equals(key3)</p>
     * <p><b>Pre-Condition</b>: The tree maps contains the 30 same mappings</p>
     * <p><b>Post-Condition</b>: The maps and set is unchanged</p>
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
        HSet entry1 = map1.entrySet();
        HSet entry2 = map2.entrySet();
        HSet entry3 = map3.entrySet();

        assertTrue(entry1.equals(entry2));
        assertTrue(entry2.equals(entry3));
        assertTrue("Transitive property is not met.", entry1.equals(entry3));
    }

    
    //********************** CLEAR METHOD **************************************

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on an already empty
     * set, which is a limit case.</p>
     * <p><b>Test Description</b>: Calls clear on the set, then it should be empty</p>
     * <p><b>Pre-Condition</b>: map and set is empty.</p>
     * <p><b>Post-Condition</b>: maps and set is still empty.</p>
     * <p><b>Expected Results</b>: set is Empty</p>
     */
    @Test
    public void Clear_Empty(){
        HSet entry = map1.entrySet();
        entry.clear();
        assertEquals("List should be empty.", true, entry.isEmpty());
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a set containing 1.</p>
     * <p><b>Test Description</b>: Calls clear on the set, then it should be empty</p>
     * <p><b>Pre-Condition</b>: The map contains 1=1, the set contains 1</p>
     * <p><b>Post-Condition</b>: The map and set is empty.</p>
     * <p><b>Expected Results</b>: set is Empty</p>
     */
    @Test
    public void Clear_1Element(){
        map1.put(1,1);
        HSet entry = map1.entrySet();
        entry.clear();
        assertEquals("List should be empty.", true, entry.isEmpty());
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a set containing {0:1000}.</p>
     * <p><b>Test Description</b>: Calls clear on the set, then it should be empty</p>
     * <p><b>Pre-Condition</b>: The map and the set contains 1000 elements</p>
     * <p><b>Post-Condition</b>: The map and the set is empty.</p>
     * <p><b>Expected Results</b>: set is empty</p>
     */
    @Test
    public void Clear_0To1000(){
        for(int i = 0; i < 1000; i ++){
            map1.put(i,i);
        }
        HSet entry = map1.entrySet();
        entry.clear();
        assertEquals("List should be empty.", true, entry.isEmpty());
    }

    // ******************* HASHCODE METHOD ************************************

    /**
     * <p><b>Summary</b>: hashCode test case.
     * Tests the behaviour of hashCode method with different
     * configurations.</p>
     * <p><b>Test Case Design</b>: The same operations are applied to map 1 and 2,
     * so they must have the same elements each time, and so they must have
     * the same entrySet each time, therefore they are equals.
     * If they are equals they must have the same hashCode.</p>
     * <p><b>Test Description</b>: Different configurations have been tested:
     * empty, {1,1}, {"ciao",164}, {"ciao",0:10}</p>
     * <p><b>Pre-Condition</b>: sets have same hashCode and they are equal.</p>
     * <p><b>Post-Condition</b>: sets have same hashCode and they are equal.</p>
     * <p><b>Expected Results</b>: sets have same hashCode and they are equal.</p>
     */
    @Test
    public void HashCode_Prop_entrySet(){
        HSet entry1 = map1.entrySet();
        HSet entry2 = map2.entrySet();
        // Empty map case
        assertEquals("maps should be equal.", true, entry1.equals(entry2));
        assertEquals("Hash codes should be equal.", entry1.hashCode(), entry2.hashCode());

        // One element case
        map1.put(1,1);
        map2.put(1,1);
        assertEquals("maps should be equal.", true, entry1.equals(entry2));
        assertEquals("Hash codes should be equal.", entry1.hashCode(), entry2.hashCode());

        map1.put("ciao",164);
        map2.put("ciao",164);
        assertEquals("maps should be equal.", true, entry1.equals(entry2));
        assertEquals("Hash codes should be equal.", entry1.hashCode(), entry2.hashCode());

        for(int i = 0; i < 10; i++){
            map1.put("ciao" + i,i + 164);
            map2.put("ciao" + i,i + 164);
        }
        assertEquals("maps should be equal.", true, entry1.equals(entry2));
        assertEquals("Hash codes should be equal.", entry1.hashCode(), entry2.hashCode());
    }

    //************************ ADD & ADDALL METHOD **********************************

    /**
     * <p><b>Summary</b>: add method test case.</p>
     * <p><b>Test Case Design</b>: The methoud throws
     * UnsupportedOperationException.</p>
     * <p><b>Test Description</b>: add is invoked.</p>
     * <p><b>Pre-Condition</b>: key1 is empty.</p>
     * <p><b>Post-Condition</b>: key1 is empty.</p>
     * <p><b>Expected Results</b>: The add method is not supported.
     * UnsupportedOperationException is thrown.</p>
     */
    @Test (expected = UnsupportedOperationException.class)
    public void Add_entry(){
        HSet entry1 = map1.entrySet();
        entry1.add(15);
    }
    
    /**
     * <p><b>Summary</b>: addAll method test case.</p>
     * <p><b>Test Case Design</b>: The methoud throws
     * UnsupportedOperationException.</p>
     * <p><b>Test Description</b>: addAll is invoked.</p>
     * <p><b>Pre-Condition</b>: key1 is empty.</p>
     * <p><b>Post-Condition</b>: key1 is empty.</p>
     * <p><b>Expected Results</b>: The addAll method is not supported.
     * UnsupportedOperationException is thrown.</p>
     */
    @Test (expected = UnsupportedOperationException.class)
    public void AddAll_entry(){
        HSet entry1 = map1.entrySet();
        HSet entry2 = map1.entrySet();
        entry1.addAll(entry2);
    }

    //***************************** REMOVE METHOD **********************************

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with null key in this entrySet should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a remove method invoke with null key.</p>
     * <p><b>Test Description</b>: remove method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and set is empty.</p>
     * <p><b>Post-Condition</b>: Map and set is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Remove_EmptyNullEntry_NPException(){
        HSet entry1 = map1.entrySet();
        entry1.remove(null);
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with null key in this entrySet should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a remove method invoke with null key.</p>
     * <p><b>Test Description</b>: remove method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and set contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and set are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Remove_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet entry1 = map1.entrySet();
        entry1.remove(null);
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with a valid key in an empty entrySet should
     * return always false</p>
     * <p><b>Test Case Design</b>: The test invokes remove method on an emtpy set
     * wich is a limit case in wich the remove method should always return false</p>
     * <p><b>Test Description</b>: remove method gets called in an empty set. </p>
     * <p><b>Pre-Condition</b>: Map and set is empty.</p>
     * <p><b>Post-Condition</b>: Map and set is still empty.</p>
     * <p><b>Expected Results</b>: remove method should be return false</p>
     */
    @Test
    public void Remove_Empty(){
        HSet entry1 = map1.entrySet();
        HMap.HEntry em = new MapEntryAdapter(456);
        em.setValue(654);
        
        assertFalse(entry1.remove(em));
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Tests the remove method feature that
     * returns the old value and after call remove method the set
     * does not contains the key</p>
     * <p><b>Test Description</b>: Put an mappings into the map. Then assert that
     * he remove the 44 values. Then assert that an second remove with the same 
     * key return null, because its not present yet. </p>
     * <p><b>Pre-Condition</b>: Map has 44=987 mappings and entrySet has 44</p>
     * <p><b>Post-Condition</b>: Map and Set is empty. </p>
     * <p><b>Expected Results</b>:remove method works properly</p>
     */
    @Test
    public void Remove_ReturnOldValue(){
        map1.put(44, 987);
        HMap.HEntry em = new MapEntryAdapter(44);
        em.setValue(987);
        HSet entry1 = map1.entrySet();
        assertTrue(entry1.remove(em));
        assertFalse(entry1.remove(em));
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Tests the remove method
	 * in a case where the set does not contain the key.</p>
     * <p><b>Test Description</b>: remove is invoked with 2 different uncontained values.</p>
     * <p><b>Pre-Condition</b>: Map contains "ciao"="bello". entrySet contains "ciao" </p>
     * <p><b>Post-Condition</b>: Map and set is unchanged.</p>
     * <p><b>Expected Results</b>: removes returns false.</p>
     */
    @Test
    public void Remove_NotPresent(){
        map1.put("ciao","bello");
        HSet entry1 = map1.entrySet();
        HMap.HEntry em = new MapEntryAdapter(44);
        em.setValue(987);;
        assertFalse(entry1.remove(em));
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Removes all the elements through remove method
     * to test its behaviour. Note that the limit case of removing the last element
     * is tested too.</p>
     * <p><b>Test Description</b>: Calls remove 450 times on an set containing
     * 450 elements, making it empty.</p>
     * <p><b>Pre-Condition</b>: Map contains 450 mappings. entrySet contains also 450 elements</p>
     * <p><b>Post-Condition</b>: Map and set is empty.</p>
     * <p><b>Expected Results</b>: set is empty, obviusly its size is 0.</p>
     */
    @Test
    public void Remove_450ToEmpty(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet entry1 = map1.entrySet();
        for(int i = 0; i < 450; i++){
            HMap.HEntry em = new MapEntryAdapter(i*i*i);
            em.setValue((i+654)*i);
            assertTrue(entry1.remove(em));
        }
        assertEquals("Size should be 0", 0, entry1.size());
        assertEquals("map should be empty.", true, entry1.isEmpty());
    }

    //************************* REMOVEALL METHOD *******************************

    /**
     * <p><b>Summary</b>: removeAll method test case.
     * Calling the removeAll method with null key in this entrySet Set should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a removeAll method invoke with null key.</p>
     * <p><b>Test Description</b>: removeAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Set is empty.</p>
     * <p><b>Post-Condition</b>: Map and Set is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void RemoveAll_EmptyNull_NPException(){
        HSet key1 = map1.entrySet();
        key1.removeAll(null);
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.
     * Calling the removeAll method with null key in this entrySet Set should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a removeAll method invoke with null key.</p>
     * <p><b>Test Description</b>: removeAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Set contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and Set are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void RemoveAll_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        key1.removeAll(null);
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.</p>
     * <p><b>Test Case Design</b>: removeAll method called with
     * empty Set as an argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the entrySet Set and then calls removeAll with an empty Set. 
     * Therefore the maps should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The maps and key1 contains 45 elements.</p>
     * <p><b>Post-Condition</b>: The maps and key1 are unchaged.</p>
     * <p><b>Expected Results</b>: The maps are unchanged, therefore removeAll
     * returns false, key1 is unchanged.</p>
     */
    @Test
    public void RemoveAll_EmptykeyArg(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        HSet keyE = map2.entrySet();
        assertTrue(keyE.isEmpty());
        assertFalse(key1.isEmpty());
        assertFalse("removeAll did not remove anything, therefore it should return false.", key1.removeAll(keyE));
        assertFalse(map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.</p>
     * <p><b>Test Case Design</b>: removeAll method called in an
     * empty Set with an nonempty Set as argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the entrySet Set and then calls removeAll on an empty Set. 
     * Therefore the maps should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The maps and key1 contains 45 elements. keyE is empty</p>
     * <p><b>Post-Condition</b>: The maps and key1 are unchaged. keyE is empty</p>
     * <p><b>Expected Results</b>: The maps are unchanged, therefore removeAll
     * returns false, key1 is unchanged. keyE is still empty</p>
     */
    @Test
    public void RemoveAll_Emptykey(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        HSet keyE = map2.entrySet();
        assertTrue(keyE.isEmpty());
        assertFalse(key1.isEmpty());
        assertFalse(map1.isEmpty());
        assertFalse("removeAll did not remove anything, therefore it should return false.", keyE.removeAll(key1));
        assertFalse(map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: removeAll method test case.</p>
     * <p><b>Test Case Design</b>: removeAll method called between two Sets. key2 includes
     * all elements of the key1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are all in common with map1 then generates
     * the values Set and then calls removeAll with key2 as argument. 
     * Therefore the map1 should be changed and map2 should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and key1 contains 100 elements. key2 contains 10 elements</p>
     * <p><b>Post-Condition</b>: The map1 and key1 are unchaged. key2 is unchanged</p>
     * <p><b>Expected Results</b>: The map1 and key1 are changed, therefore removeAll
     * returns true, key2 is unchanged. </p>
     */

    @Test
    public void RemoveAll_10to20Remove100(){
        for(int i = 0; i < 100; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 10; i < 20; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        HSet key2 = map2.entrySet();
        assertEquals(10, key2.size());
        assertEquals(100, key1.size());
        assertTrue(key1.removeAll(key2));
        assertEquals(10, key2.size());
        assertEquals(90, key1.size());
    }

    //************************* RETAINALL METHOD *******************************

    /**
     * <p><b>Summary</b>: retainAll method test case.
     * Calling the retainAll method with null key in this entrySet Set should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a retainAll method invoke with null key.</p>
     * <p><b>Test Description</b>: retainAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Set is empty.</p>
     * <p><b>Post-Condition</b>: Map and Set is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void RetainAll_EmptyNull_NPException(){
        HSet key1 = map1.entrySet();
        key1.retainAll(null);
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.
     * Calling the retainAll method with null key in this entrySet Set should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a retainAll method invoke with null key.</p>
     * <p><b>Test Description</b>: retainAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Set contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and Set are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void ReretainAll_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        key1.retainAll(null);
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.</p>
     * <p><b>Test Case Design</b>: retainAll method called with
     * empty Set as an argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the entrySet Set and then calls retainAll with an empty Set. 
     * Therefore the maps should be changed.</p>
     * <p><b>Pre-Condition</b>: The map and key1 contains 45 elements. keyE is empty</p>
     * <p><b>Post-Condition</b>: The map and key1 are chaged. keyE is still empty</p>
     * <p><b>Expected Results</b>: The map and key1 changed, therefore retainAll
     * returns true, key1 is changed.</p>
     */
    @Test
    public void RetainAll_EmptySetArg(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        HSet keyE = map2.entrySet();
        assertTrue(keyE.isEmpty());
        assertFalse(key1.isEmpty());
        assertTrue("retainAll did not retain anything, therefore it should return false.", key1.retainAll(keyE));
        assertTrue(map1.isEmpty());
        assertTrue(key1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.</p>
     * <p><b>Test Case Design</b>: retainAll method called in an
     * empty Set with an nonempty Set as argument, which is a method's
     * limit case.</p>
     * <p><b>Test Description</b>: The test adds 45 elements to the map then generates
     * the entrySet Set and then calls retainAll on an empty Set. 
     * Therefore the maps should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The maps and key1 contains 45 elements. keyE is empty</p>
     * <p><b>Post-Condition</b>: The maps and key1 are unchaged. keyE is still empty</p>
     * <p><b>Expected Results</b>: The maps are unchanged, therefore retainAll
     * returns false, key1 is unchanged.</p>
     */
    @Test
    public void RetainAll_Emptykey(){
        for(int i = 0; i < 45; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        HSet keyE = map2.entrySet();
        assertTrue(keyE.isEmpty());
        assertFalse(key1.isEmpty());
        assertFalse(map1.isEmpty());
        assertFalse("retainAll did not retain anything, therefore it should return false.", keyE.retainAll(key1));
        assertTrue(keyE.isEmpty());
        assertFalse(key1.isEmpty());
        assertFalse(map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: retainAll method test case.</p>
     * <p><b>Test Case Design</b>: retainAll method called between two Sets. key2 includes
     * all elements of the key1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are all in common with map1 then generates
     * the entrySet Set and then calls retainAll with key2 as argument. 
     * Therefore the map1 should be changed and map2 should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and key1 contains 100 elements.
     * map2 and key2 contains 10 elements</p>
     * <p><b>Post-Condition</b>: The map1 and key1 are chaged. map2 and key2 is unchanged</p>
     * <p><b>Expected Results</b>: The map1 and key1 are changed, therefore retainAll
     * returns true, key2 and map2 is unchanged. </p>
     */
    @Test
    public void RetainAll_10to20Retain100(){
        for(int i = 0; i < 100; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 10; i < 20; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        HSet key2 = map2.entrySet();
        
        assertEquals(10, key2.size());
        assertEquals(100, key1.size());
        assertEquals(100,map1.size());
        assertEquals(10,map2.size());
        
        assertTrue(key1.retainAll(key2));
        
        assertEquals(10, key2.size());
        assertEquals(10, key1.size());
        assertEquals(10,map1.size());
        assertEquals(10,map2.size());
    }

    //************************** CONTAINSALL METHOD  *****************************

    /**
     * <p><b>Summary</b>: containsAll method test case.
     * Calling the containsAll method with null key in this entrySet Set should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a containsAll method invoke with null key.</p>
     * <p><b>Test Description</b>: containsAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Set is empty.</p>
     * <p><b>Post-Condition</b>: Map and Set is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void ContainsAll_EmptyNull_NPException(){
        HSet key1 = map1.entrySet();
        key1.containsAll(null);
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.
     * Calling the containsAll method with null key in this entrySet Set should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a containsAll method invoke with null key.</p>
     * <p><b>Test Description</b>: containsAll method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map and Set contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map and Set are unchanged.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void ContainsAll_Null_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        key1.containsAll(null);
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.
     * The method tests if an empty Set contains the elements
     * of another Set, which is obviusly false.</p>
     * <p><b>Test Case Design</b>: The test case tests the limit case of
     * checking an empty Set containing something.</p>
     * <p><b>Test Description</b>: The key2 contains 45 elements.
     * The containsAll method obviously should return false for
     * any key's content as the Set is empty.</p>
     * <p><b>Pre-Condition</b>: The key1 is empty. The key2 contains 45 elements</p>
     * <p><b>Post-Condition</b>: The keye1 is still empty. The key2 is unchanged</p>
     * <p><b>Expected Results</b>: The containsAll method return false.</p>
     */
    @Test
    public void ContainsAll_Empty_False(){
        HSet key1 = map1.entrySet();
        for(int i = 0; i < 45; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HSet key2 = map2.entrySet();

        assertEquals("The method should return false because the Set is empty.", false, key1.containsAll(key2)); 
    }

   /**
     * <p><b>Summary</b>: containsAll method test case.
     * The method tests if an empty Set contains the elements
     * of another Set.</p>
     * <p><b>Test Case Design</b>: The test case tests the limit case of
     * checking an empty Set containing an empty Set, which is true, 
     * as the empty subset is the subset of every set, therefore even of the
     * empty set. The tested case is a limit case of containsAll.</p>
     * <p><b>Test Description</b>: The Set is empty.
     * The containsAll method obviously should return true for
     * any key's content, because the empty subset is the
     * subset of every set.</p>
     * <p><b>Pre-Condition</b>: The Sets is empty.</p>
     * <p><b>Post-Condition</b>: The Sets is empty.</p>
     * <p><b>Expected Results</b>: The containsAll method return true.</p>
     */
    @Test
    public void ContainsAll_BothEmpty_False(){
        HSet key1 = map1.entrySet();
        HSet key2 = map2.entrySet();
        assertTrue("The method should return true because the Set is empty.", key1.containsAll(key2)); 
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.</p>
     * <p><b>Test Case Design</b>: containsAll method called between two Sets. key2 includes
     * all elements of the key1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are all in common with map1 then generates
     * the entrySet Set and then calls containsAll with key2 as argument. 
     * Therefore the maps and key should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and key1 contains 100 elements.
     * map2 and key2 contains 10 elements</p>
     * <p><b>Post-Condition</b>: the maps and key should be unchanged.</p>
     * <p><b>Expected Results</b>: the maps and key should be unchanged. containsAll should return
     * true as key1 contains key2 elements. </p>
     */
    @Test
    public void ContainsAll_10to20contains100(){
        for(int i = 0; i < 100; i++){
            map1.put(i,i);
        }
        for(int i = 10; i < 20; i++){
            map2.put(i,i);
        }
        HSet entry1 = map1.entrySet();
        HSet entry2 = map2.entrySet();
        
        assertTrue(entry1.containsAll(entry2));
    }

    /**
     * <p><b>Summary</b>: containsAll method test case.</p>
     * <p><b>Test Case Design</b>: containsAll method called between two Sets. key2 includes
     * elements different from the elements of the key1</p>
     * <p><b>Test Description</b>: The test adds 100 elements to the map1 and 10 elements to
     * the map2 wich are not in common with map1 then generates
     * the entrySet Set and then calls containsAll with key2 as argument. 
     * Therefore the maps and key should be unchanged.</p>
     * <p><b>Pre-Condition</b>: The map1 and key1 contains 100 elements.
     * map2 and key2 contains 10 elements not present in key1</p>
     * <p><b>Post-Condition</b>: the maps and key should be unchanged.</p>
     * <p><b>Expected Results</b>: the maps and key should be unchanged. containsAll should return
     * false as key1 not contains key2 elements. </p>
     */
    @Test
    public void ContainsAll_10Notcontains100(){
        for(int i = 0; i < 100; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 290; i < 300; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        HSet key1 = map1.entrySet();
        HSet key2 = map2.entrySet();
        
        assertFalse(key1.containsAll(key2));
    }

    //************************* TOARRAY METHOD *********************************

    /**
     * <p><b>Summary</b>: toArray method test case. 
     * The test case asserts that an empty set
     * should return an empty array on a toArray call.
     * The set is not modified
     * since its creation.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of
     * a toArray call returning an empty array. From the 
     * Sommerville: "Test with sequences of zero lenght."</p>
     * <p><b>Test Description</b>: Test based on the trivial but possible
     * state of an empty set.</p>
     * <p><b>Pre-Condition</b>: The set is empty.</p>
     * <p><b>Post-Condition</b>: The set is still empty.</p>
     * <p><b>Expected Results</b>: The toArray method returns an empty
     * array and therefore its lenght is 0.</p>
     */
    @Test
    public void ToArray_Empty_EmptyArray(){
        HSet entry1 = map2.entrySet();
        Object[] arr2 = entry1.toArray();
        assertEquals("Empty list did not return empty array.", arr2.length, 0);
    }

    /**
     * <p><b>Summary</b>: toArray method test case.</p>
     * <p><b>Test Case Design</b>: Test the toArray method when the set and the map
     * contains only one element then checks if the set element
     *  matches the inserted elements.</p>
     * <p><b>Test Description</b>: Inserts 1=1 to the map. Then assertArrayEquals
     * is called.</p>
     * <p><b>Pre-Condition</b>: The set and map is empty.</p>
     * <p><b>Post-Condition</b>: The set and map contains 1 element.</p>
     * <p><b>Expected Results</b>: key1.toArray() returns [1].</p>
     */
    @Test
    public void ToArray_1_True(){
        map1.put(12,12);
        HSet entry1 = map1.entrySet();
        HMap.HEntry em = new MapEntryAdapter(12);
        em.setValue(12);
        Object[] arr = {em};
        assertArrayEquals("Arrays do not match.", arr , entry1.toArray());
    }

    /**
     * <p><b>Summary</b>: toArray test case.
     * The test adds one element to the map and then call
     * toArray method on the entrySet.</p>
     * <p><b>Test Case Design</b>: Test focuses on toArray behaviour when
     * it has only one element, which is a limit case.</p>
     * <p><b>Test Description</b>: Adds one to the set, calls toArray method
     * and checks the array's first element and its size.</p>
     * <p><b>Pre-Condition</b>: The set is empty.</p>
     * <p><b>Post-Condition</b>: The set has one element {145}.</p>
     * <p><b>Expected Results</b>: The element is stored correctly in the
     * array returned from the method (the array is [1]) and its size is 1.</p>
     */
    @Test
    public void ToArray_ArrayDest_1(){
        Object[] arr = new Object[1];
        map1.put(145,1654);
        HSet key1 = map1.entrySet();
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
     * <p><b>Pre-Condition</b>: The set is empty</p>
     * <p><b>Post-Condition</b>: The set is still empty.</p>
     * <p><b>Expected Results</b>: NullPointerExceptio is thrown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void ToArray_DestNullKey_NPException(){
        HSet key1 = map1.entrySet();
        key1.toArray(null);
    }

    /**
     * <p><b>Summary</b>: toArray(HSet) method test case.
     * The set has 2 elements and checks the array.</p>
     * <p><b>Test Case Design</b>: The test checks the method behaviour when the
     * argument size is not enough for containing the set's elements, which a
     * special case.</p>
     * <p><b>Test Description</b>: arr contains the result
     * of toArray method, but exception is being thrown.</p>
     * <p><b>Pre-Condition</b>: The set has 2 elements, arr is empty.</p>
     * <p><b>Post-Condition</b>: The set has 2 elements, arr is still empty.</p>
     * <p><b>Expected Results</b>: HIllegalArgumentException is thrown.</p>
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
        HSet key1 = map1.entrySet();
        key1.toArray(arr);
    }

    //*************************** TOSTRING METHOD *****************************

    /**
     * <p><b>Summary</b>: toString method test case.</p>
     * <p><b>Test Case Design</b>: Tests toString method on an empty
	 * set.</p>
     * <p><b>Test Description</b>: toString is invoked on a
	 * empty set.</p>
     * <p><b>Pre-Condition</b>: map1 and key are empty.</p>
     * <p><b>Post-Condition</b>: map1 and key are empty.</p>
     * <p><b>Expected Results</b>: key.toString return [] </p>
     */
	@Test
	public void ToString_Empty(){
        HSet key = map1.entrySet();
		assertEquals("[]", key.toString());
	}

    /**
     * <p><b>Summary</b>: toString method test case.</p>
     * <p><b>Test Case Design</b>: Tests toString method on a
	 * map containing Ciaoooo=1 so that key set contains Ciaoooo</p>
     * <p><b>Test Description</b>: toString is invoked on the set.</p>
     * <p><b>Pre-Condition</b>: map1 contains Ciaoooo=1.</p>
     * <p><b>Post-Condition</b>: map1 contains Ciaoooo=1.</p>
     * <p><b>Expected Results</b>: key.toString [Ciaoooo] </p>
     */
	@Test
	public void ToString_OneElement(){
		map1.put("Ciaoooo", 1);
        HSet key = map1.entrySet();
		assertEquals("[Ciaoooo]", key.toString());
	}

    // ************************** BACKED FEATURE *************************

    /**
     * <p><b>Summary</b>: backing clear and put test case. Test the correct propagation entrySet
     *  to map and viceversa by using the clear method on the 
     * map and on the set and the put method on the map</p>
     * <p><b>Test Case Design</b>: Using some assertion it test the backing of contents
     * in the map and the set before and after
     * some put call and clear call. </p>
     * <p><b>Test Description</b>: Test the backing of contents in the map and the set before and after
     * some put call and clear call.</p>
     * <p><b>Pre-Condition</b>: map and entrySet is empty. </p>
     * <p><b>Post-Condition</b>: map and entrySet is empty. </p>
     * <p><b>Expected Results</b>: put and clear should add and remove elements from both side. </p>
     */
    @Test
    public void Backed_ClearPut(){
        HSet coll = map1.entrySet();
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

    /**
     * <p><b>Summary</b>: backing putall and remove test case. Test the correct propagation entrySet
     *  to map and viceversa by using the remove method on the 
     * map and on the set and the putall method on the map</p>
     * <p><b>Test Case Design</b>: Using some assertion it test the backing of contents
     * in the map and the set before and after
     * some putall call and remove call. </p>
     * <p><b>Test Description</b>: Test the backing of contents in the map and the set before and after
     * some putall call and remove call.</p>
     * <p><b>Pre-Condition</b>: map and entrySet is empty. </p>
     * <p><b>Post-Condition</b>: map and entrySet is empty. </p>
     * <p><b>Expected Results</b>: putall and remove should add and remove elements from both side. </p>
     */
    @Test
    public void Backed_putAllRemove(){
        HSet coll = map1.entrySet();
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

    /**
     * <p><b>Summary</b>: backing put and remove test case.
     * Test the correct propagation values to map and viceversa by using the remove method on the 
     * map and on the set and the put method on the map</p>
     * <p><b>Test Case Design</b>: Using some assertion it test the backing of contents
     * in the map and the set before and after
     * some put call and remove call. </p>
     * <p><b>Test Description</b>: Test the backing of contents in the map and the set before and after
     * some put call and remove call.</p>
     * <p><b>Pre-Condition</b>: map and entrySet is empty. </p>
     * <p><b>Post-Condition</b>: map and entrySet is empty. </p>
     * <p><b>Expected Results</b>: put and remove should add and remove elements from both side. </p>
     */
    @Test
    public void Backed_PutRemove(){
        HSet key1 = map1.entrySet();
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

    /**
     * <p><b>Summary</b>: backing remove with iterator test case. Test the correct propagation
     * from entrySet to map and viceversa by using the remove method of iterator  </p>
     * <p><b>Test Case Design</b>: Tests the iterator's remove
     * method in a set with 1000 elements util iterator has next element</p>
     * <p><b>Test Description</b>: it invoke next and then remove until the iterator has the next
     * element. Once he had finished the iterator clear all the set. So the map and the 
     * set is emtpy</p>
     * <p><b>Pre-Condition</b>: map1 and entrySet has 1000 elements</p>
     * <p><b>Post-Condition</b>: map1 and entrySet are empty </p>
     * <p><b>Expected Results</b>: map1 and entrySet are empty, so the iterator correctly remove all elements</p>
     */
    @Test
    public void Backed_IteratorRemove(){
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

    /**
     * <p><b>Summary</b>: backing removeAll test case. Test the propagation from entrySet to map
     * and viceversa.</p>
     * <p><b>Test Case Design</b>:Tests removeAll method with
     * entrySet HSet. Correct propagation is tested in both ways.</p>
     * <p><b>Test Description</b>: map1 contains 50 mappings, and key1 as consequence contains
     * 50 elements. map2 contains only 5 mappings in 20-25 range. 
     * After call removeAll all the elements in common between key1 and key2 will be removed.</p>
     * <p><b>Pre-Condition</b>:map1 contains 50 mappings, key1 contains 50 values,
     * map2 contains 5 mappings and key2 contains 5 values</p>
     * <p><b>Post-Condition</b>: map2 and key2 are unchanged. map1 and key1 contains less elements.</p>
     * <p><b>Expected Results</b>: the removeAll method correctly propagate the changes. Also removeAll
     * method remove the elements in common between key1 and key2</p>
     */
    @Test
    public void Backed_removeAll(){
        for(int i = 0; i < 50; i++)
            map1.put(i*i,i);
        HSet key1 = map1.entrySet();

        for(int i = 0; i < 50; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(key1.contains(i*i));
        }

        for(int i = 20; i < 25; i++)
            map2.put(i*i,i);
        HSet key2 = map2.entrySet();

        key1.removeAll(key2);

        for(int i = 0; i < 20; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(key1.contains(i*i));
        }

        for(int i = 20; i < 25; i++){
            assertFalse(map1.containsKey(i*i));
            assertFalse(key1.contains(i*i));
        }

        for(int i = 25; i < 50; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(key1.contains(i*i));
        }
    }

    /**
     * <p><b>Summary</b>: baking retainAll test case. Test the propagation from entrySet to map
     * and viceversa.</p>
     * <p><b>Test Case Design</b>:Tests retainAll method with
     * entrySet HSet. Correct propagation is tested in both ways.</p>
     * <p><b>Test Description</b>: map1 contains 50 mappings, and key1 as consequence contains
     * 50 elements. map2 contains only 5 mappings in 20-25 range. 
     * After call retainAll all the elements in common between key1 and key2 will retain.</p>
     * <p><b>Pre-Condition</b>:map1 contains 50 mappings, key1 contains 50 values,
     * map2 contains 5 mappings and key2 contains 5 values</p>
     * <p><b>Post-Condition</b>: map2 and key2 are unchanged. map1 and key1 contains less elements.</p>
     * <p><b>Expected Results</b>: the retainAll method correctly propagate the changes. Also retainAll
     * method retain the elements in common between key1 and key2</p>
     */
    @Test
    public void Backed_retainAll(){
        for(int i = 0; i < 50; i++)
            map1.put(i*i,i);
        HSet key1 = map1.entrySet();

        for(int i = 0; i < 50; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(key1.contains(i*i));
        }

        for(int i = 20; i < 25; i++)
            map2.put(i*i,i);
        HSet key2 = map2.entrySet();

        key1.retainAll(key2);

        for(int i = 0; i < 20; i++){
            assertFalse(map1.containsKey(i*i));
            assertFalse(key1.contains(i*i));
        }

        for(int i = 20; i < 25; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(key1.contains(i*i));
        }

        for(int i = 25; i < 50; i++){
            assertFalse(map1.containsKey(i*i));
            assertFalse(key1.contains(i*i));
        }
    }

    //****************************** ITERATOR METHOD *******************************

    /**
     * <p><b>Summary</b>: hasNext and next methods test case.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of
     * an iterator returned from an empty set
     * calling hasNext and next. </p>
     * <p><b>Test Description</b>: an iterator is returned from empty
     * set. iterator.hasNext() should be false, while
     * next() should throw NoSuchElementException.</p>
     * <p><b>Pre-Condition</b>: The set is empty.</p>
     * <p><b>Post-Condition</b>: The set is still empty.</p>
     * <p><b>Expected Results</b>: hasNext returns false, NSEE is thrown.</p>
     */
    @Test (expected = NoSuchElementException.class)
    public void Iterator_HasNext_Emtpy(){
        HIterator iter = map1.entrySet().iterator();
        assertEquals("Empty set iterator should not have next.", false, iter.hasNext());
        iter.next();
    }

     /**
     * <p><b>Summary</b>: hasNext and next methods test case.
     * set contains 1 element and the test iterate through
     * it.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of 1
     * element in the set. Therefore hasNext should return
     * true while next() should return the only number
     * in the set.</p>
     * <p><b>Test Description</b>: The number 1 is added, and an iterator
     * iterates through the set. After returning the first elements,
     * it has no more next elements.</p>
     * <p><b>Pre-Condition</b>: set contains 1, iterator has next.</p>
     * <p><b>Post-Condition</b>: set contains 1, iterator has not next.</p>
     * <p><b>Expected Results</b>: The first hasNext call returns true,
     * the second returns false.</p>
     */
    @Test
    public void Iterator_HasNext_Begin1_True(){
        map1.put(1,1);
        HIterator iter = map1.entrySet().iterator();
        assertEquals("Should have next.", true, iter.hasNext());
        iter.next();
        assertEquals("Should not have next.", false, iter.hasNext());
    }

    /**
     * <p><b>Summary</b>:iterator next method test case</p>
     * <p><b>Test Case Design</b>: Tests that next method return the correct elements</p>
     * <p><b>Test Description</b>: Simply call next method on an set contains 1 elements</p>
     * <p><b>Pre-Condition</b>:map and set contains 1 elements</p>
     * <p><b>Post-Condition</b>: map and set are unchanged</p>
     * <p><b>Expected Results</b>: next return 1 and hasnext is false</p>
     */
    @Test
    public void Iterator_Next_1(){
        map1.put(1,1);
        HIterator iter = map1.entrySet().iterator();
        assertEquals("Should have 1 as next.", 1, iter.next());
        assertEquals("Should not have next.", false, iter.hasNext());
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Test should throw an exception.</p>
     * <p><b>Test Case Design</b>: Tests if for a set a remove method
     * throws IllegalStateException on an emtpy set</p>
     * <p><b>Test Description</b>: remove is invoked by an iterator instance on an emtpy set.</p>
     * <p><b>Pre-Condition</b>: set is emtpy.</p>
     * <p><b>Post-Condition</b>: set is emtpy.</p>
     * <p><b>Expected Results</b>: HIllegalStateException thrown.</p>
     */
    @Test (expected = IllegalStateException.class)
    public void Iterator_Remove_Empty_HISE(){
        HIterator iter = map1.entrySet().iterator();
        assertEquals("Should not have next.", false, iter.hasNext());
        iter.remove();
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Test should throw an exception.</p>
     * <p><b>Test Case Design</b>: Tests if for a set a remove method
     * throws HIllegalStateException, as no prev or next has been 
     * called, or remove or add have been called after the last call to
     * next or previous</p>
     * <p><b>Test Description</b>: remove is invoked by an iterator instance.</p>
     * <p><b>Pre-Condition</b>: set has 1 element.</p>
     * <p><b>Post-Condition</b>: set still has 1 element.</p>
     * <p><b>Expected Results</b>: HIllegalStateException thrown.</p>
     */
    @Test (expected = IllegalStateException.class)
    public void Remove_OneElement_HISE(){
        map1.put(1,1);
        HIterator iter = map1.entrySet().iterator();
        iter.remove(); /* Exception throw as no prev or next has been
                            called, or remove or add have been called after
                            the last call to
                            next or previous*/
    }
}
