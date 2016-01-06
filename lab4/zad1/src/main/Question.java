package main;

import java.util.ArrayList;

/**
 * Created by Michal on 2015-12-29.
 */
public class Question {
    private int id;
    private String text;
    private ArrayList<String> answers;
    private int correct;

    public Question(int id, String text, ArrayList<String> answers, int correct) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getCorrect() {
        return correct;
    }
}
