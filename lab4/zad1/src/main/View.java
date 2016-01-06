package main;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Michal on 2015-12-29.
 */
public class View {
    private int questCount = 0;
    private ArrayList<String> answers;
    private String text = "Pytanie...";
    private Parent root;

    public View(Parent root) {
        answers = new ArrayList<>();
        this.root = root;
    }

    public void setAnswers(ArrayList<String> answers, String text) {
        this.answers = answers;
        questCount = answers.size();
        this.text = text;
    }

    public void draw() {
        Label question = (Label) root.lookup("#quest");
        question.setText(text);

        ScrollPane answerPanel = (ScrollPane) root.lookup("#answerPanel");
        VBox content = new VBox();
        final ToggleGroup group = new ToggleGroup();
        answerPanel.setContent(content);
        if(answers.size() >= questCount) {
            for (int i = 0; i < questCount; i++) {
                RadioButton rb = new RadioButton();
                rb.setId("rb" + (i+1));
                rb.setToggleGroup(group);
                rb.setText((i + 1) + ". " + answers.get(i));
                rb.setPadding(new Insets(5, 5, 5, 5));
                content.getChildren().add(rb);
            }
        }
        else {
            Label lbl = new Label("Błąd wczytywania odpowiedzi...");
            content.getChildren().add(lbl);
        }
    }

    public Button getButton(String id) {
        return (Button) root.lookup("#" + id);
    }

    public RadioButton getRadio(int id) {
        return (RadioButton) root.lookup("#rb" + id);
    }

    public void setLabel(String id, String text) {
        Label lbl = (Label) root.lookup("#" + id);
        lbl.setText(text);
    }

    public int getQuestCount() {
        return questCount;
    }



}
