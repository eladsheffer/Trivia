package com.example.trivia;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> answers;
    private String rightAnswer;

    public Question() {
        answers = new ArrayList<>();
    }

    public Question(Question other) {
        this.question = other.question;
        this.rightAnswer = other.rightAnswer;
        this.answers = new ArrayList<>();
        this.answers.addAll(other.answers);
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answers.add(answer);
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
