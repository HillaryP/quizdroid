package edu.washington.prathh.quizdroid;

import java.util.List;
import java.util.Set;

/**
 * Created by Hillary on 2/16/15.
 */
public interface TopicRepository {

    List<Topic> getTopicList();

    void setTopicList();

    int getTopicCount();

    List<String> getTitles();

    int getCurrentTopicIndex(String className);
}
