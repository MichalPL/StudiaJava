package dropbox;

import org.junit.Test;

/**
 * Created by Michal on 2016-01-16.
 */
public class GUIManagerTest {

    @Test(expected = RuntimeException.class)
    public void shouldBeRuntimeException() throws Exception {
        new GUIManager(null, null);
    }
}