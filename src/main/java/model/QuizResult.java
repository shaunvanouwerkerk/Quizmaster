package model;

import java.time.LocalDateTime;

public class QuizResult {
    private int takeTestNumber;
    private int idQuiz;
    private int idGebruiker;
    private int numberAnswersRight;
    private LocalDateTime dateTimeQuiz;

    public QuizResult(int takeTestNumber, int idQuiz, int idGebruiker, int numberAnswersRight,
                      LocalDateTime dateTimeQuiz) {
        this.takeTestNumber = takeTestNumber;
        this.idQuiz = idQuiz;
        this.idGebruiker = idGebruiker;
        this.numberAnswersRight = numberAnswersRight;
        this.dateTimeQuiz = dateTimeQuiz;
    }

    public QuizResult(int idQuiz, int idGebruiker, int numberAnswersRight, LocalDateTime dateTimeQuiz) {
        this(0, idQuiz, idGebruiker, numberAnswersRight, dateTimeQuiz);
    }

    public int getTakeTestNumber() {
        return takeTestNumber;
    }

    public void setTakeTestNumber(int takeTestNumber) {
        this.takeTestNumber = takeTestNumber;
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
