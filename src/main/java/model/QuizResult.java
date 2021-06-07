package model;

import java.time.LocalDateTime;

public class QuizResult {
    private int takeTestNumber;
    private int idQuiz;
    private int idGebruiker;
    private int numberAnswersRight;
    private LocalDateTime dateTimeQuiz;

    public QuizResult(int takeTestNumber, int idQuiz, int idGebruiker, int numberAnswersRight, LocalDateTime dateTimeQuiz) {
        this.takeTestNumber = takeTestNumber;
        this.idQuiz = idQuiz;
        this.idGebruiker = idGebruiker;
        this.numberAnswersRight = numberAnswersRight;
        this.dateTimeQuiz = dateTimeQuiz;
    }





}
