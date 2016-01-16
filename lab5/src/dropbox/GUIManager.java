package dropbox;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Michal on 2016-01-15.
 */
public class GUIManager {
    ConfigManager configManager;
    GUIView view;
    Stage primaryStage;

    public GUIManager(GUIView view, Stage primaryStage) throws IOException {
        configManager = new ConfigManager();
        this.view = view;
        this.primaryStage = primaryStage;
        startListenerBrowse();
        startListenerStart();
        startListenerExit();
    }

    public void startListenerExit()
    {
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void startListenerBrowse()
    {
        TextField textField = view.getInputDir();//(TextField)root.lookup("#inputDir");

        textField.setText(configManager.getProperty(AppKeys.DIR));
        Button btn = view.getButton("browseButton"); //Button) root.lookup("#browseButton");
        btn.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Wybierz folder...");
            File file = directoryChooser.showDialog(null);
            if(file != null) {
                configManager.setProperty(AppKeys.DIR, file.getPath());
                textField.setText(file.getPath());
            }
        });
    }

    private void startListener() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            executorService.submit(new Listener(Integer.parseInt(new ConfigManager().getProperty(AppKeys.THREADS)), view));
            // executorService.submit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startListenerStart()
    {
        Button btn = view.getButton("startButton");// (Button) root.lookup("#startButton");
        btn.setOnAction(event -> {
            btn.setDisable(true);
            startListener();
        });
    }

}
