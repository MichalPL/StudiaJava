package parserCSV;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static parserCSV.PeselParser.isValid;

/**
 * Created by Michal on 2015-12-12.
 */
public class PeselParserTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldBeExceptionForNull() {
        isValid(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldBeExceptionForString() {
        isValid("aaaa");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldBeExceptionForTooLongInput() {
        isValid("3874934343434343344378");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldBeExceptionForTooShortInput() {
        isValid("9878");
    }

    @Test
    public void shouldBeTrueValidInput() {
        assertTrue(isValid("84053111010"));
    }

}