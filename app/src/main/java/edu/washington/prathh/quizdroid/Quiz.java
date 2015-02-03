package edu.washington.prathh.quizdroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hillaryprather on 2/3/15.
 */
public class Quiz {
    public List<String> questions;
    public List<String[]> answers;
    private int id;

    public Quiz() {
        questions = new ArrayList<String>();
        answers = new ArrayList<String[]>();
        id = 0;
    }

    public void addQuestion(String question, String[] answers) {
        this.questions.add(question);
        this.answers.add(answers);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
