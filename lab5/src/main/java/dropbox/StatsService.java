package dropbox;

/**
 * Created by Michal on 2016-01-11.
 */
public class StatsService {

    private Sender sender;
    private ConfigManager configManager;

    public StatsService(Sender sender, ConfigManager configManager) {
        this.sender = sender;
        this.configManager = configManager;
    }

    public int getStats() {
        return sender.getStats()/Integer.parseInt(configManager.getProperty(AppKeys.NUMBER));
    }
}
