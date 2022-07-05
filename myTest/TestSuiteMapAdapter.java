package myTest;

// JUnit imports
import org.junit.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

// Adapter package import
import myAdapter.*;

// Other imports
import java.util.Arrays;

public class TestSuiteMapAdapter {
    
    HMap map1 = null;
    HMap map2 = null;

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
    public void afterMethod()
    {
        map1 = null;
        map2 = null;
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

        assertEquals("Empty list does not have size of zero.", 0, map1.size());
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
        assertEquals("Empty list does not have size of one.", 1, map1.size());
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
        assertEquals("Empty list does not have size of one.", 5, map1.size());
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
        assertEquals("Empty list does not have size of one.", 160, map1.size());
        assertEquals("isEmpty did not returned false.", false, map1.isEmpty());
    }

    // ************************** CONTAINS KEY & VALUE METHOD **************************************

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

    //****************************** GET METHOD *******************************

    @Test
    public void Get_0_1_0(){
        assertEquals("The result should be null, the map is empty .",null, map1.get("chiave nulla"));

        map1.put(15,16.4);

        assertEquals("The result should be null, no key corresponding in this map .",null, map1.get("chi"));

        assertEquals("The element shuld be 16.4 .",16.4, map1.get(15));

        map1.remove(15);
        
        assertEquals("The result should be null, no key corresponding in this map .",null, map1.get(15));
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

    // ******************************** CLEAR METHOD **************************

    /**
     * <p><b>Summary</b>: clear method test case.</p>
     * <p><b>Test Case Design</b>: Invokes clear method on an already empty
     * map, which is a limit case.</p>
     * <p><b>Test Description</b>: Calls clear on the map, then it should be
     * equal to another empty map.</p>
     * <p><b>Pre-Condition</b>: Map is empty.</p>
     * <p><b>Post-Condition</b>: Map is still empty.</p>
     * <p><b>Expected Results</b>: Map is equal to another empty map.</p>
     */
    @Test
    public void Clear_Empty(){
        map1.clear();
        assertEquals("Map should be empty.", true, map1.isEmpty());
        assertEquals("Map should be empty.", true, map1.equals(map2));
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

    @Test 
    public void PutAll_SameEmptyMap(){
        assertEquals(true, map1.isEmpty());
        map1.putAll(map1);
        assertEquals(true, map1.isEmpty());
    }

    @Test 
    public void PutAll_2TimesPut(){
        for(int i = 0; i < 100; i++)
            map2.put(i*i, i+16);

        map1.putAll(map2);
        for(int i = 0; i < 100; i++)
            assertEquals(true, map1.containsKey(i*i));
        
        map1.putAll(map2);
        for(int i = 0; i < 100; i++)
            assertEquals(true, map1.containsKey(i*i));
    }



}
