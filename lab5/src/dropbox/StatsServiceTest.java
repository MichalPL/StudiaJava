package dropbox;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Michal on 2016-01-16.
 */
public class StatsServiceTest {

    @Test
    public void testGetStats() throws Exception {
        StatsService statsService = new StatsService(new Sender());
        statsService.getStats();
        Assert.assertEquals(statsService.getStats(), 0);
    }
}