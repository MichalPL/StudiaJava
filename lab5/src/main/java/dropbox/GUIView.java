package dropbox;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Michal on 2016-01-15.
 */
public class GUIView {
    Parent root;

    public GUIView(Parent root) {
        this.root = root;
    }

    public Label getInfo()
    {
        return (Label) root.lookup("#info");
    }

    public TextField getInputDir()
    {
        return (TextField) root.lookup("#inputDir");
    }

    public Button getButton(String id)
    {
        return (Button) root.lookup("#" + id);
    }

    public void setInfo(String s)
    {

        Platform.runLater(() -> getInfo().setText(s));
    }
}
