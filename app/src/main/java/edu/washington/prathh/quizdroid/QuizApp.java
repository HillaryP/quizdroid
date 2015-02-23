package edu.washington.prathh.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by iguest on 2/16/15.
 */
public class QuizApp extends Application {
    private String URL;
    private int minutes;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "QuizApp Application started");
        initSingletons();
        URL = "";
        minutes = 0;
    }

    public void setURL(String url) {
        this.URL = url;
        Log.i("QuizApp", "URL set to: " + url);
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        Log.i("QuizApp", "Minutes set to: " + minutes);
    }

    public String getURL() {
        return this.URL;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void initSingletons() {
        TopicBuilder.initInstance();
    }
}
