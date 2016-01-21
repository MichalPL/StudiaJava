package dropbox;

import java.io.File;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by Michal on 2016-01-11.
 */
public class Listener implements Runnable{
    private Sender sender;
    private ExecutorService executorService;
    private ConfigManager configManager;
    private Path dir;
    private WatchKey watchKey;

    public Listener( int threadAmount, ConfigManager configManager, Sender sender){
        executorService = Executors.newFixedThreadPool(threadAmount);
        this.sender = sender;
        this.configManager = configManager;
    }

    @Override
    public void run() {
        try {
            dir = Paths.get(configManager.getProperty(AppKeys.DIR));
            sendFilesOnStart();
            WatchService watchService = dir.getFileSystem().newWatchService();
            dir.register(watchService, ENTRY_CREATE);
            watchKey = watchService.take();
        } catch (Exception e) {
            System.out.println(e.toString()); //nie jest wazne dla uzytkownika
        }

        while (true) {
            List<WatchEvent<?>> events = watchKey.pollEvents();
            for (WatchEvent event : events) {
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                if (event.kind() == ENTRY_CREATE) sendInThread(dir.toString() + "/" + fileName.toString());
            }
        }
    }

    public void sendFilesOnStart()
    {
        File file = new File(dir.toString());
        File[] files = file.listFiles();
        if(files != null)
            for(File f : files) {
                    sendInThread(f.toString());
            }
    }

    public void sendInThread(String path) {
        executorService.submit(new FileWorker(sender, path));
    }
}
