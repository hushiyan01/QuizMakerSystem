package com.isom.a4_team_5;

/**
 * @author hushiyan
 * @date 2/10/23 11:15 PM
 */
public class NAQuestion extends Question{
    private double correctAnswer;

    public NAQuestion(String topic, String difficultyLevel, String questionText, double correctQuestion) {
        super(topic, difficultyLevel, questionText);
        this.correctAnswer = correctQuestion;
    }

    /**
     * example question format:
     *
     * (Topic: Operations, Difficulty Level: Hard)
     * What will the statement System.out.print(“1” + 2 + 3); print?
     * Answer: 123
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + "\n"
                + "Answer: " + (int)correctAnswer
                +"\n";
    }

    @Override
    public String getAnswer() {
        return String.valueOf(correctAnswer);
    }

    @Override
    public String getCompleteQuestionText(){
        return super.getCompleteQuestionText() + "\n";
    }

}
