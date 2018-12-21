import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void testMain(){
        Assert.assertTrue(true);
    }

    @Test
    public void testFail(){
        Assert.assertFalse(false);
    }

}
