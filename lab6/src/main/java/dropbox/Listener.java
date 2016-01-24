package dropbox;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by Michal on 2016-01-11.
 */
public class Listener implements Runnable{
    private ExecutorService executorService;
    private ConfigManager configManager;
    private ArrayList<String> link;
    private WebParser webParser;

    public Listener( int threadAmount, ConfigManager configManager,  ArrayList <String> link, WebParser webParser){
        executorService = Executors.newFixedThreadPool(threadAmount);
        this.configManager = configManager;
        this.link = link;
        this.webParser = webParser;
    }

    @Override
    public void run() {
        for(int i = 0; i < link.size(); i++) {
            parseInThread(link.get(i) + configManager.getProperty(AppKeys.WORD));
        }
    }

    public void parseInThread(String link){
        executorService.submit(new LinkWorker(webParser, configManager, link));
    }
}
