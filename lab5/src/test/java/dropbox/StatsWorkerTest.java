package dropbox;

import org.junit.Test;

/**
 * Created by Michal on 2016-01-16.
 */
public class StatsWorkerTest {

    @Test(expected = NullPointerException.class)
    public void ShouldBeNullException() {
        new StatsWorker(null).generateStats();
    }
}