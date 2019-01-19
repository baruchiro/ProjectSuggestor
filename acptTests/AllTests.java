package acptTests;
import junit.framework.*;

public class AllTests {
    public static Test suite(){
        TestSuite suite= new TestSuite("ProjectManagement");
        suite.addTest( new TestSuite(SuggestedProjectTest.class));
        suite.addTest(new TestSuite(RegisterToProject.class));

        return suite;
    }


}
