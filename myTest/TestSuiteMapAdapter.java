package myTest;

// JUnit imports
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Adapter package import
import myAdapter.*;

/**
 * 
 * <strong> Class TestSuiteMapAdapter </strong>
 * <p>
 * 
 * <p><b>Summary</b>: The test suite TestSuiteMapAdapter provides test on map methods.
 * It offer different type of test in different case scenario, in order to test their corret 
 * behaviour. The first section of this test suite contains the test in the TestMap.java file
 * assigned by the Professor, correctly translated in the JUnit format.
 * After this first section there are several section for each methods (or group of feature) contains 
 * the test cases ideated by me. 
 * 
 * The {@code entrySet()}, {@code keySet()} and {@code values()} method and its feature are
 *  tested in different dedicated test suite. 
 * </p>
 * <p><b>Test Suite Design</b>: The test suite contains fine-grained test cases in order to
 * easily individuate errors in HMap methods.
 * 
 * This suite include modification test, inspection set and an section dedicated to HEntry method test.
 * In the test suite there are many test cases focusing on limit and special cases,
 * invalid arguments and etc.
 * 
 * Each method is tested apart from others.</p>
 * 
 * @version 1.0
 * @see TestSuiteValuesCollection
 * @see TestSuiteKeySet
 * @see TestSuiteEntrySet
 * @see MapAdapter
 * @author Giacomo Calabria
 */

public class TestSuiteMapAdapter {
    
    HMap map1 = null;
    HMap map2 = null;

    String[] args = {"pippo", "pluto", "qui", "ciccio", "gambatek"};
    static long timeStart = 0;
    /**
     * BeforeClass JUnit static method. Announces the the test begin
     * and starts its timer.
     */
    @BeforeClass
    public static void beforeClassMethod(){
        System.out.println("TestSuiteMapAdapter suite started.");
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
        map2 = null;
    }

    /**
     * AfterClass JUnit static method. Announces the the test end
     * and stops its timer. Prints milliseconds elapsed from the beginning.
     */
    @AfterClass
    public static void afterClassMethod(){
        System.out.println("TestSuiteMapAdapter suite ended. Time elapsed " + (System.currentTimeMillis() - timeStart)  + "ms.");
    }

    // **************************** TEST MAP ASSIGNED BY PROFESSOR ****************************
    
    /**
     * <p><b>Summary</b>:Test the propagation of changes from the map to the keySet.
     * It is tested with adding and removing mappings from the map. </p>
     * <p><b>Test Case Design</b>: Tests that the map correctly propagates the changes
     * of the element in the keySet and vice-versa</p>
     * <p><b>Test Description</b>: The map is initialized with some mappings. Then an entry is removed from
     * the map and after re added to the map.</p>
     * <p><b>Pre-Condition</b>: The map contains args elements.</p>
     * <p><b>Post-Condition</b>: The map contains args elements.</p>
     * <p><b>Expected Results</b>: The map correctly propagates the changes to keySet</p>
     */

    @Test
    public void Propagation_Map_KeySet(){
        
        for(int i=0;i<args.length;i++){
			map1.put(args[i], args[i]);
		}

        int sm0, sm1, sm2, ss0, ss1, ss2;

        HSet ks = map1.keySet();
        sm0 = map1.size();
		ss0 = ks.size();
        map1.remove(args[0]);
        sm1 = map1.size();
		ss1 = ks.size();
        map1.put(args[0], args[0]);
		sm2 = map1.size();
		ss2 = ks.size();

        assertTrue("La mappa non propaga le modifiche a KeySet", sm0 == ss0 && sm1 == ss1 && sm2 == ss2 && (sm0-sm1) == 1);
    }
    
    /**
     * <p><b>Summary</b>:Test the backing of changes from the map to the keySet.
     * It is tested with adding and removing mappings from the keySet. </p>
     * <p><b>Test Case Design</b>: Tests that the keySet correctly backed the changes
     * of the element in the map and vice-versa</p>
     * <p><b>Test Description</b>: The map is initialized with some mappings. Then an entry is removed from
     * the keySet and after re added to the map.</p>
     * <p><b>Pre-Condition</b>: The map contains args elements.</p>
     * <p><b>Post-Condition</b>: The map contains args elements.</p>
     * <p><b>Expected Results</b>: The keySet correctly backed the changes to map</p>
     */
    @Test
    public void Backing_Map_KeySet(){
        
        for(int i=0;i<args.length;i++){
			map1.put(args[i], args[i]);
		}

        int sm0, sm1, sm2, ss0, ss1, ss2;

        HSet s1 = map1.keySet();
		sm0 = map1.size();
		ss0 = s1.size();

        HIterator iter = s1.iterator();
        int count = s1.size() + 2;
        
        while(iter.hasNext()&&count-->=0){
            iter.next();
        }
        s1.remove(args[0]);

        sm1 = map1.size();
        ss1 = s1.size();
        iter = s1.iterator();
        count = s1.size()+2;
        while(iter.hasNext()&&count-->=0){
            iter.next();
        }
        
        map1.put("carrozza", "carrozza");
        
        iter = s1.iterator();
        count = s1.size()+2;
        while(iter.hasNext()&&count-->=0){
            iter.next();
        }

        sm2 = map1.size();
        ss2 = s1.size();

        s1.remove("carrozza");

        assertFalse("Removal from key set not working",sm2 == map1.size() || ss2 == s1.size() || s1.size() != map1.size());
        assertTrue("KeySet non propaga modifiche a map", sm0 == ss0 && sm1 == ss1 && sm2 == ss2 && (sm0-sm1) == 1);
    }

    /**
     * <p><b>Summary</b>:Test the propagation of changes from the map to the keySet with the iterator. </p>
     * <p><b>Test Case Design</b>: Tests that the map correctly propagates the changes
     * of the element in the keySet using its iterator</p>
     * <p><b>Test Description</b>: The map is initialized with some mappings. Then with the keySet iterator
     * it removes all elements</p>
     * <p><b>Pre-Condition</b>: The map contains args elements.</p>
     * <p><b>Post-Condition</b>: The map is empty.</p>
     * <p><b>Expected Results</b>: The keySet iterator works properly and empty the map</p>
     */
    @Test
    public void Emptying_KeySet_Iterator(){
        
        for(int i=0;i<args.length;i++){
			map1.put(args[i], args[i]);
		}

        HSet s1 = map1.keySet();
        HIterator iter = s1.iterator();
        int count = s1.size() + 2;
        while(iter.hasNext() && count-- >= 0){
            iter.next();
            iter.remove();
        }
        assertTrue("keyset iterator removal does not work",map1.size() == s1.size() && map1.size() == 0);
    }

    /**
     * <p><b>Summary</b>: Test the clear and put method. </p>
     * <p><b>Test Case Design</b>: Test that clear and put methods works properly</p>
     * <p><b>Test Description</b>: The map is initialized with some mappings. Then its cleared and after 
     * is refilled with the initialization mappings and one more element wich is a duplicate</p>
     * <p><b>Pre-Condition</b>:The map contains args elements. </p>
     * <p><b>Post-Condition</b>: The map contains args elements</p>
     * <p><b>Expected Results</b>: The map is unchanged and map put works properly as it not permit duplicate
     * elements.</p>
     */
    @Test
    public void Reset_Map(){
        
        for(int i=0;i<args.length;i++){
			map1.put(args[i], args[i]);
		}
        map1.clear();
        map1.put(args[0], args[0]);
        for(int i=0;i<args.length;i++){
            map1.put(args[i], args[i]);
        }

        assertFalse("Map.put not working", map1.size() != args.length);
    }

    /**
     * <p><b>Summary</b>:Test the propagation of changes from the map to the values.
     * It is tested with adding and removing mappings from the map. </p>
     * <p><b>Test Case Design</b>: Tests that the map correctly propagates the changes
     * of the element in the values and vice-versa</p>
     * <p><b>Test Description</b>: The map is initialized with some mappings. Then an entry is removed from
     * the map and after re added to the map.</p>
     * <p><b>Pre-Condition</b>: The map contains args elements.</p>
     * <p><b>Post-Condition</b>: The map contains args elements.</p>
     * <p><b>Expected Results</b>: The map correctly propagates the changes to valuest</p>
     */
    @Test
    public void Test_ValuesMethod(){
        int sm0, sm1, sm2, ss0, ss1, ss2;
        
        for(int i=0;i<args.length;i++){
			map1.put(args[i], args[i]);
		}

        HCollection c = map1.values();

        sm0 = map1.size();
        ss0 = c.size();

        HIterator iter = c.iterator();
        int count = c.size() +2;
        while(iter.hasNext() && count-- >= 0)
            iter.next();
        c.remove(args[0]);

        sm1 = map1.size();
        ss1 = c.size();

        iter = c.iterator();
        count = c.size()+2;
        while(iter.hasNext()&&count-->= 0)
            iter.next();

        sm2 = map1.size();
        ss2 = c.size();

        assertTrue("values NON propaga modifiche a map ",sm0 == ss0 && sm1 == ss1 && sm2 == ss2 && (sm0-sm1) == 1);
    }

    /**
     * <p><b>Summary</b>:Test the propagation of changes from the map to the values with the iterator. </p>
     * <p><b>Test Case Design</b>: Tests that the map correctly propagates the changes
     * of the element in the values using its iterator</p>
     * <p><b>Test Description</b>: The map is initialized with some mappings. Then with the values iterator
     * it removes all elements</p>
     * <p><b>Pre-Condition</b>: The map contains args elements.</p>
     * <p><b>Post-Condition</b>: The map is empty.</p>
     * <p><b>Expected Results</b>: The values iterator works properly and empty the map</p>
     */
    @Test
    public void Emptying_Values_Iterator(){
        
        for(int i=0;i<args.length;i++){
			map1.put(args[i], args[i]);
		}

        HCollection c = map1.values();
        HIterator iter = c.iterator();
        int count = c.size() + 2;
        while(iter.hasNext() && count-- >= 0){
            iter.next();
            iter.remove();
        }
        assertTrue("keyset iterator removal does not work",map1.size() == c.size() && map1.size() == 0);
    }

    // ****************************** SIZE ISEMPTY METHOD *************************

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that an empty map should have a size of zero and
     * isEmpty call returning true. The map is not modified since its creation.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 0 size (empty)</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map is still empty.</p>
     * <p><b>Expected Results</b>: The size method returns 0 and the isEmpty method returns true.</p>
     */
    @Test
    public void Size_Empty_0(){

        assertEquals("Empty map does not have size of zero.", 0, map1.size());
        assertEquals("isEmpty did not returned true.", true, map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map with one mapping should have a size of 1 and
     * isEmpty call returning false. The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 1 size and not being empty.</p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map contains mapping 1=159 .</p>
     * <p><b>Expected Results</b>: The size method returns 1 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_1Element(){

        map1.put(1, 159);
        assertEquals("Empty map does not have size of one.", 1, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map with five mappings should have a size of 5 and
     * isEmpty call returning false. The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 5 size and not being empty. </p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map contains five mappings.</p>
     * <p><b>Expected Results</b>: The size method returns 5 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_5Element(){

        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }
        assertEquals("Empty map does not have size of one.", 5, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
    }

    /**
     * <p><b>Summary</b>: size, isEmpty method test case. 
     * The test case asserts that a map with 160 mappings should have a size of 160 and
     * isEmpty call returning false. The map is modified before the asserts.</p>
     * <p><b>Test Case Design</b>: The design is a simple assert of
     * a size call and expected 160 size and not being empty./p>
     * 
     * <p><b>Test Description</b>: size and isEmpty methods are invoked on the map.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map contains 160 mappings.</p>
     * <p><b>Expected Results</b>: The size method returns 160 and the isEmpty method returns false.</p>
     */
    @Test
    public void Size_160Element(){

        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        assertEquals("Empty map does not have size of one.", 160, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
    }

    // ************************** CONTAINS KEY & VALUE METHOD **************************************

    /**
     * <p><b>Summary</b>: containsKey method test case.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of invoking the method in an empty map
     * wich should always return false. </p>
     * <p><b>Test Description</b>: key 'ci' is tested to be presente in the map as key. </p>
     * <p><b>Pre-Condition</b>: The map is empty. </p>
     * <p><b>Post-Condition</b>: The map is unchanged, still empty.</p>
     * <p><b>Expected Results</b>: containsKey returns false.</p>
     */
    @Test
    public void Contains_EmptyMapKey(){
        assertEquals("The map contains key 'ci' even if it is empty.", false, map1.containsKey("ci"));
    }

    /**
     * <p><b>Summary</b>: containsValue method test case.</p>
     * <p><b>Test Case Design</b>: Tests the limit case of invoking the method in an empty map
     * wich should always return false. </p>
     * <p><b>Test Description</b>: value 'ci' is tested to be present in the map as value. </p>
     * <p><b>Pre-Condition</b>: The map is empty. </p>
     * <p><b>Post-Condition</b>: The map is unchanged, still empty.</p>
     * <p><b>Expected Results</b>: containsValue returns false.</p>
     */
    @Test
    public void Contains_EmptyMapValue(){
        assertEquals("The map contains value 'ci' even if it is empty.", false, map1.containsValue("ci"));
    }

    /**
     * <p><b>Summary</b>: containsKey method test case.</p>
     * <p><b>Test Case Design</b>: Test the case of invoking the method before put and after put mappings</p>
     * <p><b>Test Description</b>: 2 keys are tested to be present in the map before and after put the mappings. </p>
     * <p><b>Pre-Condition</b>: The map is empty. </p>
     * <p><b>Post-Condition</b>: The map contains two mappings: "ci"=15 and "ao"=465 </p>
     * <p><b>Expected Results</b>: containsKey return false when the mappings are not present, true otherwhise. </p>
     */
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

    /**
     * <p><b>Summary</b>: containsValue method test case.</p>
     * <p><b>Test Case Design</b>: Test the case of invoking the method before put and after put mappings</p>
     * <p><b>Test Description</b>: 2 values are tested to be present in the map before and after put the mappings. </p>
     * <p><b>Pre-Condition</b>: The map is empty. </p>
     * <p><b>Post-Condition</b>: The map contains two mappings: "ci"=15 and "ao"=465 </p>
     * <p><b>Expected Results</b>: containsValue return false when the mappings are not present, true otherwhise. </p>
     */
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

    /**
     * <p><b>Summary</b>: containsKey method test case.</p>
     * <p><b>Test Case Design</b>: Test the case in wich the method is invoked in a map with medium size. </p>
     * <p><b>Test Description</b>: The map is modified before the asserts. Then it cheks containsKey
     *  in different ranges.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map is not empty</p>
     * <p><b>Expected Results</b>: containsKey return false when the mappings are not present, true otherwhise. 
     * The map contains the right key during execution</p>
     */
    @Test
    public void Contains_Key_50to100(){
        for (int i = 50; i < 100; i++)
            map1.put(i,i+164);
        for (int i = 25; i < 50; i++){
            assertEquals("The map should NOT include " + i, false, map1.containsKey(i));
        }
        for (int i = 50; i < 100; i++){
            assertEquals("The map should include " + i, true, map1.containsKey(i));
        }
        for (int i = 100; i < 125; i++){
            assertEquals("The map should NOT include " + i, false, map1.containsKey(i));
        }
    }

    /**
     * <p><b>Summary</b>: containsValue method test case.</p>
     * <p><b>Test Case Design</b>: Test the case in wich the method is invoked in a map with medium size. </p>
     * <p><b>Test Description</b>: The map is modified before the asserts. Then it cheks containsValue
     * in different ranges.</p>
     * <p><b>Pre-Condition</b>: The map is empty.</p>
     * <p><b>Post-Condition</b>: The map is not empty</p>
     * <p><b>Expected Results</b>: containsValue return false when the mappings are not present, true otherwhise.
     * The map contains the right value during execution</p>
     */
    @Test
    public void Contains_Value_50to100(){
        for (int i = 50; i < 100; i++)
            map1.put(i+44,i);
        for (int i = 25; i < 50; i++){
            assertEquals("The map should NOT include " + i, false, map1.containsValue(i));
        }
        for (int i = 50; i < 100; i++){
            assertEquals("The map should include " + i, true, map1.containsValue(i));
        }
        for (int i = 100; i < 125; i++){
            assertEquals("The map should NOT include " + i, false, map1.containsValue(i));
        }
    }

    /**
     * <p><b>Summary</b>: containsKey containsValue method test case.</p>
     * <p><b>Test Case Design</b>: Test the case in wich the method is invoked in a map with 3 mappings.</p>
     * <p><b>Test Description</b>: Checks some assert before and after the putting of mappings in the map. </p>
     * <p><b>Pre-Condition</b>:The map is empty. </p>
     * <p><b>Post-Condition</b>: The map contains 3 mappings. </p>
     * <p><b>Expected Results</b>: containsValue and containsKey return false when the
     * mappings are not present, true otherwhise.</p>
     */
    @Test
    public void Contains_KeyAndValue_1and3(){
        assertEquals("The map contains entry 654=15 even if it is empty.", false, map1.containsValue(15) && map1.containsKey(654));
        map1.put(654,15);

        assertEquals("The map does not contains entry 654=15 even if it should.", true, map1.containsValue(15) && map1.containsKey(654));
        assertEquals("The map contains entry 155=44 even if it is empty.", false, map1.containsValue(44) && map1.containsKey(155));

        map1.put(654,155);
        map1.put(155,44);

        assertEquals("The map does not contains entry 155=44 even if it should.", true, map1.containsValue(44) && map1.containsKey(155));
    }

    /**
     * <p><b>Summary</b>: containsKey method test case.
     * Calling the containsKey method with null key in this map should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a containsKey method invoke with null key.</p>
     * <p><b>Test Description</b>: containsKey method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test(expected = NullPointerException.class)
    public void Contains_NullKey_NPException(){
        map1.containsKey(null);
    }

    /**
     * <p><b>Summary</b>: containsValue method test case.
     * Calling the containsValue method with null value in this map should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a containsValue method invoke with null value.</p>
     * <p><b>Test Description</b>: containsValue method gets called with null value. </p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test(expected = NullPointerException.class)
    public void Contains_NullValue_NPException(){
        map1.containsValue(null);
    }

    //****************************** EQUALS METHOD ****************************

    /**
     * <p><b>Summary</b>: equals method test case.</p>
     * <p><b>Test Case Design</b>: equals method is tested with an equal map
     * with the same put calling. The returned values should be true.</p>
     * 
     * <p><b>Test Description</b>: Maps are initialized with the putting of one 
     * mappings 15=16.4 then equals is invoked.</p>
     * <p><b>Pre-Condition</b>: Maps are empty</p>
     * <p><b>Post-Condition</b>: Maps contains 15=16.4.</p>
     * <p><b>Expected Results</b>: The Maps are equals.</p>
     */
    @Test
    public void Equals_1(){
        map1.put(15,16.4);
        map2.put(15,16.4);
        assertTrue("This maps should be equals.",map1.equals(map2));
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The test case the method behaviour with 2 empty map.</p>
     * <p><b>Test Case Design</b>: When both maps are empty the equals
     * method should return true because an empty map is equal to an
     * empty map.</p>
     * 
     * <p><b>Test Description</b>: Single assert, l1.equals(l2) invoked.</p>
     * <p><b>Pre-Condition</b>: Both maps are empty.</p>
     * <p><b>Post-Condition</b>: Both maps are empty.</p>
     * <p><b>Expected Results</b>: equals returns true, as one empty map
     * of course equals another empty maps.</p>
     */
    @Test
    public void Equals_Empty_True(){
        assertEquals("Two empty maps should equals.", true, map1.equals(map2));
        assertEquals("Two empty maps should equals.", true, map2.equals(map1));
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The reflective property of equal method is tested.</p>
     * <p><b>Test Case Design</b>: equals method should be reflective,
     * therefore x.equals(x) should always return true.</p>
     * 
     * <p><b>Test Description</b>: The test invokes map1.equals(map1) when
     * map1 is empty, when it has 10 elements and when it has 1000 elements.</p>
     * <p><b>Pre-Condition</b>: Map is not null.</p>
     * <p><b>Post-Condition</b>: map has 1000 elements. </p>
     * <p><b>Expected Results</b>: map equals itself, therefore
     * reflective property is valid.</p>
     */
    @Test
    public void Equals_Reflective(){
        assertEquals("Reflective property is not met.", true, map1.equals(map1));
        for(int i = 0; i < 10; i++){
            map1.put(i,i);
        }
        assertEquals("Reflective property is not met.", true, map1.equals(map1));
        for(int i = 0; i < 1000; i++){
            map1.put(i,i);
        }
        assertEquals("Reflective property is not met.", true, map1.equals(map1));
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The transitive property of equal method is tested.</p>
     * <p><b>Test Case Design</b>: equals method should be transitive,
     * therefore a.equals(b) and b.equals(c) {@literal =>} a.equals(c).</p>
     * 
     * <p><b>Test Description</b>: The test invokes map1.equals(map2) and map2.equals(map3)
     * and map1.equals(map3)</p>
     * <p><b>Pre-Condition</b>: maps contains 30 mappings.</p>
     * <p><b>Post-Condition</b>: maps are unchanged. </p>
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

        assertEquals("maps should be equal.", true, map1.equals(map2));
        assertEquals("maps should be equal.", true, map2.equals(map3));
        assertEquals("Transitive property is not met.",true, map1.equals(map3));
    }

    //****************************** GET METHOD *******************************

    /**
     * <p><b>Summary</b>: get method test case.</p>
     * <p><b>Test Case Design</b>: Tests get behaviour when the
     * map is empty, which is a limit case.</p>
     * 
     * <p><b>Test Description</b>: get is invoked on an empty map with a key.</p>
     * <p><b>Pre-Condition</b>: Map is empty</p>
     * <p><b>Post-Condition</b>: Map is unchanged</p>
     * <p><b>Expected Results</b>: {@code null} is returned</p>
     */
    @Test
    public void Get_Empty(){
        assertEquals("Should be null", null, map1.get("Key"));
    }

    /**
     * <p><b>Summary</b>: get method test case.</p>
     * <p><b>Test Case Design</b>: Tries to get a value
     * of a key not in the map, then the mappings is put into the map.</p>
     * 
     * <p><b>Test Description</b>: get is invoked before and after putting a mappings in the map.</p>
     * <p><b>Pre-Condition</b>: Map contains 15=16.4.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: m.get returns proper values.</p>
     */

    @Test
    public void Get_1(){
        assertEquals("The result should be null, the map is empty .",null, map1.get("chiave nulla"));

        map1.put(15,16.4);

        assertEquals("The result should be null, no key corresponding in this map .",null, map1.get("chi"));

        assertEquals("The element shuld be 16.4 .",16.4, map1.get(15));

        map1.remove(15);
        
        assertEquals("The result should be null, no key corresponding in this map .",null, map1.get(15));
    }

    /**
     * <p><b>Summary</b>: get method test case.</p>
     * <p><b>Test Case Design</b>: Tests get behaviour when the
     * map contains 3 mappings.</p>
     * <p><b>Test Description</b>: get is invoked with keys.</p>
     * <p><b>Pre-Condition</b>: m contains 3 mappings</p>
     * <p><b>Post-Condition</b>: m is unchanged</p>
     * <p><b>Expected Results</b>: each get returns in string form.</p>
     */

    @Test
    public void Get_123(){
        map1.put(654,1);
        map1.put(456,2);
        map1.put(546,3);
        String res = "" + map1.get(654) + map1.get(456) + map1.get(546);
        assertEquals("The result string sould be 123", "123", res);
    }

    /**
     * <p><b>Summary</b>: get method test case.
     * Calling the get method with null key in this map should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a get method invoke with null key.</p>
     * 
     * <p><b>Test Description</b>: get method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test(expected = NullPointerException.class)
    public void Get_GetNullKey_NPException(){
        map1.get(null);
    }

    /**
     * <p><b>Summary</b>: get method test case.
     * Test aim is to put in the map 10000 mappings and check
     * if the elements are stored correctly.</p>
     * <p><b>Test Case Design</b>: Test the map with a very large size.</p>
     * 
     * <p><b>Test Description</b>: The test put 10000 mappings to the
     * map and then checks every one of them with the get method.</p>
     * <p><b>Pre-Condition</b>: The map is Empty.</p>
     * <p><b>Post-Condition</b>: The map contains 10000 mappings</p>
     * <p><b>Expected Results</b>: Every one of the 10000 mappings matches,
     * therefore get returns each element.</p>
     */
    @Test
    public void Get_10000_All(){
        for(int i = 0; i < 100000; i++)
            map1.put(i,i);
        for (int i = 0; i < 10000; i++)
            assertEquals("Element at index " + i + "should be " + i, i, map1.get(i));
    }

    // ******************************** CLEAR METHOD **************************

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on an already empty
     * map, which is a limit case.</p>
     * <p><b>Test Description</b>: map is empty. Calls clear on the map, then it should be
     * equal to another empty map.</p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is still empty.</p>
     * <p><b>Expected Results</b>: Map is equal to another empty map. isEmpty return true and size is 0. </p>
     */
    @Test
    public void Clear_Empty(){
        map1.clear();
        assertTrue("Map should be empty.", map1.isEmpty());
        assertTrue("Map should be empty.", map1.equals(map2));
        assertEquals("Size should be 0", 0,map1.size());
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a map containing 1=1.</p>
     * <p><b>Test Description</b>: Calls clear on the map, then it should be
     * equal to another empty map.</p>
     * <p><b>Pre-Condition</b>: Map contains 1:1.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: Map is equal to another empty map .</p>
     */
    @Test
    public void Clear_1Element(){
        map1.put(1,1);
        map1.clear();
        assertEquals("Map should be empty.", true, map1.isEmpty());

        assertEquals("Map should be empty.", true, map1.equals(map2));
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on a map containing 1000 mappings.</p>
     * <p><b>Test Description</b>: Calls clear on the map, then it should be
     * equal to another empty map.</p>
     * <p><b>Pre-Condition</b>: Map contains 1000 mappings.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: Map is equal to another empty map.</p>
     */
    @Test
    public void Clear_0To1000(){
        for(int i = 0; i < 1000; i++){
            map1.put(i*i,(i+15)*i);
        }
        map1.clear();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Map should be empty.", true, map1.equals(map2));
    }

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on 2 different map.
     * The maps are in various configurations through the test case, and each one
     * is much different from the other. But in the end, after a clear invoke, they
     * must all be empty.</p>
     * <p><b>Test Description</b>: Calls clear on the maps, then they should 
     * equal in any case.</p>
     * <p><b>Pre-Condition</b>: map1 and map2 are different.</p>
     * <p><b>Post-Condition</b>: Maps are empty.</p>
     * <p><b>Expected Results</b>: Maps are equal.</p>
     */
    @Test
    public void Clear_TwoMaps()
    {
        for(int i = 0; i < 100; i++){
            map1.put(i*i,(i+15)*i);
        }
        for(int i = 0; i < 450; i++){
            map2.put(i*i*i,(i+654)*i);
        }
        map1.clear();
        map2.clear();
        assertEquals("Empty maps should be equal.", true, map2.equals(map1));
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Map should be empty.", true, map2.isEmpty());
    }

    //*************************** PUT METHOD **********************************

    /**
     * <p><b>Summary</b>: put method test case.
     * Calling the put method with null key in this map should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a put method invoke with null key.</p>
     * 
     * <p><b>Test Description</b>: put method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Put_NullKey_NPException(){
        map1.put(null, 156);
    }

    /**
     * <p><b>Summary</b>: put method test case.
     * Calling the put method with null value in this map should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a put method invoke with null value.</p>
     * 
     * <p><b>Test Description</b>: put method gets called with null value. </p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Put_NullValue_NPException(){
        map1.put(1789, null);
    }

    /**
     * <p><b>Summary</b>: put method test case. Tests one put invoke.</p>
     * <p><b>Test Case Design</b>: Tests put invoke with an key and value.</p>
     * <p><b>Test Description</b>: put is invoked with key
	 * 156 and value "ciao"</p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map contains 156="ciao".</p>
     * <p><b>Expected Results</b>: Map contains Key: 156 and value "ciao" and its
	 * size is 1.</p>
     */
    @Test
    public void Put_1Mapping(){
        assertFalse(map1.containsKey(156));
        assertFalse(map1.containsValue("ciao"));
        map1.put(156, "ciao");
        assertTrue(map1.containsKey(156));
        assertTrue(map1.containsValue("ciao"));
        assertEquals("Size should be 1", 1, map1.size());
    }
    
    /**
     * <p><b>Summary</b>: put method test case.
	 * Tests 100 put invoke.</p>
     * <p><b>Test Case Design</b>: Tests put with 100
	 * mappings, testing medium input for the map.</p>
     * <p><b>Test Description</b>: put is invoked with
	 * key i*i and value i+16 for each iteration.</p>
     * <p><b>Pre-Condition</b>: map is empty.</p>
     * <p><b>Post-Condition</b>: map contains 100 mappings</p>
     * <p><b>Expected Results</b>: map contains each mappings and its
	 * size is 100, therefore puts works correctly.</p>
     */
    @Test
    public void Put_100Mapping(){
        for(int i = 0; i < 100; i++){
            assertFalse(map1.containsKey(i*i));
            assertFalse(map1.containsValue(i+16));
        }
        for(int i = 0; i < 100; i++)
            map1.put(i*i, i+16);
        
        for(int i = 0; i < 100; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(map1.containsValue(i+16));
        }
        assertEquals("Size should be 100",100,map1.size());
    }

    //************************* REMOVE METHOD *********************************

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with null key in empty map should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a remove method invoke with null key.</p>
     * 
     * <p><b>Test Description</b>: remove method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Remove_EmptyNullKey_NPException(){
        map1.remove(null);
    }

    /**
     * <p><b>Summary</b>: remove method test case.
     * Calling the remove method with null key in this map should throw
     * NullPointerException runtime exception.</p>
     * <p><b>Test Case Design</b>: The test expects the aforementioned
     * exception to be thrown after a remove method invoke with null key.</p>
     * 
     * <p><b>Test Description</b>: remove method gets called with null key. </p>
     * <p><b>Pre-Condition</b>: Map contains 450 elements.</p>
     * <p><b>Post-Condition</b>: Map contains 450 elements.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void Remove_NullKey_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        map1.remove(null);
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Tests the remove method
	 * in a limit case, which is an empty map, which
	 * obviusly does not contain the key.</p>
     * <p><b>Test Description</b>: remove is invoked with 156 key.</p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is unchanged.</p>
     * <p><b>Expected Results</b>: remove returns null</p>
     */
    @Test
    public void Remove_Empty(){
        assertEquals(null, map1.remove(156));
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Tests the remove method feature that
     * returns the old value and after call remove method the map
     * does not contains the key</p>
     * <p><b>Test Description</b>: Put an mappings into the map. Then assert that
     * he remove the 987 values. Then assert that an second remove with the same 
     * key return null, because its not present yet. </p>
     * <p><b>Pre-Condition</b>:Map has 44=987 mappings</p>
     * <p><b>Post-Condition</b>: Map is empty. </p>
     * <p><b>Expected Results</b>:remove method works properly</p>
     */

    @Test
    public void Remove_ReturnOldValue(){
        map1.put(44, 987);
        assertEquals(987, map1.remove(44));
        assertEquals(null, map1.remove(44));
    }

    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Tests the remove method
	 * in a case where the map does not contain the key.</p>
     * <p><b>Test Description</b>: remove is invoked with 20
	 * key.</p>
     * <p><b>Pre-Condition</b>: Map contains "ciao"="bello".</p>
     * <p><b>Post-Condition</b>: Map is unchanged.</p>
     * <p><b>Expected Results</b>: removes returns null.</p>
     */
    @Test
    public void Remove_NotPresent(){
        map1.put("ciao","bello");
        assertEquals(null, map1.remove(44));
        assertEquals(null, map1.remove("aa"));
    }
    
    /**
     * <p><b>Summary</b>: remove method test case.</p>
     * <p><b>Test Case Design</b>: Removes all the elements through remove method
     * to test its behaviour. Note that the limit case of removing the last element
     * is tested too.</p>
     * <p><b>Test Description</b>: Calls remove 450 times on an map containing
     * 450 mappings, making it empty. Also test that remove methods return the old value</p>
     * <p><b>Pre-Condition</b>: Map contains 450 mappings.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: Map is empty, obviusly its size is 0.</p>
     */
    @Test
    public void Remove_450ToEmpty(){
        assertEquals("Size should be 0", 0, map1.size());
        assertEquals("map should be empty.", true, map1.isEmpty());
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        for(int i = 0; i < 450; i++){
            assertEquals((i+654)*i, map1.remove(i*i*i));
        }
        assertEquals("Size should be 0", 0, map1.size());
        assertEquals("map should be empty.", true, map1.isEmpty());
    }

    //********************************* PUTALL METHOD **************************

    /**
     * <p><b>Summary</b>: PutAll method test case.
     * Calling PutAll method, which takes a HMap argument, throws an exception.</p>
     * <p><b>Test Case Design</b>: Tests the case with null
     * argument passed, which is a special case of invalid argument.</p>
     * <p><b>Test Description</b>: Calls PutAll with null argument, therefore it
     * throws NullPointerException.</p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is empty.</p>
     * <p><b>Expected Results</b>: NullPointerException has been trown.</p>
     */
    @Test (expected = NullPointerException.class)
    public void PutAll_NullMap_NPException(){
        map1.putAll(null);
    }

    /**
     * <p><b>Summary</b>: putAll method test case. </p>
     * <p><b>Test Case Design</b>: putAll must behave correctly
     * adding a collection of 100 elements, which is a common case for
     * the putAll method.</p>
     * <p><b>Test Description</b>: 100 elements are added in map2 and after map2 is putAll in map1 then the test checks the contained element and its size. </p>
     * <p><b>Pre-Condition</b>: map1 is Empty, map2 contains 100 mappings. </p>
     * <p><b>Post-Condition</b>: map1 and map2 contains 100 mappings. </p>
     * <p><b>Expected Results</b>: map2 contanis 100 mappings. </p>
     */
    @Test
    public void PutAll_An100ElementsMap(){
        for(int i = 0; i < 100; i++){
            assertEquals(false, map1.containsKey(i*i));
            assertEquals(false, map2.containsKey(i*i));
        }
        
        for(int i = 0; i < 100; i++)
            map2.put(i*i, i+16);
        
        for(int i = 0; i < 100; i++)
            assertEquals(true, map2.containsKey(i*i));
        
        map1.putAll(map2);

        assertEquals("Maps size should be 100.", 100, map1.size());

        for(int i = 0; i < 100; i++)
            assertEquals(true, map1.containsKey(i*i));
    }

    /**
     * <p><b>Summary</b>: putAll method test case. </p>
     * <p><b>Test Case Design</b>: Tests the case of empty map being
     * passed as argument, which is a limit case.</p>
     * <p><b>Test Description</b>: The test cases calls putAll as empty map 
     * as argument</p>
     * <p><b>Pre-Condition</b>: Maps are empty</p>
     * <p><b>Post-Condition</b>: Maps are empty</p>
     * <p><b>Expected Results</b>: Maps are still empty</p>
     */
    @Test
    public void PutAll_EmptyMapInEmptyMap(){
        assertEquals(true, map1.isEmpty());
        assertEquals(true, map2.isEmpty());
        map1.putAll(map2);
        assertEquals(true, map1.isEmpty());
        assertEquals(true, map2.isEmpty());
        map2.putAll(map1);
        assertEquals(true, map1.isEmpty());
        assertEquals(true, map2.isEmpty());
    }
    
    /**
     * <p><b>Summary</b>: putAll method test case. </p>
     * <p><b>Test Case Design</b>: Tests the case of same empty map being
     * passed as argument, which is a limit case.</p>
     * <p><b>Test Description</b>: The test cases calls putAll as same empty map
     * as argument</p>
     * <p><b>Pre-Condition</b>: Map is empty</p>
     * <p><b>Post-Condition</b>: Map is empty</p>
     * <p><b>Expected Results</b>: Map is still empty</p>
     */

    @Test 
    public void PutAll_SameEmptyMap(){
        assertEquals(true, map1.isEmpty());
        map1.putAll(map1);
        assertEquals(true, map1.isEmpty());
    }
    

    /**
     * <p><b>Summary</b>:putAll method test case. 
     * The test adds two times the same map to the map, then
     *  checks if the elements were stored correctly.</p>
     * <p><b>Test Case Design</b>: putAll must behave correctly
     * adding two times the same things. </p>
     * 
     * <p><b>Test Description</b>: invokes two times the same method with the same
     * map passed as argument</p>
     * <p><b>Pre-Condition</b>: map1 is empty, map2 contains 100 mappings. </p>
     * <p><b>Post-Condition</b>: both maps contains 100 mappings</p>
     * <p><b>Expected Results</b>: map1 contains 100 mappings </p>
     */
    @Test 
    public void PutAll_2TimesPut(){
        for(int i = 0; i < 100; i++)
            map2.put(i*i, i+16);
        for(int i = 0; i < 100; i++){
            assertTrue(map2.containsKey(i*i));
            assertTrue(map2.containsValue(i+16));
        }

        assertTrue(map1.isEmpty());

        map1.putAll(map2);

        assertEquals(false, map1.isEmpty());
        for(int i = 0; i < 100; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(map1.containsValue(i+16));
        }
        
        map1.putAll(map2);
        for(int i = 0; i < 100; i++){
            assertTrue(map1.containsKey(i*i));
            assertTrue(map1.containsValue(i+16));
        }

        assertEquals(100, map1.size());
        assertEquals(100, map2.size());
    }

    /**
     * <p><b>Summary</b>:putAll method test case. The test adds two different maps to the same map, then checks if the elements were stored correctly.</p>
     * <p><b>Test Case Design</b>: putAll must behave correctly
     * adding two different maps. </p>
     * <p><b>Test Description</b>: invokes two time the same method with the two different
     *  maps passed as argument</p>
     * <p><b>Pre-Condition</b>: map1 is empty, map2 and map3 contains 100-10 mappings. </p>
     * <p><b>Post-Condition</b>: map1 is not empty, map2 and map3 contains 100-10 mappings.</p>
     * <p><b>Expected Results</b>: map1 contains elements from both maps </p>
     */

    @Test
    public void PutAll_2Maps(){
        for(int i = 0; i < 100; i++)
            map2.put(i*i, i+16);
        
        HMap map3 = new MapAdapter();

        for(int i = 0; i < 10; i++)
            map3.put(i*i+2, i+48);
        
        for(int i = 0; i < 100; i++){
            assertEquals(true, map2.containsKey(i*i));
            assertEquals(true, map2.containsValue(i+16));
        }

        for(int i = 0; i < 10; i++){
            assertEquals(true, map3.containsKey(i*i+2));
            assertEquals(true, map3.containsValue(i+48));
        }

        assertEquals(true, map1.isEmpty());
        map1.putAll(map2);

        assertEquals(100, map1.size());
        for(int i = 0; i < 100; i++){
            assertEquals(true, map1.containsKey(i*i));
            assertEquals(true, map1.containsValue(i+16));
        }

        map1.putAll(map3);
        for(int i = 0; i < 10; i++){
            assertEquals(true, map1.containsKey(i*i+2));
            assertEquals(true, map1.containsValue(i+48));
        }
    }


    // ******************* HASHCODE METHOD ************************************

    /**
     * <p><b>Summary</b>: hashCode test case.
     * Tests the behaviour of hashCode method with different
     * configurations.</p>
     * <p><b>Test Case Design</b>: The same operations are applied to map 1 and 2,
     * so they must have the same elements each time, therefore they are equals.
     * If they are equals they must have the same hashCode.</p>
     * 
     * <p><b>Test Description</b>: Different configurations have been tested:
     * empty, 1=1, "ciao"=164, 10 mappings </p>
     * <p><b>Pre-Condition</b>: Maps have same hashCode and they are equal.</p>
     * <p><b>Post-Condition</b>: Maps have same hashCode and they are equal.</p>
     * <p><b>Expected Results</b>: Maps have same hashCode and they are equal.</p>
     */
    @Test
    public void HashCode_Prop(){
        // Empty map case
        assertEquals("maps should be equal.", true, map1.equals(map2));
        assertEquals("Hash codes should be equal.", map1.hashCode(), map2.hashCode());

        // One element case
        map1.put(1,1);
        map2.put(1,1);
        assertEquals("maps should be equal.", true, map1.equals(map2));
        assertEquals("Hash codes should be equal.", map1.hashCode(), map2.hashCode());

        map1.put("ciao",164);
        map2.put("ciao",164);
        assertEquals("maps should be equal.", true, map1.equals(map2));
        assertEquals("Hash codes should be equal.", map1.hashCode(), map2.hashCode());

        for(int i = 0; i < 10; i++){
            map1.put("ciao" + i,i + 164);
            map2.put("ciao" + i,i + 164);
        }
        assertEquals("maps should be equal.", true, map1.equals(map2));
        assertEquals("Hash codes should be equal.", map1.hashCode(), map2.hashCode());
    }

    // **************************** HCOLLECTION METHODS ******************************************
    
    /**
     * <p><b>Summary</b>: values test case</p>
     * <p><b>Test Case Design</b>: values() is an HCollection</p>
     * <p><b>Test Description</b>: checks that values() is an instance of HCollection</p>
     * <p><b>Pre-Condition</b>:Map is empt</p>
     * <p><b>Post-Condition</b>:Map is empty</p>
     * <p><b>Expected Results</b>: values() is an HCollection</p>
     */
    @Test
    public void Values(){
        assertTrue(map1.values() instanceof HCollection);
    }

    /**
     * <p><b>Summary</b>: keySet test case</p>
     * <p><b>Test Case Design</b>: keySet() is an HSet</p>
     * <p><b>Test Description</b>: checks that keySet() is an instance of HSet</p>
     * <p><b>Pre-Condition</b>:Map is empt</p>
     * <p><b>Post-Condition</b>:Map is empty</p>
     * <p><b>Expected Results</b>: keySet() is an HSet</p>
     */
    @Test
    public void KeySet(){
        assertTrue(map1.keySet() instanceof HSet);
    }

    /**
     * <p><b>Summary</b>: entrySet test case</p>
     * <p><b>Test Case Design</b>: entrySet() is an HSet</p>
     * <p><b>Test Description</b>: checks that entrySet() is an instance of HSet</p>
     * <p><b>Pre-Condition</b>:Map is empt</p>
     * <p><b>Post-Condition</b>:Map is empty</p>
     * <p><b>Expected Results</b>: entrySet() is an HSet</p>
     */
    @Test
    public void EntrySet(){
        assertTrue(map1.entrySet() instanceof HSet);
    }

    //************************ TOSTRING METHOD *********************************

    /**
     * <p><b>Summary</b>: toString method test case.</p>
     * <p><b>Test Case Design</b>: Tests toString method on an empty
	 * map.</p>
     * <p><b>Test Description</b>: toString is invoked on a
	 * empty map.</p>
     * <p><b>Pre-Condition</b>: map1 is empty.</p>
     * <p><b>Post-Condition</b>: map1 is empty.</p>
     * <p><b>Expected Results</b>: map1.toString returns {}</p>
     */
	@Test
	public void ToString_Empty(){
		assertEquals("{}", map1.toString());
	}

    /**
     * <p><b>Summary</b>: toString method test case.</p>
     * <p><b>Test Case Design</b>: Tests toString method on a
	 * map containing 1=Ciaoooo.</p>
     * <p><b>Test Description</b>: toString is invoked on the map.</p>
     * <p><b>Pre-Condition</b>: map1 contains 1=Ciaoooo.</p>
     * <p><b>Post-Condition</b>: map1 still contains 1=Ciaoooo.</p>
     * <p><b>Expected Results</b>: map1.toString returns {1=Ciaoooo}</p>
     */
	@Test
	public void ToString_OneElement(){
		map1.put(1, "Ciaoooo");
		assertEquals("{1=Ciaoooo}", map1.toString());
	}


    // ************************************ ENTRY CLASS ******************************
    
    /**
     * <p><b>Summary</b>: getValue method test case. </p>
     * <p><b>Test Case Design</b>: Test getValue method on an entry containing 15=164</p>
     * <p><b>Test Description</b>: getValue is invoked on an entry</p>
     * <p><b>Pre-Condition</b>: entry me contains 15=164 </p>
     * <p><b>Post-Condition</b>:  entry me still contains 15=164 </p>
     * <p><b>Expected Results</b>: getValue return 164</p>
     */
    @Test
    public void Get_Value(){
        MapEntryAdapter me = new MapEntryAdapter(15,164);
        assertEquals(164, me.getValue());
    }

    /**
     * <p><b>Summary</b>: getKey method test case. </p>
     * <p><b>Test Case Design</b>: Test getKey method on an entry containing 999="ciao"</p>
     * <p><b>Test Description</b>: getKey is invoked on an entry</p>
     * <p><b>Pre-Condition</b>: entry me contains 999="ciao" </p>
     * <p><b>Post-Condition</b>:  entry me still contains 999="ciao" </p>
     * <p><b>Expected Results</b>: getKey return 999</p>
     */
    @Test
    public void Get_Key(){
        MapEntryAdapter me = new MapEntryAdapter(999,"ciao");
        assertEquals(999, me.getKey());
    }
    
    /**
     * <p><b>Summary</b>: setValue method test case. </p>
     * <p><b>Test Case Design</b>: Test setValue method on an entry containing 15=164</p>
     * <p><b>Test Description</b>: setValue is invoked on an entry with the new value "ciao"
     *  it also check that setValue return the old value</p>
     * <p><b>Pre-Condition</b>: entry me contains 15=164 </p>
     * <p><b>Post-Condition</b>:  entry me contains 15="ciao" </p>
     * <p><b>Expected Results</b>: setValue work correctly</p>
     */
    @Test
    public void SetValue_Return(){
        MapEntryAdapter me = new MapEntryAdapter(15,164);
        assertEquals(164, me.getValue());
        assertEquals(164, me.setValue("ciao"));
        assertEquals("ciao", me.getValue());
    }

    /**
     * <p><b>Summary</b>: equals method test case. </p>
     * <p><b>Test Case Design</b>: Test equals method between two entry containing 15 as key</p>
     * <p><b>Test Description</b>: equals method is invoked</p>
     * <p><b>Pre-Condition</b>: entry me1 and me2 contains key 15 </p>
     * <p><b>Post-Condition</b>:  entry me1 and me2 still contains key 15 </p>
     * <p><b>Expected Results</b>: the two entres should be equal</p>
     */
    @Test
    public void Equals_Empty(){
        MapEntryAdapter me1 = new MapEntryAdapter(15);
        MapEntryAdapter me2 = new MapEntryAdapter(15);
        assertTrue(me1.equals(me2));
        assertTrue(me2.equals(me1));
    }

    /**
     * <p><b>Summary</b>: equals method test case. </p>
     * <p><b>Test Case Design</b>: Test equals methon between two entry containing 15 as key</p>
     * <p><b>Test Description</b>: equals method is invoked</p>
     * <p><b>Pre-Condition</b>: entry me1 and me2 contains key 15 </p>
     * <p><b>Post-Condition</b>:  entry me1 and me2 still contains key 15 </p>
     * <p><b>Expected Results</b>: the two entres should be equal</p>
     */
    @Test
    public void Equals_Val1(){
        MapEntryAdapter me1 = new MapEntryAdapter(15,"cii");
        MapEntryAdapter me2 = new MapEntryAdapter(15,"cii");
        assertTrue(me1.equals(me2));
    }

    /**
     * <p><b>Summary</b>: hashCode test case.
     * Tests the behaviour of hashCode method</p>
     * <p><b>Test Case Design</b>: The same operations are applied to emtry 1 and 2,
     * so they must have the same elements each time, therefore they are equals.
     * If they are equals they must have the same hashCode.</p>
     * <p><b>Test Description</b>: Configurations have been tested</p>
     * <p><b>Pre-Condition</b>: Maps have same hashCode and they are equal.</p>
     * <p><b>Post-Condition</b>: Maps have same hashCode and they are equal.</p>
     * <p><b>Expected Results</b>: Maps have same hashCode and they are equal.</p>
     */
    @Test
    public void HashCode_Entry(){
        MapEntryAdapter me1 = new MapEntryAdapter(15);
        MapEntryAdapter me2 = new MapEntryAdapter(15);
        // Empty map case
        assertEquals("Entry should be equal.", true, me1.equals(me2));
        assertEquals("Hash codes should be equal.", me1.hashCode(), me2.hashCode());
    }


    /**
     * <p><b>Summary</b>: toString method test case.</p>
     * <p><b>Test Case Design</b>: Tests toString method on two entres.</p>
     * <p><b>Test Description</b>: toString is invoked on the entres</p>
     * 
     * <p><b>Pre-Condition</b>: me1 and me2 contains 15=null</p>
     * <p><b>Post-Condition</b>: me1 and me2 contains 15=null</p>
     * <p><b>Expected Results</b>: me1.toString should be equal to me2.toString </p>
     */
    @Test
    public void ToString_Entry(){
        MapEntryAdapter me1 = new MapEntryAdapter(15);
        MapEntryAdapter me2 = new MapEntryAdapter(15);
        assertEquals("Entry should be equal.", true, me1.equals(me2));
        assertEquals("toString string should be equal.", me1.toString(), me2.toString());
        assertEquals("15",me1.toString());
        assertEquals("15",me2.toString());
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