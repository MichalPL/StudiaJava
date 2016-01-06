package main;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    Model model;
    View view;
    Question quest;
    int questNumber;
    int correctAnswers;
    RadnomNumber rNumber;
    ArrayList<Integer> randomList;
    int allQuestCount;
    Printer prnt;

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
            try {
                if(isCorrect(getCheckedId())) {
                    prnt.setButtonText(btn, "Poprawne");
                    correctAnswers++;
                }
                else {
                    prnt.setButtonText(btn, "Błędne");
                }
                prnt.setInfo(questNumber, correctAnswers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void startListenerNextButton(Button btn) {
        Button checkBtn = view.getButton("check");
        btn.setOnAction(event -> {
            try {

                checkBtn.setDisable(false);
                prnt.setButtonText(checkBtn, "Sprawdź");
                prnt.setInfo(questNumber, correctAnswers);
                fillQuestion();

                if((questNumber >= allQuestCount)) {
                    btn.setDisable(true);
                    prnt.setButtonText(btn, "KONIEC TESTU");
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void fillQuestion() throws SQLException, ClassNotFoundException {
        quest = model.getQuest(randomList.get(questNumber));
        ArrayList<String> al = quest.getAnswers();
        view.setAnswers(al, quest.getText());
        view.draw();
        questNumber++;
    }

    public boolean isCorrect(int checkedRadio) throws SQLException {
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
