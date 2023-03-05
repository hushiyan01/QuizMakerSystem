/**
 * @author hushiyan
 * @date 1/30/23 2:19 PM
 */
package com.isom.a4_team_5;

public abstract class Question {
    private String topic;
    private String difficultyLevel;
    private String questionText;

    public Question(String topic, String difficultyLevel, String questionText){
        this.topic = topic;
        this.difficultyLevel = difficultyLevel;
        this.questionText = questionText;
    }

    public String toString(){
        return "(Topic: "+ topic+", " + "Difficulty Level: " + difficultyLevel+")\n"+
                questionText;
    }

    public String getCompleteQuestionText(){
        return questionText;
    }

    public abstract String getAnswer();
}
