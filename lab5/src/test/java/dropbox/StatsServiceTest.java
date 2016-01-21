package dropbox;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Michal on 2016-01-16.
 */
public class StatsServiceTest {

    @Test
    public void testGetStats() throws Exception {
        ConfigManager configManager = new ConfigManager();
        StatsService statsService = new StatsService(new Sender(configManager), configManager);
        statsService.getStats();
        Assert.assertEquals(statsService.getStats(), 0);
    }
}