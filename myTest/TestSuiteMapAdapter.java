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
    
    int count = 0;
    HMap m = null;
    HSet s1 = null;
    HSet ks = null;
    HIterator iter = null;
    HCollection c = null;

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

    String[] args = {"pippo", "pluto", "qui", "ciccio", "gambatek"};

    /**
     * Before JUnit method. It initializes the two lists and the
     * collection used in this suite.
     */
    @Before
    public void beforeMethod()
    {
        m = new MapAdapter();
        for(int i=0;i<args.length;i++)
		{
			m.put(args[i], args[i]);
		}
        
    }

}
