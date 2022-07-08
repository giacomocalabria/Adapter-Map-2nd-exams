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
 * @version 1.0
 * @see TestSuiteValuesCollection
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

        assertEquals("Empty map does not have size of zero.", 0, map1.size());
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
    public void Size_1Element(){

        map1.put(1, 159);
        assertEquals("Empty map does not have size of one.", 1, map1.size());
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
    public void Size_5Element(){

        for(int i = 0; i < 5; i++){
            map1.put(i, (i+15)*(i+2));
        }
        assertEquals("Empty map does not have size of one.", 5, map1.size());
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
    public void Size_160Element(){

        for(int i = 0; i < 160; i++){
            map1.put(i, (i+15)*(i+2));
        }
        assertEquals("Empty map does not have size of one.", 160, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
    }

    // ************************** CONTAINS KEY & VALUE METHOD **************************************

    @Test
    public void Contains_EmptyMapKey(){
        assertEquals("The map contains key 'ci' even if it is empty.", false, map1.containsKey("ci"));
    }

    @Test
    public void Contains_EmptyMapValue(){
        assertEquals("The map contains value 'ci' even if it is empty.", false, map1.containsValue("ci"));
    }

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

    @Test(expected = NullPointerException.class)
    public void Contains_NullKey_NPException(){
        map1.containsKey(null);
    }

    @Test(expected = NullPointerException.class)
    public void Contains_NullValue_NPException(){
        map1.containsValue(null);
    }

    //****************************** EQUALS METHOD ****************************

    @Test
    public void Equals_1(){
        map1.put(15,16.4);
        map2.put(15,16.4);
        assertEquals(true, map1.equals(map2));
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The test case the method behaviour with 2 empty map.</p>
     * <p><b>Test Case Design</b>: When both maps are empty the equals
     * method should return true because an empty map is equal to an
     * empty map.</p>
     * <p><b>Test Description</b>: Single assert, l1.equals(l2) invoked.</p>
     * <p><b>Pre-Condition</b>: Both maps are empty.</p>
     * <p><b>Post-Condition</b>: Both maps are empty.</p>
     * <p><b>Expected Results</b>: equals returns true, as one
     * empty map of course equals another empty maps.</p>
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
     * <p><b>Test Description</b>: The test invokes map1.equals(map1) when
     * map1 is empty, when it has 10 elements and when it has 1000 elements.</p>
     * <p><b>Pre-Condition</b>: Map is not null.</p>
     * <p><b>Post-Condition</b>: map has 1000 elements. </p>
     * <p><b>Expected Results</b>: map equals itself, therefore
     * reflective property is valid.</p>
     */
    @Test
    public void Equals_Reflective()
    {
        assertEquals("Reflective property is not met.", true, map1.equals(map1));    // MAp is empty
        for(int i = 0; i < 10; i++){
            map1.put(i,i);
        }
        assertEquals("Reflective property is not met.", true, map1.equals(map1));    // map is not empty, should return true anyways
        for(int i = 0; i < 1000; i++){
            map1.put(i,i);
        }
        assertEquals("Reflective property is not met.", true, map1.equals(map1));    // map is not empty, should return true anyways
    }

    /**
     * <p><b>Summary</b>: equals method test case.
     * The transitive property of equal method is tested.</p>
     * <p><b>Test Case Design</b>: equals method should be transitive,
     * therefore a.equals(b) and b.equals(c) {@literal =>} a.equals(c).</p>
     * <p><b>Test Description</b>: The test invokes map1.equals(map2) and map2.equals(map3)
     * and map1.equals(map3)</p>
     * <p><b>Pre-Condition</b>: maps contain {1 : 30}.</p>
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

    @Test
    public void Get_1(){
        assertEquals("The result should be null, the map is empty .",null, map1.get("chiave nulla"));

        map1.put(15,16.4);

        assertEquals("The result should be null, no key corresponding in this map .",null, map1.get("chi"));

        assertEquals("The element shuld be 16.4 .",16.4, map1.get(15));

        map1.remove(15);
        
        assertEquals("The result should be null, no key corresponding in this map .",null, map1.get(15));
    }

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
     * exception to be thrown after a get method invoke with null key. From the 
     * Sommerville: "Test with sequences of zero lenght."</p>
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
     * Test aim is to push the map
     size to 10000 and check
     * if the elements are stored correctly.</p>
     * <p><b>Test Case Design</b>: Test the map with a very large size.
     * From the Sommerville: "Use sequences of different sizes in
     * different tests."</p>
     * <p><b>Test Description</b>: The test pushes 10000 elements to the
     * map and then checks every one of them with the get method.</p>
     * <p><b>Pre-Condition</b>: The map is Empty.</p>
     * <p><b>Post-Condition</b>: The map contains {0:10000}</p>
     * <p><b>Expected Results</b>: Every one of the 10000 elements matches,
     * therefore get returns each element in {0:10000}.</p>
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
     * <p><b>Test Case Design</b>: Invokes clear method on a map containing 1:1.</p>
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
     * <p><b>Test Case Design</b>: Invokes clear method on a map containing {0:1000}.</p>
     * <p><b>Test Description</b>: Calls clear on the map, then it should be
     * equal to another empty map.</p>
     * <p><b>Pre-Condition</b>: Map contains {0:1000}.</p>
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
     * <p><b>Pre-Condition</b>: map1 and 2 are different.</p>
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

    @Test (expected = NullPointerException.class)
    public void Put_NullKey_NPException(){
        map1.put(null, 156);
    }

    @Test (expected = NullPointerException.class)
    public void Put_NullValue_NPException(){
        map1.put(1789, null);
    }

    @Test
    public void Put_1MappingForKey(){
        assertEquals(false, map1.containsKey(156));
        map1.put(156, "ciao");
        assertEquals(true, map1.containsKey(156));
    }
    
    @Test
    public void Put_100MappingForKey(){
        for(int i = 0; i < 100; i++)
            assertEquals(false, map1.containsKey(i*i));
        
        for(int i = 0; i < 100; i++)
            map1.put(i*i, i+16);
        
        for(int i = 0; i < 100; i++)
            assertEquals(true, map1.containsKey(i*i));
    }

    //************************* REMOVE METHOD *********************************

    @Test (expected = NullPointerException.class)
    public void Remove_EmptyNullKey_NPException(){
        map1.remove(null);
    }

    @Test (expected = NullPointerException.class)
    public void Remove_NullKey_NPException(){
        for(int i = 0; i < 450; i++){
            map1.put(i*i*i,(i+654)*i);
        }
        map1.remove(null);
    }

    @Test
    public void Remove_Empty(){
        assertEquals(null, map1.remove(156));
    }

    @Test
    public void Remove_ReturnOldValue(){
        map1.put(44, 987);
        assertEquals(987, map1.remove(44));
        assertEquals(null, map1.remove(44));
    }

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
     * 450 mappings, making it empty.</p>
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
     * <p><b>Expected Results</b>: NullPointerException thrown.</p>
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
     * <p><b>Summary</b>:putAll method test case. The test adds two times the same map to the map, then checks if the elements were stored correctly.</p>
     * <p><b>Test Case Design</b>: putAll must behave correctly
     * adding two times the same things. </p>
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
            assertEquals(true, map2.containsKey(i*i));
            assertEquals(true, map2.containsValue(i+16));
        }

        assertEquals(true, map1.isEmpty());

        map1.putAll(map2);

        assertEquals(false, map1.isEmpty());
        for(int i = 0; i < 100; i++){
            assertEquals(true, map1.containsKey(i*i));
            assertEquals(true, map1.containsValue(i+16));
        }
        
        map1.putAll(map2);
        for(int i = 0; i < 100; i++){
            assertEquals(true, map1.containsKey(i*i));
            assertEquals(true, map1.containsValue(i+16));
        }
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
     * <p><b>Test Description</b>: Different configurations have been tested:
     * empty, {1,1}, {"ciao",164}, {"ciao",0:10}</p>
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

    @Test
    public void Values(){
        assertTrue(map1.values() instanceof HCollection);
    }

    @Test
    public void KeySet(){
        assertTrue(map1.keySet() instanceof HSet);
    }

    @Test
    public void EntrySet(){
        assertTrue(map1.entrySet() instanceof HSet);
    }

    // ************************************ ENTRY CLASS ******************************

    @Test
    public void Get_ValueEmpty(){
        MapEntryAdapter me = new MapEntryAdapter(15);
        assertEquals(null, me.getValue());
    }

    @Test
    public void Get_Value(){
        MapEntryAdapter me = new MapEntryAdapter(15);
        me.setValue(164);
        assertEquals(164, me.getValue());
    }

    @Test
    public void Get_Key(){
        MapEntryAdapter me = new MapEntryAdapter(999);
        assertEquals(999, me.getKey());
    }

    @Test
    public void SetValue_Return(){
        MapEntryAdapter me = new MapEntryAdapter(15);
        me.setValue(164);
        assertEquals(164, me.getValue());
        Object ret = me.setValue("ciao");
        assertEquals(164, ret);
        assertEquals("ciao", me.getValue());
    }

    @Test
    public void Equals_Empty(){
        MapEntryAdapter me1 = new MapEntryAdapter(15);
        MapEntryAdapter me2 = new MapEntryAdapter(15);
        assertTrue(me1.equals(me2));
    }

    @Test
    public void Equals_Val1(){
        MapEntryAdapter me1 = new MapEntryAdapter(15);
        MapEntryAdapter me2 = new MapEntryAdapter(15);
        me1.setValue("cii");
        me2.setValue("cii");
        assertTrue(me1.equals(me2));
    }

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
    public void HashCode_Entry(){
        MapEntryAdapter me1 = new MapEntryAdapter(15);
        MapEntryAdapter me2 = new MapEntryAdapter(15);
        // Empty map case
        assertEquals("Entry should be equal.", true, me1.equals(me2));
        assertEquals("Hash codes should be equal.", me1.hashCode(), me2.hashCode());
    }

    @Test
    public void ToString_Entry(){
        MapEntryAdapter me1 = new MapEntryAdapter(15);
        MapEntryAdapter me2 = new MapEntryAdapter(15);
        assertEquals("Entry should be equal.", true, me1.equals(me2));
        assertEquals("toString string should be equal.", me1.toString(), me2.toString());
    }

    // **************************** TEST MAP CONSEGNA ****************************
    
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

    @Test
    public void Emptying_KeySet_ViaIterator(){
        
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
    

}
