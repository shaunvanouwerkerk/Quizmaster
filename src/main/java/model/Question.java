package model;

import java.util.ArrayList;

public class Question {
    // Attributen
    private int idQuestion;
    private int idQuiz;
    private String questionString;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private static final int AANTAL_ANTWOORDEN = 4;

    // Constructor
    public Question (int idQuestion, int idQuiz, String questionString,
                     String answerA, String answerB, String answerC, String answerD) {
        this.idQuestion = idQuestion;
        this.idQuiz = idQuiz;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    // Methodes
    /*@Override
    public String toString() {
        // 1. Zet alle antwoorden in een lijst met randomAnswers
        ArrayList<String> randomAnswers = new ArrayList<>(AANTAL_ANTWOORDEN);
        // 2. Shuffle randomAnswers lijst
        // 3. Return String met vraag en antwoorden
    }*/

    // Getters en Setters



}
