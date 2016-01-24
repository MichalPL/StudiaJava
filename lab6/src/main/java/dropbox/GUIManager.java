package dropbox;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Michal on 2016-01-15.
 */
public class GUIManager {
    private ConfigManager configManager;
    private GUIView view;
    private Stage primaryStage;
    private StatsService statsService;
    private DialogClass dialogClass = new DialogClass();

    public GUIManager(GUIView view, Stage primaryStage) {
        try {
            configManager = new ConfigManager();
        } catch (ReadFileException e) {
            dialogClass.showDialog("Blad ustawien", e.toString());
        }
        this.view = view;
        this.primaryStage = primaryStage;

        startListenerStartBtn();
        startListenerExitBtn();
    }

    public void startListenerExitBtn() {
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }


    private void startListener() throws SQLException {
        MySqlConnector mySqlConnector = new MySqlConnector();
        mySqlConnector.truncateDB();
        WebParser webParser = new WebParser(mySqlConnector, configManager);
        statsService = new StatsService(webParser, configManager);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService statsThread = Executors.newSingleThreadExecutor();
        statsThread.submit((Runnable) () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(Integer.parseInt(configManager.getProperty(AppKeys.NUMBER)));
                } catch (InterruptedException e) {
                    dialogClass.showDialog("Blad", "Blad watkow...");
                }
                ArrayList<String> list = webParser.getLinks();
                for (String aList : list) view.appendTextinTextArea(aList + "\n");
                view.setInfo("Dodawanie z predkoscia: " + statsService.getStats() + " linkow/s");
                webParser.clearArray();
            }
        });
        executorService.submit(new Listener(Integer.parseInt(configManager.getProperty(AppKeys.THREADS)), configManager, mySqlConnector.getLinks("base_links"), webParser));
    }

    public void startListenerStartBtn() {
        Button btn = view.getButton("startButton");
        btn.setOnAction(event -> {
            btn.setDisable(true);
            try {
                startListener();
            } catch (SQLException e) {
                dialogClass.showDialog("Blad", "Blad w rozpoczeciu...");
            }
        });
    }

}
