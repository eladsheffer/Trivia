package com.example.trivia;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Trivia {

    private final int SCORE_FOR_RIGHT_ANSWER = 10;
    private final int PENALTY_FOR_WRONG_ANSWER = 5;
    private List<Question> questions;
    private QuestionsCollection questionsCollection;
    private Question question;
    private int score;

    public Trivia() {
        this.questionsCollection = new QuestionsCollection();
    }

    public void pickNewQuestion() {
        Random random = new Random();
        int index = random.nextInt(this.questions.size());
        this.question = this.questions.get(index);
        Collections.shuffle(this.question.getAnswers());
        this.questions.remove(index);
    }

    public void startNewGame() {
        this.score = 0;
        if (this.questionsCollection == null) {
            this.questionsCollection = new QuestionsCollection();
        }
        this.questionsCollection.loadQuestions();
        questions = this.questionsCollection.getQuestions();
    }

    public void finishGame() {
        if (this.questions != null) {
            questions.clear();
        }
    }

    public Question getQuestion() {
        return question;
    }

    public boolean isRightAnswer(String answer) {
        if (this.question.getRightAnswer().equals(answer)) {
            this.score += SCORE_FOR_RIGHT_ANSWER;
            return true;
        } else {
            if (this.score >= PENALTY_FOR_WRONG_ANSWER) {
                this.score -= PENALTY_FOR_WRONG_ANSWER;
            }
            return false;
        }
    }

    public boolean isEmpty() {
        return this.questions.size() == 0;
    }

    public int getScore() {
        return score;
    }
}
