package dropbox

import org.junit.Test

import static junit.framework.Assert.assertSame

/**
 * Created by Michal on 2016-01-16.
 */
class ConfigManagerTest {
    private ConfigManager configManager = new ConfigManager();

    @Test
    public void shouldReturn10() {
        assertSame(10,configManager.getProperty(AppKeys.THREADS));
    }

    @Test(expected = NullPointerException.class)
    public void shouldBeNullException() {
        configManager.getProperty(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldBeNullExeption() {
        configManager.setProperty(null,null);
    }
}
