package model;

/*
* @Author: Nijad Nazarli
* */

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
        this.questionString = questionString;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public Question (int idQuiz, String questionString,
                     String answerA, String answerB, String answerC, String answerD) {
        this(0, idQuiz, questionString, answerA, answerB, answerC, answerD);
    }

    // Methodes
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append(questionString);
        return resultString.toString();
    }
    // Getters en Setters
    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public String getQuestionString() {
        return questionString;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }
}
