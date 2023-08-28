package com.example.trivia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionsCollection {

    private final int NUMBER_OF_LINES_IN_QUESTION = 5;
    private final int QUESTION_LINE = 0;
    private final int RIGHT_ANSWER_LINE = 1;
    private List<Question> questions;

    public QuestionsCollection() {
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void loadQuestions() {
        Scanner input = null;
        try {
            String TRIVIA_PATH = "trivia.txt";
            input = new Scanner(new File(TRIVIA_PATH));
            int lineNumber = 0;
            Question question = null;
            while (input.hasNextLine()) {

                String line = input.nextLine();
                if (lineNumber % NUMBER_OF_LINES_IN_QUESTION == QUESTION_LINE) {
                    question = new Question();
                    question.setQuestion(line);
                    this.questions.add(question);
                } else {
                    if (lineNumber % NUMBER_OF_LINES_IN_QUESTION == RIGHT_ANSWER_LINE) {
                        question.setRightAnswer(line);
                    }
                    question.setAnswer(line);
                }

                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
        } finally {
            if (input != null) { // Exception might have happened at constructor
                input.close(); // closes FileInputStream too
            }
        }
    }
}
