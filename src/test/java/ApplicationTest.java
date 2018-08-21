import com.application.Application;
import org.junit.*;
import static junit.framework.Assert.*;

public class ApplicationTest {

    private Application app;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before ApplicationTest.class");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("After ApplicationTest.class");
    }

    @Before
    public void initTest() {
         app = new Application();
    }

    @After
    public void afterTest() {
        app = null;
    }

    @Test
    public void testCalcMin() throws Exception {
        //translates hours into minutes
        // args should be "String"
        // hour * 60 + min = 02 * 60 + 30 = 150

        int method = app.calcMin("02","30");

        assertEquals(150, method);

        method = app.calcMin("2","40");

        assertEquals(160, method);
    }

    @Test
    public void testMainSolution() throws Exception {
        //test 1
        String test1 = "src/main/java/com/application/test1.txt";
        int mainMethod = app.solution(test1);
        assertEquals(4, mainMethod);
        //test 2
        String test2 = "src/main/java/com/application/test2.txt";
        mainMethod = app.solution(test2);
        assertEquals(3, mainMethod);
    }

}


