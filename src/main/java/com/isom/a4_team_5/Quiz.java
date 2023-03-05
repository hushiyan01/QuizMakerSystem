package com.isom.a4_team_5;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Optional;

/**
 * @author hushiyan
 * @date 1/30/23 2:16 PM
 */


public class Quiz {
    private String title;
    private LocalDate openDate;
    private LocalDate dueDate;
    private LinkedList<QuizItem> quizItems = new LinkedList<>();

    public Quiz(String title, LocalDate openDate, LocalDate dueDate) {
        this.title = title;
        this.openDate = openDate;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void addQuestion(Question question, int score) {
        quizItems.add(new QuizItem(question, score));
    }

    public int computeTotalPoints() {
        Optional<Integer> res = quizItems.stream()
                .map(QuizItem::getScore)
                .reduce(Integer::sum);
        return res.isEmpty() ? 0 : res.get();
    }

    public void printQuizToFile(){
        //TODO
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(title+".txt");
            String quizContext = getQuizString();
            fileWriter.write(quizContext);
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printQuizSolutionToFile(){ //filePath??
        //TODO
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(title+"Soln.txt");
            String solnContext = getSolutionString();
            fileWriter.write(solnContext);
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String getQuizString(){
        String od = openDate.getMonth().getValue() + "/" + openDate.getDayOfMonth() + "/" + openDate.getYear();
        String dd = dueDate.getMonth().getValue() + "/" + dueDate.getDayOfMonth() + "/" + dueDate.getYear();

        String sb = ""+
                "----------------------------------------------------------\n"+
                title+"(  "+computeTotalPoints()+" points  )\n\n"+
                "Open Date:" + od +"\t"+ "Due Date:" + dd+ "\n"+
                "----------------------------------------------------------\n\n";
        for(int i=0;i<quizItems.size();i++){
            sb += ((i+1) + ". (" + quizItems.get(i).getScore()+ " points)\n");
            sb += quizItems.get(i).getQuestion().getCompleteQuestionText();
            sb += "\n\n\n";
        }
        return sb;
    }


    /**
     *
     * @return
     */
    private String getSolutionString(){
        String res = "";
        res += "----------------------------------------------------------\n" +
                title+"( "+computeTotalPoints()+" points )\n" +
                "----------------------------------------------------------\n\n";
        for (int i=0;i<quizItems.size();i++){
            res += ((i+1) + ". (" + quizItems.get(i).getScore()+" points)\t");
            res += (quizItems.get(i).getQuestion().getAnswer() + "\n\n");
        }

        return res;
    }

}
