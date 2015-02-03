package edu.washington.prathh.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MarvelQuiz extends ActionBarActivity {
    private View[] ids;
    private Quiz quiz;
    private int score;
    private int numQuestions;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel_quiz);
        this.ids = new View[]{findViewById(R.id.answer1), findViewById(R.id.answer2),
                findViewById(R.id.answer3), findViewById(R.id.answer4)};
        this.score = 0;
        this.numQuestions = 1;
        this.index = 0;
        this.quiz = new Quiz();
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
        qAndA.put("What color was the Hulk originally intended to be before the iconic green was selected?",
                new String[] {
                "Grey",
                "Blue",
                "Red",
                "Yellow"
        });
        qAndA.put("Marvel Comics co-owns the trademark to the phrase \"super hero\" with which company?",
                new String[] {
                "DC Comics",
                "Pixar",
                "Disney",
                "Google"
        });
        qAndA.put("Who is the founder of Marvel Comics?", new String[] {
                "Martin Goodman",
                "Stan Lee",
                "Steve Ditko",
                "Joe Biden"
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
            answer.setText(answers.get(i));
        }
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("Math Quiz: Question " + numQuestions);
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(false);
    }

    public void submit(View v) {
        this.numQuestions++;
        this.index++;
        Log.i("MarvelQuiz", "Index is " + this.index);
        if (index < quiz.questions.size()) {
            setLayout();
        } else {
            //end
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marvel_quiz, menu);
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
