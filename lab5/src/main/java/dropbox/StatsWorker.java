package dropbox;

/**
 * Created by Michal on 2016-01-11.
 */
public class StatsWorker {
    private StatsService statsService;

    public StatsWorker(StatsService statsService) {
        this.statsService = statsService;
    }

    public int generateStats() {
       return statsService.getStats();
    }
}
