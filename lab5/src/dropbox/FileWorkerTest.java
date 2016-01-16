package dropbox;

import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by Michal on 2016-01-16.
 */
public class FileWorkerTest {

    @Test(expected = FileNotFoundException.class)
    public void shouldBeFileNotFoundException() throws Exception {
        new FileWorker(new Sender(), "").call();

    }
}