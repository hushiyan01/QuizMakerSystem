package com.isom.a4_team_5;

/**
 * @author hushiyan
 * @date 2/10/23 11:05 PM
 */
public class TFQuestion extends Question{

    private boolean correctAnswer;

    public TFQuestion(String topic, String difficultyLevel, String questionText, boolean correctAnswer) {
        super(topic, difficultyLevel, questionText);
        this.correctAnswer = correctAnswer;
    }

    /**
     * example question format
     *
     * (Topic: Operators, Difficulty Level: Hard)
     * The statement double x = 5; will compile without errors. A. True B. False
     * Answer: true
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + " A. True B. False\n"
                + "Answer: " + correctAnswer
                + "\n";
    }

    @Override
    public String getAnswer() {
        return String.valueOf(correctAnswer);
    }

    @Override
    public String getCompleteQuestionText(){
        return super.getCompleteQuestionText() + "\n" + "A. True     B. False" + "\n";
    }



}
