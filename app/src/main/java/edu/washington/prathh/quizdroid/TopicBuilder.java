package edu.washington.prathh.quizdroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hillary on 2/16/15.
 */
public class TopicBuilder implements TopicRepository {
    private static TopicBuilder instance;
    private List<Topic> topicList;

    public TopicBuilder() {
        topicList = new ArrayList<>();
    }

    public static void initInstance() {
        if (instance == null) {
            instance = new TopicBuilder();
            instance.setTopicList();
        }
    }

    public static TopicBuilder getInstance() {
        return instance;
    }

    public List<Topic> getTopicList() {
        return this.topicList;
    }

    public void setTopicList() {
        Map<String, String[]> qAndA = new HashMap<>();
        qAndA.put("What was the name of the pup in Wizard of Oz?", new String[]{
                "Toto",
                "Lassie",
                "Ollie",
                "Rover"
        });
        qAndA.put("Which of these pups is good friends with the lazy cat Garfield?", new String[]{
                "Odie",
                "Mike",
                "Ruff",
                "Toto"
        });
        qAndA.put("Which type of dog won hearts and took the Westminster Best In Show in 2014?", new String[]{
                "Fox Terrier",
                "Pit Bull",
                "Jack Russell Terrier",
                "Dachshund"
        });
        qAndA.put("Which adorable pup grows to be more than 100 pounds?", new String[]{
                "Bernese",
                "Jack Russell",
                "Pomeranian",
                "Corgi"
        });
        Topic puppy = new Topic();
        puppy.setTitle("Puppies");
        puppy.setShortDescription("Learn about man's best friend!");
        puppy.setLongDescription("Who doesn't love a quiz about puppies?\n" +
                "Look no further than this quiz! Test your knowledge and maybe learn a thing or two.");
        addTopic(qAndA, puppy);

        qAndA.clear();
        qAndA.put("2 + 18", new String[]{
                "20",
                "36",
                "42",
                "22"
        });
        qAndA.put("Integrate 1/n", new String[]{
                "ln|n| + c",
                "log(n) + c",
                "log|n| + c",
                "ln(n + c)"
        });
        qAndA.put("Differentiate sin(x)", new String[]{
                "cos(x)",
                "-cos(x)",
                "cos(x)sin(x)",
                "sin(x)/cos(x)"
        });
        qAndA.put("What is 20% of 50% of 100?", new String[]{
                "10",
                "20",
                "25",
                "22"
        });
        Topic math = new Topic();
        math.setTitle("Math");
        math.setShortDescription("Become a math whiz!");
        math.setLongDescription("Want to learn more about math?\n" +
                "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
        addTopic(qAndA, math);

        qAndA.clear();
        qAndA.put("How does one calculate force?", new String[]{
                "mass * acceleration",
                "mass / acceleration",
                "(mass * acceleration) * pi",
                "pi * mass"
        });
        qAndA.put("What is the acronym for the international lab that claims ownership of the Large Hadron Collider?"
                , new String[]{
                "CERN",
                "ARO",
                "ELHC",
                "HELO"
        });
        qAndA.put("What is the metric value for gravity", new String[]{
                "9.8 mt/sc^2",
                "32 ft/sc^2",
                "18 mt/sc^2",
                "12 cm/sc^2"
        });
        qAndA.put("How many significant digits are in 93200.000", new String[]{
                "3",
                "5",
                "8",
                "2"
        });
        Topic physics = new Topic();
        physics.setTitle("Physics");
        physics.setShortDescription("You'll be the next Einstein!");
        physics.setLongDescription("Want to learn more about physics?\n" +
                "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
        addTopic(qAndA, physics);

        qAndA.clear();
        qAndA.put("What color was the Hulk originally intended to be before the iconic green was selected?",
                new String[]{
                        "Grey",
                        "Blue",
                        "Red",
                        "Yellow"
                });
        qAndA.put("Marvel Comics co-owns the trademark to the phrase \"super hero\" with which company?",
                new String[]{
                        "DC Comics",
                        "Pixar",
                        "Disney",
                        "Google"
                });
        qAndA.put("Who is the founder of Marvel Comics?", new String[]{
                "Martin Goodman",
                "Stan Lee",
                "Steve Ditko",
                "Joe Biden"
        });
        qAndA.put("Who played the Black Widow in the recent movie adaptation of The Avengers?", new String[]{
                "Scarlett Johanson",
                "Stan Lee",
                "Reese Witherspoon",
                "Emma Stone"
        });
        Topic marvel = new Topic();
        marvel.setTitle("Marvel");
        marvel.setShortDescription("Geek out over your favorite comics!");
        marvel.setLongDescription("Think you're a hero?\n" +
                "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
        addTopic(qAndA, marvel);
    }

    private void addTopic(Map<String, String[]> qAndA, Topic topic) {
        List<Quiz> quizzes = new ArrayList<>();
        for (String s : qAndA.keySet()) {
            Quiz quiz = new Quiz();
            quiz.setQuestion(s);
            quiz.setAnswers(qAndA.get(s));
            quiz.setCorrectIndex(0);
            quizzes.add(quiz);
        }
        topic.setQAndA(quizzes);
        topicList.add(topic);
    }

    public int getTopicCount() {
        return this.topicList.size();
    }

    public List<String> getTitles() {
        List<String> names = new ArrayList<>();
        for (Topic topic : topicList) {
            names.add(topic.getTitle());
        }
        return names;
    }

    public int getCurrentTopicIndex(String className) {
        for (int i = 0; i < topicList.size(); i++) {
            if (topicList.get(i).getTitle().equals(className)) {
                return i;
            }
        }
        return -1;
    }
}
