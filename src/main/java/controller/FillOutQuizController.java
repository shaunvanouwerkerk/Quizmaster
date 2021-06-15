package controller;

/*
* @author Nijad
*/

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Question;
import model.Quiz;
import model.QuizResult;
import view.CouchDBQuizResultsLauncher;
import view.Main;
import java.time.LocalDateTime;
import java.util.*;

public class FillOutQuizController {

    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher;
    private ArrayList<Question> vragenUitQuiz;
    private ArrayList<String> juisteAntwoorden;
    private ArrayList<String> studentAntwoorden;
    private String[] antwoordKeuzes;
    private Quiz huidigeQuiz;
    private QuizResult huidigeQuizResult;
    private int aantalJuisteAntwoorden;
    private int aantalVragenInEenQuiz;
    private int huidigeVraagNr;
    private int labelHuidigeVraagNr;
    private static final int AANTAL_ANTWOORDEN = 4;

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;
    @FXML
    private Button volgende;

    public FillOutQuizController(){
        this.dbAccess = Main.getDBaccess();
        this.questionDAO = new QuestionDAO(dbAccess);
        this.couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();
        this.vragenUitQuiz = new ArrayList<>();
        this.juisteAntwoorden = new ArrayList<>();
        this.studentAntwoorden = new ArrayList<>();
        this.antwoordKeuzes = new String[AANTAL_ANTWOORDEN];
        this.huidigeQuizResult = null;
        this.aantalJuisteAntwoorden = 0;
        this.huidigeVraagNr = 0;
        this.labelHuidigeVraagNr = 1;
    }

    public void setup(Quiz quiz) {
        if(questionDAO.getNumberOfQuestionsInAquiz(quiz.getIdQuiz()) == 0) {
            Alert geenVragen = new Alert(Alert.AlertType.INFORMATION);
            geenVragen.setContentText("Er zijn nog geen vragen in deze quiz");
            geenVragen.showAndWait();
            Main.getSceneManager().showSelectQuizForStudent();
        }

        huidigeQuiz = quiz;
        // Haal alle vragen per Quiz uit de database
        vragenUitQuiz = questionDAO.getAllperQuiz(quiz);

        // Sla juiste antwoorden op om die verder te vergelijken met antwoorden van student
        for (Question question: vragenUitQuiz) {
            juisteAntwoorden.add(question.getAnswerA());
        }
        // Voorkomt indexoutofbound en laat de gebruiker antwoorden herschrijven
        studentAntwoorden.ensureCapacity(vragenUitQuiz.size());
        // Een vraag uit quiz tonen
        if (huidigeVraagNr < vragenUitQuiz.size()) {
            showQuestion(huidigeVraagNr);
        }

        if(labelHuidigeVraagNr == vragenUitQuiz.size()) {
            volgende.setText("Afronden");
        } else {
            volgende.setText("Volgende");
        }
    }

    public void doRegisterA() {
            studentAntwoorden.add(huidigeVraagNr, antwoordKeuzes[0]);
            doNextQuestion();
    }

    public void doRegisterB() {
            studentAntwoorden.add(huidigeVraagNr, antwoordKeuzes[1]);
            doNextQuestion();
    }

    public void doRegisterC() {
            studentAntwoorden.add(huidigeVraagNr, antwoordKeuzes[2]);
            doNextQuestion();
    }

    public void doRegisterD() {
            studentAntwoorden.add(huidigeVraagNr, antwoordKeuzes[0]);
            doNextQuestion();
    }

    public void doNextQuestion() {
        huidigeVraagNr++;
        labelHuidigeVraagNr++;
        if(huidigeVraagNr < vragenUitQuiz.size()) {
            setup(huidigeQuiz);
        } else if (huidigeVraagNr >= vragenUitQuiz.size()){
          Alert quizAfronden = new Alert(Alert.AlertType.CONFIRMATION);
          quizAfronden.setContentText("Weet je zeker dat je deze quiz wil afsluiten en verzenden?");
          quizAfronden.setHeaderText("Quiz afronden");
          quizAfronden.setTitle("Bevestiging");
          Optional<ButtonType> decision = quizAfronden.showAndWait();
          if (decision.get() == ButtonType.OK) {
              compareAndCountCorrectAnswers();
            huidigeQuizResult = new QuizResult(huidigeQuiz.getIdQuiz(),
                    Main.loggedInUser.getIdUser(), aantalJuisteAntwoorden, LocalDateTime.now());
              // Sla huidigeQuizResult in de database
              couchDBQuizResultsLauncher.run();
              couchDBQuizResultsLauncher.getQuizResultsCouchDAO().saveQuizResultInJson(huidigeQuizResult);
              Main.getSceneManager().showStudentFeedback(huidigeQuiz);
          } else {
              huidigeVraagNr = 0;
              labelHuidigeVraagNr = 1;
              setup(huidigeQuiz);
              System.out.println(huidigeVraagNr);
          }
        }
    }

    public void doPreviousQuestion() {
        if (labelHuidigeVraagNr > 1) {
            huidigeVraagNr--;
            labelHuidigeVraagNr--;
            setup(huidigeQuiz);
        } else if (labelHuidigeVraagNr == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Dit is de eerste vraag van de quiz");
            alert.show();
        }
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

        questionArea.setText(String.format("%s\n\nAntwoord A: %s\nAntwoord B: %s\nAntwoord C: %s\nAntwoord D: %s\n",
                vragenUitQuiz.get(huidigeVraagNr).toString(), antwoordKeuzes[0], antwoordKeuzes[1],
                antwoordKeuzes[2], antwoordKeuzes[3]));
        titleLabel.setText(String.format("Vraag %d", labelHuidigeVraagNr));
    }

    public void compareAndCountCorrectAnswers(){
        for (int teller = 0; teller < vragenUitQuiz.size(); teller++) {
            try {
                if (studentAntwoorden.get(teller).equals(juisteAntwoorden.get(teller))) {
                    aantalJuisteAntwoorden++;
                }
            } catch (IndexOutOfBoundsException error) {
                studentAntwoorden.add(teller, "geen antwoord opgegeven");
            }
        }
    }

}
