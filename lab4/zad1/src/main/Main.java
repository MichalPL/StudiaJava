package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("okienko.fxml"));
        primaryStage.setTitle("Testownik Pro");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Model model = new Model();


        View view = new View(root);
        Controller controller = new Controller(model, view);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
