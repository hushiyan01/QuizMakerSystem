package com.isom.a4_team_5;

/**
 * @author hushiyan
 * @date 1/30/23 2:34 PM
 */
public class QuizItem {
    private int score;
    private Question question;

    public QuizItem(Question question, int score){
        this.question = question;
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public Question getQuestion(){
        return question;
    }
}
