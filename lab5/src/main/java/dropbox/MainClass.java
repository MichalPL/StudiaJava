package dropbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Michal on 2016-01-11.
 */
public class MainClass extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui.fxml"));
        primaryStage.setTitle("MyDropbox");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        GUIView guiView = new GUIView(root);
        new GUIManager(guiView, primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
