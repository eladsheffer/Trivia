package com.example.trivia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class TriviaController {

    private final int FIRST_QUESTION = 1;

    private Trivia trivia;
    private int questionNumber;
    private boolean isGameFinished;

    @FXML
    private Label labelQuestionNumber;

    @FXML
    private Text textQuestion;

    @FXML
    private Button buttonGame;

    @FXML
    private Button buttonNextQuestion;

    @FXML
    private Button buttonSubmit;

    @FXML
    private ToggleGroup tgAnswers;

    @FXML
    private void initialize() {
        trivia = new Trivia();
        startGame();
    }

    private void startGame() {
        this.questionNumber = FIRST_QUESTION;
        this.isGameFinished = false;
        this.buttonGame.setText("Finish Game");
        this.labelQuestionNumber.setVisible(true);
        this.buttonSubmit.setDisable(false);
        this.buttonNextQuestion.setDisable(true);
        this.trivia.startNewGame();
        newQuestion();
    }

    private void newQuestion() {

        if (this.isGameFinished) {
            finishGame();
            return;
        }

        this.trivia.pickNewQuestion();
        this.labelQuestionNumber.setText(this.questionNumber + ". ");
        this.textQuestion.setText(this.trivia.getQuestion().getQuestion());
        List<Toggle> toggles = this.tgAnswers.getToggles();
        List<String> answers = trivia.getQuestion().getAnswers();
        for (int i = 0; i < toggles.size() && i < answers.size(); i++) {
            RadioButton radioButton = (RadioButton) toggles.get(i);
            radioButton.setVisible(true);
            radioButton.setText((char) (i + 'A') + ".  " + answers.get(i));
            radioButton.setUserData(answers.get(i));
        }

        this.questionNumber++;
        if (this.trivia.isEmpty()) {
            this.isGameFinished = true;
        }
    }

    private void disableToggleAnswers() {
        List<Toggle> toggles = this.tgAnswers.getToggles();
        for (Toggle toggle : toggles) {
            RadioButton radioButton = (RadioButton) toggle;
            radioButton.setDisable(true);

        }
    }

    private void clearAnswers() {
        List<Toggle> toggles = this.tgAnswers.getToggles();
        for (Toggle toggle : toggles) {
            RadioButton radioButton = (RadioButton) toggle;
            radioButton.setBackground(null);
            radioButton.setSelected(false);
            radioButton.setUserData(null);
            radioButton.setDisable(false);
            radioButton.setText("");
            if (this.isGameFinished) {
                radioButton.setVisible(false);
            }

        }
    }

    @FXML
    void buttonSubmitClicked(ActionEvent event) {
        if (this.tgAnswers.getSelectedToggle() == null || this.tgAnswers.getSelectedToggle().getUserData() == null) {
            return;
        }

        RadioButton selectedToggle = (RadioButton) this.tgAnswers.getSelectedToggle();
        String answerByUser = (String) selectedToggle.getUserData();
        if (this.trivia.isRightAnswer(answerByUser)) {
            selectedToggle.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), null)));
        } else {
            selectedToggle.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), null)));
            markRightAnswer();
        }

        disableToggleAnswers();
        this.buttonNextQuestion.setDisable(false);
        this.buttonSubmit.setDisable(true);
    }

    private void markRightAnswer() {
        for (Toggle toggle : this.tgAnswers.getToggles()) {
            if (this.trivia.isRightAnswer((String) toggle.getUserData())) {
                ((RadioButton) toggle).setBackground(
                        new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), null)));
            }
        }
    }

    @FXML
    void buttonNextQuestionClicked(ActionEvent event) {
        clearAnswers();
        this.buttonNextQuestion.setDisable(true);
        this.buttonSubmit.setDisable(false);
        newQuestion();
    }


    @FXML
    void buttonGameClicked(ActionEvent event) {

        if (buttonGame.getText().equalsIgnoreCase("finish game")) {
            finishGame();
        } else {
            startGame();
        }
    }

    private void finishGame() {
        this.isGameFinished = true;
        clearAnswers();
        this.textQuestion.setText("Your score is: " + this.trivia.getScore());
        this.buttonGame.setText("Start New Game");
        this.buttonSubmit.setDisable(true);
        this.buttonNextQuestion.setDisable(true);
        clearAnswers();
        labelQuestionNumber.setVisible(false);
        this.trivia.finishGame();
    }

}
