package dropbox;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michal on 2016-01-15.
 */
public class GUIManager {
    private ConfigManager configManager;
    private GUIView view;
    private Stage primaryStage;
    private StatsWorker statsWorker;
    private DialogClass dialogClass = new DialogClass();

    public GUIManager(GUIView view, Stage primaryStage) {
        try {
            configManager = new ConfigManager();
        } catch (ReadFileException e) {
            dialogClass.showDialog("Blad ustawien", e.toString());
        }
        this.view = view;
        this.primaryStage = primaryStage;

        startListenerBrowseBtn();
        startListenerStartBtn();
        startListenerExitBtn();
    }

    public void startListenerExitBtn() {
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void startListenerBrowseBtn() {
        TextField textField = view.getInputDir();

        textField.setText(configManager.getProperty(AppKeys.DIR));
        Button btn = view.getButton("browseButton");
        btn.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Wybierz folder...");
            File file = directoryChooser.showDialog(null);
            if (file != null) {
                configManager.setProperty(AppKeys.DIR, file.getPath());
                textField.setText(file.getPath());
            }
        });
    }

    private void startListener() {
        Sender sender = new Sender(configManager);
        statsWorker = new StatsWorker(new StatsService(sender, configManager));

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        ExecutorService statsThread = Executors.newSingleThreadExecutor();
        statsThread.submit((Callable<Void>) () -> {
            TimeUnit.SECONDS.sleep(Integer.parseInt(configManager.getProperty(AppKeys.NUMBER)));
            view.setInfo("Przesylanie z predkoscia: " + statsWorker.generateStats() + " plikow/s");
            return null;
        });
        executorService.submit(new Listener(Integer.parseInt(configManager.getProperty(AppKeys.THREADS)),
                    configManager, sender));
    }

    public void startListenerStartBtn() {
        Button btn = view.getButton("startButton");
        btn.setOnAction(event -> {
            btn.setDisable(true);
            startListener();
        });
    }

}
