package edu.washington.prathh.quizdroid;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuizAndAnswer extends ActionBarActivity {
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

            switch (className) {
                case "Math":
                    break;
                case "Physics":
                    break;
                case "Marvel":
                    break;
                case "Puppies":
                    break;
            }
        }
    }

    public void begin(View v) {
        final QuizFragment quiz = new QuizFragment();
        Log.i("QuizAndAnswer", "Begin function called");
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

    public void enableSubmit(View v) {
        this.guess = ((Button) v).getText().toString();
        Button b = (Button) findViewById(R.id.next);
        b.setClickable(true);
    }

    public void submit(View v) {
        QuizFragment quiz = new QuizFragment();
        this.index++;
        if (this.guess.equals(this.correct)) {
            this.score++;
        }
        Log.i("QuizAndAnswer", "Index is " + this.index);
        Bundle args = new Bundle();
        args.putString("className", className);
        args.putInt("index", index);
        quiz.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, quiz, "QUIZ")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
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
     * Quiz Fragment for storing and displaying Q and A
     */
    public static class QuizFragment extends Fragment {
        private View[] ids;
        Quiz quiz;
        String correct;
        String className;
        int index;

        public QuizFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz_and_answer, container, false);
            quiz = new Quiz();
            this.ids = new View[]{rootView.findViewById(R.id.answer1), rootView.findViewById(R.id.answer2),
                    rootView.findViewById(R.id.answer3), rootView.findViewById(R.id.answer4)};
            quiz.getQuestions(className);
            TextView question = (TextView) rootView.findViewById(R.id.question);
            question.setText(quiz.questions.get(index));
            String[] answerArray = quiz.answers.get(index);
            RadioGroup group = (RadioGroup) rootView.findViewById(R.id.group);
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
            TextView title = (TextView) rootView.findViewById(R.id.title);
            title.setText(className + " Quiz: Question " + (index + 1));
            Button b = (Button) rootView.findViewById(R.id.next);
            b.setClickable(false);
            Log.i("QuizFragment", "Correct: " + this.correct);
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
        String className;
        Map<String, String> mapping;
        Button button;

        public OverviewFragment() {
            Log.i("OverviewFragment", "Constructor called");
        }

        public static OverviewFragment newInstance(String title, String description) {
            OverviewFragment f = new OverviewFragment();
            Log.i("OverviewFragment", "NewInstance called");

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("description", description);
            f.setArguments(args);

            return f;
        }

        @Override
        public void onCreate(Bundle bundle) {
            Log.i("OverviewFragment", "OnCreate called");
            super.onCreate(bundle);
            if (getArguments() != null) {
                className = getArguments().getString("className");
                mapping = new HashMap<>();
                setUpMapping(mapping);
            }
        }

        public void setUpMapping(Map<String, String> mapping) {
            mapping.put("Math", "Want to learn more about math?\n" +
                    "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
            mapping.put("Physics", "Want to learn more about physics?\n" +
                    "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
            mapping.put("Marvel", "Think you're a hero?\n" +
                    "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
            mapping.put("Puppies", "Who doesn't love a quiz about puppies?\n" +
                    "Look no further than this quiz! Test your knowledge and maybe learn a thing or two.");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i("OverviewFragment", "OnCreateView called");

            View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
            TextView titleText = (TextView) rootView.findViewById(R.id.textView);
            titleText.setText(this.className + " Quiz");
            TextView description = (TextView) rootView.findViewById(R.id.textView2);
            description.setText(this.mapping.get(this.className));
            this.button = (Button) rootView.findViewById(R.id.button);
            return rootView;
        }
    }
}
