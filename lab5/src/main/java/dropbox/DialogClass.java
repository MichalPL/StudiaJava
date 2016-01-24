package dropbox;

import javafx.scene.control.Alert;

/**
 * Created by Michal on 2016-01-21.
 */
public class DialogClass {
    public void showDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
