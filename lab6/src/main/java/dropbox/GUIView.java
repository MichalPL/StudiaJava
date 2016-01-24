package dropbox;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    public TextArea getTextArea()
    {
        return (TextArea) root.lookup("#links");
    }

    public Button getButton(String id)
    {
        return (Button) root.lookup("#" + id);
    }

    public void appendTextinTextArea(String s) {
        Platform.runLater(() -> getTextArea().appendText(s));
    }

    public void setInfo(String s)
    {
        Platform.runLater(() -> getInfo().setText(s));
    }
}
