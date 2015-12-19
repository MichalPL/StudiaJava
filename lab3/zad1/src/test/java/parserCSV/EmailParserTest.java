package parserCSV;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static parserCSV.EmailParser.isValid;

/**
 * Created by Michal on 2015-12-12.
 */
public class EmailParserTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shoouldBeExceptionForNull() {
        isValid(null);
    }

    @Test
    public void shouldBeTrueValidInput() {
        assertTrue(isValid("abcd@abcd.as"));
    }

    @Test
    public void shouldBeFalseInValidInput() {
        assertFalse(isValid("abcd"));
    }

}