package com.isom.a4_team_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hushiyan
 * @date 2/2/23 8:08 PM
 */
public class QuizMakerSystem {

    private static LinkedList<Quiz> quizzes = new LinkedList<>();
    private static LinkedList<Question> questions = new LinkedList<>();

    public static void addMCQuestion(String topic, String difficultyLevel, String questionText, String[] answerChoices, boolean[] correctAnswers) {
        questions.add(new MCQuestion(topic, difficultyLevel, questionText, answerChoices, correctAnswers));
    }

    public static void addTFQuestion(String topic, String difficultyLevel, String questionText, boolean correctAnswer) {
        questions.add(new TFQuestion(topic, difficultyLevel, questionText, correctAnswer));
    }

    public static void addNAQuestion(String topic, String difficultyLevel, String questionText, double correctAnswer) {
        questions.add(new NAQuestion(topic, difficultyLevel, questionText, correctAnswer));
    }

    public static void printAllQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).toString());
        }
    }

    public static void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public static Quiz searchQuiz(String quizTitle) {
        Optional<Quiz> res = quizzes.stream()
                .filter(quiz -> quiz.getTitle().equals(quizTitle))
                .findFirst();
        if (res.isEmpty()) return null;
        else return res.get();
    }

    public static String[] getQuizTitles() {
        String[] res = new String[quizzes.size()];
        for(int i=0;i<quizzes.size();i++){
            res[i] = quizzes.get(i).getTitle();
        }
        return res;
    }

    public static void printQuizQuestionsAndSolutionsToFile(String quizTitle) {
        Quiz q = QuizMakerSystem.searchQuiz(quizTitle);
//        assert q!=null;
        q.printQuizToFile();
        q.printQuizSolutionToFile();
    }

    public static LinkedList<Question> getQuestions() {
        return questions;
    }

    public static void importQuestionsFromFile(String filePath) throws Exception {
        Scanner s = null;
        boolean duplicated = false;
        try {
            File f = new File(filePath);
            s = new Scanner(f);
            s.useDelimiter("\n");

            while (s.hasNextLine()) {
                String question = s.nextLine();
                String[] properties = question.split(",");
                try {
                    addQuestionByProperties(properties);
                } catch (Exception e) {
                    Logger.getLogger("QuizMakerSystem").log(Level.INFO, ">>>>>>>>>>>  " + e.getMessage());
                    duplicated = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) s.close();
        }
        if (duplicated) throw new Exception("some questions have been added, they won't be added again!");
    }

    private static void addQuestionByProperties(String[] properties) throws Exception {
        assert properties.length == 6;
        String topic, difficultyLevel, questionText;
        topic = properties[1];
        difficultyLevel = properties[2];
        questionText = properties[3];
        if (checkQuestion(questionText))
            throw new Exception("the following question:" + questionText + "\t has already imported!");

        switch (properties[0]) {
            case "MC" -> {                                                                                          //multiple choice question
                String[] choices = properties[4].split("'");
                String[] answers = properties[5].split(" ");
                boolean[] correctAnswers = new boolean[choices.length];
                for (String an : answers) {
                    correctAnswers[an.charAt(0) - 'A'] = true;
                }
                addMCQuestion(topic, difficultyLevel, questionText, choices, correctAnswers);
            }
            case "TF" -> addTFQuestion(topic, difficultyLevel, questionText, properties[5].toUpperCase(Locale.ROOT).contains("TRUE"));       //True/False question
            case "NA" -> addNAQuestion(topic, difficultyLevel, questionText, Double.parseDouble(properties[5]));    //Number question
            default -> {
            }
        }
    }

    private static boolean checkQuestion(String questionText) {
        return questions.stream()
                .anyMatch(q -> q.getCompleteQuestionText().contains(questionText));
    }
}
