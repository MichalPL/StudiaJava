package main;

import javafx.scene.control.Button;

/**
 * Created by Michal on 2016-01-06.
 */
public class Printer {
    View view;

    public Printer(View view)
    {
        this.view = view;
    }

    public void setInfo(int questNumber, int correctAnswers)
    {
        if(questNumber != 0)
            view.setLabel("counter",correctAnswers + "/" + questNumber + " || Poprawnych odpowiedzi: " + (int)(Math.floor(((double)correctAnswers/(double)questNumber)*100)) + "%");
        else
            view.setLabel("counter","0/0 || Poprawnych odpowiedzi: 0%");
    }

    public void setButtonText(Button btn, String str)
    {
        btn.setText(str);
    }

}
