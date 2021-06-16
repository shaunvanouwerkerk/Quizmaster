package controller;

//Author Branko Visser

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import view.CouchDBQuizResultsLauncher;
import view.Main;

import java.util.ArrayList;
import java.util.Collections;

public class SelectQuizForStudentController {

    private ArrayList<Quiz> quizes = new ArrayList<>();
    private DBAccess dbAccess = Main.getDBaccess();
    private UserDAO userDAO = new UserDAO(dbAccess);
    private QuizDAO quizDAO = new QuizDAO(dbAccess);
    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();


    @FXML
    ListView<Quiz> quizList;
    @FXML
    public Button teamlogo;

    public void setup() {
        couchDBQuizResultsLauncher.run();
        fillQuizesForStudent();
        for(Quiz quiz : quizes) {
            quizList.getItems().add(quiz);
        }
        if (quizes.isEmpty()) {
            Alert geenQuizes = new Alert(Alert.AlertType.WARNING);
            geenQuizes.setHeaderText("Je hebt geen Quizen om te selecteren!");
            geenQuizes.setContentText("Schrijf je eerst in voor een cursus.");
            geenQuizes.showAndWait();
            Main.getSceneManager().showStudentSignInOutScene();
        }
        quizList.getSelectionModel().selectFirst();
        setLabelQuizResult();
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doQuiz() {
        Main.getSceneManager().showFillOutQuiz(quizList.getSelectionModel().getSelectedItem());
    }

    public void fillQuizesForStudent() {
        ArrayList<Integer> allCourseId = userDAO.getAllCourseId(Main.loggedInUser.getIdUser());
        for(Integer courseId: allCourseId) {
            ArrayList<Quiz> quizzesPerCourse = quizDAO.getQuizesByCourseId(courseId);
            for(Quiz quiz : quizzesPerCourse) {
                quizes.add(quiz);
            }
        }
    }

    public void setLabelQuizResult() {
        ArrayList<QuizResult> quizResults = couchDBQuizResultsLauncher.getQuizResultsCouchDAO().getAllQuizResults();
        for (QuizResult quizResult : quizResults) {
            if(Main.loggedInUser.getIdUser() == quizResult.getIdGebruiker()){
                System.out.println(quizResult);
                System.out.println(quizResult.getIdGebruiker());
            }
        }
    }

}
