package dropbox;

import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Created by Michal on 2016-01-21.
 */
public class ConfigManagerTest {
    private ConfigManager configManager;

    public ConfigManagerTest() throws ReadFileException {
        configManager = new ConfigManager();
    }

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