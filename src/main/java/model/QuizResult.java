package model;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import view.Main;

import java.time.LocalDateTime;

public class QuizResult implements Comparable<QuizResult>{
    private int idQuiz;
    private int idGebruiker;
    private int numberAnswersRight;
    private LocalDateTime dateTimeQuiz;

    public QuizResult(int idQuiz, int idGebruiker, int numberAnswersRight,
                      LocalDateTime dateTimeQuiz) {
        this.idQuiz = idQuiz;
        this.idGebruiker = idGebruiker;
        this.numberAnswersRight = numberAnswersRight;
        this.dateTimeQuiz = dateTimeQuiz;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Datum van indiening: %s\n", dateTimeQuiz.withNano(0)));
        result.append(String.format("Aantal juiste antwoorden: %d\n", numberAnswersRight));
        return result.toString();
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public int getIdGebruiker() {
        return idGebruiker;
    }

    public void setIdGebruiker(int idGebruiker) {
        this.idGebruiker = idGebruiker;
    }

    public int getNumberAnswersRight() {
        return numberAnswersRight;
    }

    public void setNumberAnswersRight(int numberAnswersRight) {
        this.numberAnswersRight = numberAnswersRight;
    }

    public LocalDateTime getDateTimeQuiz() {
        return dateTimeQuiz;
    }

    public void setDateTimeQuiz(LocalDateTime dateTimeQuiz) {
        this.dateTimeQuiz = dateTimeQuiz;
    }

    @Override
    public int compareTo(QuizResult andereQuizResult) {
        return -1 * this.dateTimeQuiz.compareTo(andereQuizResult.getDateTimeQuiz());
    }
}
