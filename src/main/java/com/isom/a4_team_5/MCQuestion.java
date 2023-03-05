/**
 * @author hushiyan
 * @date 2/2/23 8:17 PM
 */
package com.isom.a4_team_5;

public class MCQuestion extends Question {
    private String[] answerChoices;
    private boolean[] correctAnswers;

    public MCQuestion(String topic, String difficultyLevel, String questionText, String[] answerChoices, boolean[] correctAnswers) {
        super(topic, difficultyLevel, questionText);
        this.answerChoices = answerChoices;
        this.correctAnswers = correctAnswers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answerChoices.length; i++) {
            sb.append((char) ('A' + i)).append(".").append(answerChoices[i]).append("  ");
        }
        sb.append("\n").append("Answer: ");
        for (int i = 0; i < answerChoices.length; i++) {
            if (correctAnswers[i]) sb.append((char) ('A' + i)).append(" ");
        }

        return super.toString() + " Select all that apply.\n" + sb.toString() + "\n";

    }

    @Override
    public String getAnswer() {
//        return correctAnswers
        StringBuilder res = new StringBuilder();
        for(int i=0;i<correctAnswers.length;i++){
            if(correctAnswers[i]) res.append((char) (i + 'A')).append(" ");
        }
        return res.toString();
    }

    @Override
    public String getCompleteQuestionText(){
        StringBuilder res = new StringBuilder();
        res.append(super.getCompleteQuestionText()).append(" Select all that apply\n");
        for (int i = 0; i < answerChoices.length; i++) {
            res.append((char) ('A' + i)).append(".").append(answerChoices[i]).append("  ");
        }
        res.append("\n");
        return res.toString();
    }
}
