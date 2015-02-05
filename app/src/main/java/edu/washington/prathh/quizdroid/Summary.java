package edu.washington.prathh.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Summary extends ActionBarActivity {
    private int score;
    private int total;
    private String correct;
    private String guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        this.score = intent.getIntExtra("score", 1);
        this.total = intent.getIntExtra("index", 1);
        this.correct = intent.getStringExtra("correct");
        this.guess = intent.getStringExtra("guess");
        boolean last = intent.getBooleanExtra("last", false);
        Button button = (Button) findViewById(R.id.next_question);
        if (last) {
            button.setText("Finish");
        } else {
            button.setText("Next Question");
        }

        TextView score = (TextView) findViewById(R.id.total_correct);
        score.setText("You have answered " + this.score + "/" + this.total + " questions correctly.");
        TextView correctAnswer = (TextView) findViewById(R.id.correct_answer);
        correctAnswer.setText("Correct answer: " + correct);
        TextView yourAnswer = (TextView) findViewById(R.id.your_answer);
        yourAnswer.setText("Your answer: " + this.guess);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puppy_summary, menu);
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

    public void goBack(View v) {
        Button b = (Button) findViewById(R.id.next_question);
        if (b.getText().equals("Finish")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            finish();
        }
    }
}
