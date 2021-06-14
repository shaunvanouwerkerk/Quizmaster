package controller;

/*
* @author Nijad
*/

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Question;
import model.Quiz;
import view.Main;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FillOutQuizController {

    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private ArrayList<Question> vragenUitQuiz;
    private ArrayList<String> juisteAntwoorden;
    private ArrayList<String> studentAntwoorden;
    private String[] antwoordKeuzes;
    private int aantalJuisteAntwoorden;
    private int aantalVragenInEenQuiz;
    // twee aparte huidigeVraagNr: omdat een array met 0 begint
    private int huidigeVraagNr;
    private int labelHuidigeVraagNr;
    private static final int AANTAL_ANTWOORDEN = 4;

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;

    public FillOutQuizController(){
        this.dbAccess = Main.getDBaccess();
        this.questionDAO = new QuestionDAO(dbAccess);
        this.vragenUitQuiz = new ArrayList<>();
        this.juisteAntwoorden = new ArrayList<>();
        this.studentAntwoorden = new ArrayList<>();
        this.antwoordKeuzes = new String[AANTAL_ANTWOORDEN];
        this.aantalJuisteAntwoorden = 0;
        this.huidigeVraagNr = 0;
        this.labelHuidigeVraagNr = 1;
    }

    public void setup(Quiz quiz) {
        // Haal alle vragen per Quiz uit de database
        vragenUitQuiz = questionDAO.getAllperQuiz(quiz);

        // Sla juiste antwoorden op om die verder te vergelijken met antwoorden van student
        for (Question question: vragenUitQuiz) {
            juisteAntwoorden.add(question.getAnswerA());
        }
        // Een vraag uit quiz tonen
        showQuestion(huidigeVraagNr);
    }

    public void doRegisterA() {}

    public void doRegisterB() {}

    public void doRegisterC() {}

    public void doRegisterD() {}

    public void doNextQuestion() {


    }

    public void doPreviousQuestion() {


    }

    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }

    public static String[] setUpShuffledAnswers (Question question) {
        String[] antwoorden = new String[4];
        antwoorden[0] = question.getAnswerA();
        antwoorden[1] = question.getAnswerB();
        antwoorden[2] = question.getAnswerC();
        antwoorden[3] = question.getAnswerD();

        // Shuffle answers
        List<String> listToBeShuffled = Arrays.asList(antwoorden);
        Collections.shuffle(listToBeShuffled);
        listToBeShuffled.toArray(antwoorden);

        return antwoorden;
    }

    public void showQuestion(int vraagNr) {
        antwoordKeuzes = setUpShuffledAnswers(vragenUitQuiz.get(huidigeVraagNr));

        questionArea.setText(String.format("%s\nAntwoord A: %s\nAntwoord B: %s\nAntwoord C: %s\nAntwoord D:%s\n",
                vragenUitQuiz.get(huidigeVraagNr).toString(), antwoordKeuzes[0], antwoordKeuzes[1],
                antwoordKeuzes[2], antwoordKeuzes[3]));
        titleLabel.setText(String.format("Vraag %d", labelHuidigeVraagNr));
    }

}
