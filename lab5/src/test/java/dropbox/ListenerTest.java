package dropbox;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Michal on 2016-01-16.
 */
public class ListenerTest {
    private Path path = Paths.get("aaaa");
    @Test
    public void shouldBeSame() {
        Assert.assertEquals(path.toString(), "aaaa");
    }

    @Test
    public void shouldBeNull() throws IOException, InterruptedException {
        new Listener(10, null, null).sendInThread("");
    }
}