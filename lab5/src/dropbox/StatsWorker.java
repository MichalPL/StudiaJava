package dropbox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michal on 2016-01-11.
 */
public class StatsWorker {
    private StatsService statsService;
    ScheduledExecutorService scheduledExecutorService;
    GUIView guiView;

    public StatsWorker(StatsService statsService, GUIView guiView) {
        this.statsService = statsService;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
        this.guiView = guiView;
    }

    public void generateStats() {
        guiView.setInfo("Wyslano: " + statsService.getStats() + " plikow/s");
    }

    Runnable task = () -> {
        try{
            TimeUnit.SECONDS.sleep(10);
            generateStats();
        } catch (InterruptedException e) {

        }
    };
}
