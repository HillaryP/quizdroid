package edu.washington.prathh.quizdroid;

/**
 * Created by iguest on 2/16/15.
 */
public class Quiz {
    private String question;
    private String[] answers;
    private int correctIndex;

    public Quiz() {
        this.answers = new String[4];
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectIndex() {
        return this.correctIndex;
    }

    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }
}
