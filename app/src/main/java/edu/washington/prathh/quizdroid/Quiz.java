package edu.washington.prathh.quizdroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hillaryprather on 2/3/15.
 */
public class Quiz {
    public List<String> questions;
    public List<String[]> answers;
    private int id;

    public Quiz() {
        questions = new ArrayList<String>();
        answers = new ArrayList<String[]>();
        id = 0;
    }

    public void addQuestion(String question, String[] answers) {
        this.questions.add(question);
        this.answers.add(answers);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void getQuestions(String className) {
        Map<String, String[]> qAndA = new HashMap<>();
        switch (className) {
            case "Puppies":
                qAndA.put("What was the name of the pup in Wizard of Oz?", new String[]{
                        "A:Toto",
                        "Lassie",
                        "Ollie",
                        "Rover"
                });
                qAndA.put("Which of these pups is good friends with the lazy cat Garfield?", new String[]{
                        "A:Odie",
                        "Mike",
                        "Ruff",
                        "Toto"
                });
                qAndA.put("Which type of dog won hearts and took the Westminster Best In Show in 2014?", new String[]{
                        "A:Fox Terrier",
                        "Pit Bull",
                        "Jack Russell Terrier",
                        "Dachshund"
                });
                qAndA.put("Which adorable pup grows to be more than 100 pounds?", new String[]{
                        "A:Bernese",
                        "Jack Russell",
                        "Pomeranian",
                        "Corgi"
                });
                break;
            case "Math":
                qAndA.put("2 + 18", new String[]{
                        "A:20",
                        "36",
                        "42",
                        "22"
                });
                qAndA.put("Integrate 1/n", new String[]{
                        "A:ln|n| + c",
                        "log(n) + c",
                        "log|n| + c",
                        "ln(n + c)"
                });
                qAndA.put("Differentiate sin(x)", new String[]{
                        "A:cos(x)",
                        "-cos(x)",
                        "cos(x)sin(x)",
                        "sin(x)/cos(x)"
                });
                qAndA.put("What is 20% of 50% of 100?", new String[]{
                        "A:10",
                        "20",
                        "25",
                        "22"
                });
                break;
            case "Physics":
                qAndA.put("How does one calculate force?", new String[]{
                        "A:mass * acceleration",
                        "mass / acceleration",
                        "(mass * acceleration) * pi",
                        "pi * mass"
                });
                qAndA.put("What is the acronym for the international lab that claims ownership of the Large Hadron Collider?"
                        , new String[]{
                        "A:CERN",
                        "ARO",
                        "ELHC",
                        "HELO"
                });
                qAndA.put("What is the metric value for gravity", new String[]{
                        "A:9.8 mt/sc^2",
                        "32 ft/sc^2",
                        "18 mt/sc^2",
                        "12 cm/sc^2"
                });
                qAndA.put("How many significant digits are in 93200.000", new String[]{
                        "A:3",
                        "5",
                        "8",
                        "2"
                });
                break;
            case "Marvel":
                qAndA.put("What color was the Hulk originally intended to be before the iconic green was selected?",
                        new String[]{
                                "A:Grey",
                                "Blue",
                                "Red",
                                "Yellow"
                        });
                qAndA.put("Marvel Comics co-owns the trademark to the phrase \"super hero\" with which company?",
                        new String[]{
                                "A:DC Comics",
                                "Pixar",
                                "Disney",
                                "Google"
                        });
                qAndA.put("Who is the founder of Marvel Comics?", new String[]{
                        "A:Martin Goodman",
                        "Stan Lee",
                        "Steve Ditko",
                        "Joe Biden"
                });
                break;
        }
        for (String s : qAndA.keySet()) {
            addQuestion(s, qAndA.get(s));
        }
    }
}
