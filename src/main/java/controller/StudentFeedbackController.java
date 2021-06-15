package controller;
/*
* @Author: Nijad Nazarli
*/

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import view.CouchDBQuizResultsLauncher;
import view.Main;
import java.util.ArrayList;

public class StudentFeedbackController {

    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher;
    private ArrayList<QuizResult> quizResults;
    private DBAccess dbAccess;
    private QuizDAO quizDAO;

    @FXML
    private Label feedbackLabel;
    @FXML
    private ListView<QuizResult> feedbackList;
    @FXML
    private Label uitslagQuiz;


    public StudentFeedbackController(){
        this.couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();
        couchDBQuizResultsLauncher.run();
        this.quizResults = new ArrayList<>();
        this.dbAccess = Main.getDBaccess();
        this.quizDAO = new QuizDAO(dbAccess);
    }

    public void setup(Quiz quiz) {
        feedbackLabel.setText(String.format("Feedback voor quiz %s", quiz.getNameQuiz()));
        // Haalt alle quizResults uit CouchDB
        ArrayList<QuizResult> quizResultsTemp = couchDBQuizResultsLauncher.getQuizResultsCouchDAO().getAllQuizResults();
        // Filtert het lijst alleen tot ingelogde student
        for (QuizResult quizresult: quizResultsTemp) {
            if (quizresult.getIdGebruiker() == Main.loggedInUser.getIdUser()) {
                if (quizresult.getIdQuiz() == quiz.getIdQuiz()) {
                    quizResults.add(quizresult);
                }
            }
        }
        feedbackList.getItems().addAll(quizResults);
        feedbackList.getSelectionModel().selectFirst();
        uitslagQuiz.setText(checkAndPrintResult(feedbackList.getSelectionModel().getSelectedItem().getNumberAnswersRight(),
                quiz.getSuccesDefinition()));

        feedbackList.setOnMouseClicked(mouseEvent ->
                uitslagQuiz.setText(checkAndPrintResult(feedbackList.getSelectionModel().getSelectedItem().getNumberAnswersRight(),
                        quiz.getSuccesDefinition())));
        feedbackList.setOnKeyPressed(keyEvent ->
                uitslagQuiz.setText(checkAndPrintResult(feedbackList.getSelectionModel().getSelectedItem().getNumberAnswersRight(),
                        quiz.getSuccesDefinition())));
    }
    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }

    public String checkAndPrintResult(int aantalJuisteAntwoorden, int successDefinition) {
        StringBuilder result = new StringBuilder();
        result.append("Resultaat: ");

        if (aantalJuisteAntwoorden >= successDefinition) {
            result.append("Je bent voor deze quiz geslaagd!");
        } else {
            result.append("Helaas, je hebt het niet gehaald!");
        }

        return result.toString();

    }
}

