package parserCSV;
import org.junit.Before;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Michal on 2015-12-16.
 */
public class ServiceTest{

    private Service serv;
    @Before
    public void  before() throws IOException {
        ConfigManager cmanager = new ConfigManager();
        serv = new Service(cmanager.getProperty("filename"));
    }

    @Test(expectedExceptions = IOException.class)
    public void shouldBeIOExceptionForNotExistingFile() throws IOException {
        serv.readFromFile("aaatjyuccjju");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldBeIOExceptionForNull() throws IOException {
        serv.readFromFile(null);
    }
}
