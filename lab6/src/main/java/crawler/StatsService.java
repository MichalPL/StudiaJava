package crawler;
/**
 * Created by Michal on 2016-01-11.
 */
public class StatsService {

    private WebParser webParser;
    private ConfigManager configManager;

    public StatsService(WebParser webParser, ConfigManager configManager) {
        this.webParser = webParser;
        this.configManager = configManager;
    }

    public int getStats() {
        return webParser.getStats()/Integer.parseInt(configManager.getProperty(AppKeys.NUMBER));
    }
}
