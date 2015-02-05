package edu.washington.prathh.quizdroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PuppyQuiz extends ActionBarActivity {
    private View[] ids;
    private Quiz quiz;
    private int score;
    private  int index;
    private String correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy_quiz);
        this.ids = new View[]{findViewById(R.id.answer1), findViewById(R.id.answer2),
                findViewById(R.id.answer3), findViewById(R.id.answer4)};
        this.score = 0;
        this.index = 0;
        this.quiz = new Quiz();
        this.correct = "";
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(false);
        setUpQuiz();
        setLayout();
    }

    public void enableSubmit(View v) {
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(true);
    }

    public void setUpQuiz() {
        Map<String, String[]> qAndA = new HashMap<>();
        qAndA.put("What was the name of the pup in Wizard of Oz?", new String[] {
                "A:Toto",
                "Lassie",
                "Ollie",
                "Rover"
        });
        qAndA.put("Which of these pups is good friends with the lazy cat Garfield?", new String[] {
                "A:Odie",
                "Mike",
                "Ruff",
                "Toto"
        });
        qAndA.put("Which type of dog won hearts and took the Westminster Best In Show in 2014?", new String[] {
                "A:Fox Terrier",
                "Pit Bull",
                "Jack Russell Terrier",
                "Dachshund"
        });
        qAndA.put("Which adorable pup grows to be more than 100 pounds?", new String[] {
                "A:Bernese",
                "Jack Russell",
                "Pomeranian",
                "Corgi"
        });
        for (String s : qAndA.keySet()) {
            quiz.addQuestion(s, qAndA.get(s));
        }
    }

    public void setLayout() {
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(quiz.questions.get(this.index));
        String[] answerArray = quiz.answers.get(this.index);
        List<String> answers = Arrays.asList(answerArray);
        Collections.shuffle(answers);
        for (int i = 0; i < answers.size(); i++) {
            TextView answer = (TextView) ids[i];
            String currAnswer = answers.get(i);
            if (currAnswer.startsWith("A:")) {
                this.correct = currAnswer.substring(2);
                currAnswer = currAnswer.substring(2);
            }
            answer.setText(currAnswer);
        }
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("Math Quiz: Question " + (index + 1));
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(false);
        Log.i("PuppyQuiz", "Correct: " + this.correct);
    }

    public void submit(View v) {
        this.index++;
        Log.i("PuppyQuiz", "Index is " + this.index);
        Intent summary = new Intent(this, Summary.class);
        summary.putExtra("score", score);
        summary.putExtra("index", index);
        summary.putExtra("correct", correct);
        if (index < quiz.questions.size()) {
            summary.putExtra("last", false);
            startActivityForResult(summary, 1000);
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    setLayout();
                }
            }, 4000);
        } else {
            summary.putExtra("last", true);
            startActivityForResult(summary, 1000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puppy_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
