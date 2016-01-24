package dropbox;

import java.util.concurrent.Callable;

/**
 * Created by Michal on 2016-01-24.
 */
public class LinkWorker implements Callable {
    private WebParser webParser;
    private String startUrl;
    private ConfigManager configManager;

    public LinkWorker(WebParser webParser, ConfigManager configManager, String startUrl) {
        this.webParser = webParser;
        this.configManager = configManager;
        this.startUrl = startUrl;
    }


    @Override
    public Object call() throws Exception {
        webParser.parse(configManager.getProperty(AppKeys.WORD), startUrl);
        return null;
    }
}
