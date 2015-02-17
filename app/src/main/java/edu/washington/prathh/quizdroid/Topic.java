package edu.washington.prathh.quizdroid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by iguest on 2/16/15.
 */
public class Topic {
    private String title;
    private String shortDescription;
    private String longDescription;
    private List<Quiz> qAndA;

    public Topic() {
        qAndA = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String description) {
        this.shortDescription = description;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String description) {
        this.longDescription = description;
    }

    public List<Quiz> getQAndA() {
        return this.qAndA;
    }

    public void setQAndA(List<Quiz> newData) {
        this.qAndA = newData;
    }
}
