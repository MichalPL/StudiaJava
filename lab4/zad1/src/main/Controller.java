package main;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    private Model model;
    private View view;
    private Question quest;
    private int questNumber;
    private int correctAnswers;
    private RadnomNumber rNumber;
    private ArrayList<Integer> randomList;
    private int allQuestCount;
    private Printer prnt;

    public Controller(Model model, View view) throws SQLException, ClassNotFoundException {
        this.model = model;
        this.view = view;
        questNumber = 0;
        correctAnswers = 0;
        rNumber = new RadnomNumber();
        allQuestCount = model.getCount();
        randomList = rNumber.getRandomNumbers(allQuestCount);
        prnt = new Printer(view);

        prnt.setInfo(questNumber, correctAnswers);
        fillQuestion();
        startListenerCheckButton(view.getButton("check"));
        startListenerNextButton(view.getButton("next"));
    }

    private void startListenerCheckButton(Button btn) {
        btn.setOnAction(event -> {
            btn.setDisable(true);
            if(isCorrect(getCheckedId())) {
                prnt.setButtonText(btn, "Poprawne");
                correctAnswers++;
            }
            else {
                prnt.setButtonText(btn, "Błędne");
            }
            prnt.setInfo(questNumber, correctAnswers);
        });
    }

    private void startListenerNextButton(Button btn) {
        Button checkBtn = view.getButton("check");
        btn.setOnAction(event -> {
            checkBtn.setDisable(false);
            prnt.setButtonText(checkBtn, "Sprawdź");
            prnt.setInfo(questNumber, correctAnswers);
            fillQuestion();

            if ((questNumber >= allQuestCount)) {
                btn.setDisable(true);
                prnt.setButtonText(btn, "KONIEC TESTU");
            }

        });
    }

    public void fillQuestion() {
        try {
            quest = model.getQuest(randomList.get(questNumber));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> al = quest.getAnswers();
        view.setAnswers(al, quest.getText());
        view.draw();
        questNumber++;
    }

    public boolean isCorrect(int checkedRadio) {
        return Objects.equals(quest.getCorrect(), checkedRadio);
    }

    public int getCheckedId() {
        int count = view.getQuestCount();
        for (int i = 0; i < count; i++) {
            if(view.getRadio(i + 1).isSelected()) {
                return i + 1;
            }
        }
        return 0;
    }

}
