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
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MathQuiz extends ActionBarActivity {
    private View[] ids;
    private Quiz mathQuiz;
    private int score;
    private int index;
    private String correct;
    private String guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);
        this.ids = new View[]{findViewById(R.id.answer1), findViewById(R.id.answer2),
                findViewById(R.id.answer3), findViewById(R.id.answer4)};
        this.score = 0;
        this.index = 0;
        this.mathQuiz = new Quiz();
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(false);
        setUpQuiz();
        setLayout();
    }

    public void enableSubmit(View v) {
        this.guess = ((Button) v).getText().toString();
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(true);
    }

    public void setUpQuiz() {
        Map<String, String[]> qAndA = new HashMap<>();
        qAndA.put("2 + 18", new String[] {
                "A:20",
                "36",
                "42",
                "22"
        });
        qAndA.put("Integrate 1/n", new String[] {
                "A:ln|n| + c",
                "log(n) + c",
                "log|n| + c",
                "ln(n + c)"
        });
        qAndA.put("Differentiate sin(x)", new String[] {
                "A:cos(x)",
                "-cos(x)",
                "cos(x)sin(x)",
                "sin(x)/cos(x)"
        });
        qAndA.put("What is 20% of 50% of 100?", new String[] {
                "A:10",
                "20",
                "25",
                "22"
        });
        for (String s : qAndA.keySet()) {
            mathQuiz.addQuestion(s, qAndA.get(s));
        }
    }

    public void setLayout() {
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(mathQuiz.questions.get(this.index));
        String[] answerArray = mathQuiz.answers.get(this.index);
        RadioGroup group = (RadioGroup) findViewById(R.id.group);
        group.clearCheck();
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
        if (this.guess.equals(this.correct)) {
            this.score++;
        }
        Log.i("MathQuiz", "Index is " + this.index);
        Intent summary = new Intent(this, Summary.class);
        summary.putExtra("score", score);
        summary.putExtra("index", index);
        summary.putExtra("correct", correct);
        summary.putExtra("guess", guess);
        if (index < mathQuiz.questions.size()) {
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
        getMenuInflater().inflate(R.menu.menu_math_quiz, menu);
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
