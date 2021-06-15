package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import view.CouchDBQuizResultsLauncher;
import view.Main;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudentFeedbackController {

    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher;
    private ArrayList<QuizResult> quizResults;

    @FXML
    private Label feedbackLabel;
    @FXML
    private ListView<QuizResult> feedbackList;

    public StudentFeedbackController(){
        this.couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();
        couchDBQuizResultsLauncher.run();
        this.quizResults = new ArrayList<>();
    }

    public void setup(Quiz quiz) {
        feedbackLabel.setText(String.format("Feedback voor quiz %s", quiz.getNameQuiz()));
        ArrayList<QuizResult> quizResultsTemp = couchDBQuizResultsLauncher.getQuizResultsCouchDAO().getAllResultsbyQuizIdWithStudentId();
        for (QuizResult quizresult: quizResultsTemp) {
            if (quizresult.getIdGebruiker() == Main.loggedInUser.getIdUser()) {
                quizResults.add(quizresult);
            }
        }
        feedbackList.getItems().addAll(quizResults);
        ObservableList observableList = FXCollections.observableArrayList(feedbackList);

    }
    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }
}

