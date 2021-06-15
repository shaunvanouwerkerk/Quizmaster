package model;

import java.time.LocalDateTime;

public class QuizResult {
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
        return String.format("Date-time made: %s", dateTimeQuiz);
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
}
