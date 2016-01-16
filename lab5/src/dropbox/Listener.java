package dropbox;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by Michal on 2016-01-11.
 */
public class Listener implements Runnable{
    Sender sender;
    private ExecutorService executorService;
    ConfigManager configManager;
    Path dir;
    StatsWorker statsWorker;
    GUIView guiView;

    public Listener( int threadAmount, GUIView guiView) throws IOException {
        configManager = new ConfigManager();
        sender = new Sender();
        executorService = Executors.newFixedThreadPool(threadAmount);
        this.guiView = guiView;
        statsWorker = new StatsWorker(new StatsService(sender), guiView);
    }

    @Override
    public void run() {
        try {

            dir = Paths.get(configManager.getProperty(AppKeys.DIR));
            sendFilesOnStart();
            WatchService watchService = dir.getFileSystem().newWatchService();
            dir.register(watchService, ENTRY_CREATE);
            WatchKey watchKey = watchService.take();

            while (true) {
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent event : events) {
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    if (event.kind() == ENTRY_CREATE) {
                        sendInThread(dir.toString() + "/" + fileName.toString());
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
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
