package edu.washington.prathh.quizdroid;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuizAndAnswer extends ActionBarActivity {
    private TopicBuilder topics;
    private String className;
    private String guess;
    private String correct;
    private int score;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_and_answer);
        className = getIntent().getStringExtra("class");
        topics = TopicBuilder.getInstance();

        if (savedInstanceState == null) {
            OverviewFragment overview = new OverviewFragment();
            Log.i("QuizAndAnswer.class", "Fragment and associated activity: "
                    + overview);

            Bundle args = new Bundle();
            args.putString("className", className);
            overview.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, overview, "OVERVIEW")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
    }

    public void begin(View v) {
        final QuizFragment quiz = new QuizFragment();
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("className", className);
                args.putInt("index", index);
                quiz.setArguments(args);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, quiz, "QUIZ")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        });
    }

    public void setCorrect(String s) {
        this.correct = s;
        Log.i("QuizAndAnswer", "Correct set to " + this.correct);
    }

    public void enableSubmit(View v) {
        this.guess = ((Button) v).getText().toString();
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(true);
    }

    public void submit(View v) {
        SummariesFragment summary = new SummariesFragment();
        this.index++;
        if (this.guess.equals(this.correct)) {
            this.score++;
        }
        Log.i("QuizAndAnswer", "Index is " + this.index);

        Bundle summaryArgs = new Bundle();
        summaryArgs.putString("guess", this.guess);
        summaryArgs.putString("correct", this.correct);
        summaryArgs.putInt("question_number", this.index);
        summaryArgs.putInt("score", score);
        summaryArgs.putBoolean("last", this.index == 4);
        summary.setArguments(summaryArgs);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, summary, "SUMMARY")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    public void goBack(View v) {
        Button b = (Button) findViewById(R.id.next_question);
        if (b.getText().equals("Finish")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            QuizFragment quiz = new QuizFragment();
            Bundle args = new Bundle();
            args.putString("className", className);
            args.putInt("index", index);
            quiz.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, quiz, "QUIZ")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_and_answer, menu);
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

    /**
     * QuizOrig Fragment for storing and displaying Q and A
     */
    public static class QuizFragment extends Fragment {
        private View[] ids;
        String correct;
        String className;
        int index;

        public QuizFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz_and_answer, container, false);
            this.ids = new View[]{rootView.findViewById(R.id.answer1), rootView.findViewById(R.id.answer2),
                    rootView.findViewById(R.id.answer3), rootView.findViewById(R.id.answer4)};

            int classIndex = TopicBuilder.getInstance().getCurrentTopicIndex(className);
            Topic topic = TopicBuilder.getInstance().getTopicList().get(classIndex);
            Quiz quiz = topic.getQAndA().get(index);

            TextView question = (TextView) rootView.findViewById(R.id.question);
            question.setText(quiz.getQuestion());
            String[] answerArray = quiz.getAnswers();
            RadioGroup group = (RadioGroup) rootView.findViewById(R.id.group);
            group.clearCheck();

            List<String> answers = Arrays.asList(answerArray);
            this.correct = answerArray[quiz.getCorrectIndex()];
            Collections.shuffle(answers);
            for (int i = 0; i < answers.size(); i++) {
                TextView answer = (TextView) ids[i];
                String currAnswer = answers.get(i);
                answer.setText(currAnswer);
            }

            TextView title = (TextView) rootView.findViewById(R.id.title);
            title.setText(className + ": Question " + (index + 1));
            Button b = (Button) rootView.findViewById(R.id.next);
            b.setClickable(false);
            Log.i("QuizFragment", "Correct: " + this.correct);
            QuizAndAnswer parent = (QuizAndAnswer) getActivity();
            parent.setCorrect(this.correct);
            return rootView;
        }

        @Override
        public void onCreate(Bundle bundle) {
            Log.i("QuizFragment", "OnCreate called");
            super.onCreate(bundle);
            if (getArguments() != null) {
                className = getArguments().getString("className");
                index = getArguments().getInt("index");
            }
        }

        public static QuizFragment newInstance(String className) {
            QuizFragment f = new QuizFragment();
            Log.i("QuizFragment", "NewInstance called");

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putString("className", className);
            f.setArguments(args);

            return f;
        }
    }

    /**
     * Overview Fragment for storing and displaying quiz information
     */
    public static class OverviewFragment extends Fragment {
        int classIndex;
        Button button;

        public OverviewFragment() {
            Log.i("OverviewFragment", "Constructor called");
        }

        public static OverviewFragment newInstance(String className) {
            OverviewFragment f = new OverviewFragment();
            Log.i("OverviewFragment", "NewInstance called");

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putString("className", className);
            f.setArguments(args);

            return f;
        }

        @Override
        public void onCreate(Bundle bundle) {
            Log.i("OverviewFragment", "OnCreate called");
            super.onCreate(bundle);
            if (getArguments() != null) {
                String className = getArguments().getString("className");
                classIndex = TopicBuilder.getInstance().getCurrentTopicIndex(className);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i("OverviewFragment", "OnCreateView called");

            View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
            TextView titleText = (TextView) rootView.findViewById(R.id.textView);
            titleText.setText(TopicBuilder.getInstance().getTitles().get(classIndex));
            TextView description = (TextView) rootView.findViewById(R.id.textView2);
            description.setText(TopicBuilder.getInstance().getTopicList().get(classIndex).getLongDescription());
            this.button = (Button) rootView.findViewById(R.id.button);
            return rootView;
        }
    }

    /**
     * Summaries Fragment for storing and displaying a user's progress
     */
    public static class SummariesFragment extends Fragment {
        private String correct;
        private String guess;
        private int total;
        private int score;
        private boolean last;

        public SummariesFragment() { }

        public static SummariesFragment newInstance() {
            SummariesFragment f = new SummariesFragment();
            return f;
        }

        @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            if (getArguments() != null) {
                correct = getArguments().getString("correct");
                guess = getArguments().getString("guess");
                total = getArguments().getInt("question_number");
                score = getArguments().getInt("score");
                last = getArguments().getBoolean("last");
                Log.i("SUMMARIES", "total:" + total + ", score: "  + score);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_summaries, container, false);
            Button button = (Button) rootView.findViewById(R.id.next_question);
            if (last) {
                button.setText("Finish");
            } else {
                button.setText("Next Question");
            }

            TextView score = (TextView) rootView.findViewById(R.id.total_correct);
            score.setText("You have answered " + this.score + "/" + this.total + " questions correctly.");
            TextView correctAnswer = (TextView) rootView.findViewById(R.id.correct_answer);
            correctAnswer.setText("Correct answer: " + correct);
            TextView yourAnswer = (TextView) rootView.findViewById(R.id.your_answer);
            yourAnswer.setText("Your answer: " + this.guess);
            return rootView;
        }
    }
}
