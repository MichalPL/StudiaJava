package dropbox;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Michal on 2016-01-16.
 */
public class SenderTest {

    @Test(expected = FileNotFoundException.class)
    public void testSendFile() throws Exception {
        File file = new File("");
        new Sender(new ConfigManager()).sendFile(file);
    }
}