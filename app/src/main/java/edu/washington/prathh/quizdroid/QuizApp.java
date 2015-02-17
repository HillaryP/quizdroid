package edu.washington.prathh.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by iguest on 2/16/15.
 */
public class QuizApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "QuizApp Application started");
        initSingletons();
    }

    public void initSingletons() {
        TopicBuilder.initInstance();
    }
}
