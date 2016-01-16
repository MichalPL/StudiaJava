package dropbox;

/**
 * Created by Michal on 2016-01-11.
 */
public class StatsService {

    private Sender sender;

    public StatsService(Sender sender) {
        this.sender = sender;
    }

    public int getStats() {
        return sender.getStats()/10;
    }
}
