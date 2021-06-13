package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class SelectQuizForStudentController {

    private ArrayList<Quiz> quizes = new ArrayList<>();
    private DBAccess dbAccess = Main.getDBaccess();
    private CourseDAO courseDAO = new CourseDAO(dbAccess);
    private UserDAO userDAO = new UserDAO(dbAccess);
    private QuizDAO quizDAO = new QuizDAO(dbAccess);


    @FXML
    ListView<Quiz> quizList;

    public void setup() {
        fillQuizesForStudent();
        for(Quiz quiz : quizes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();
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
        System.out.println(quizes);
    }
}
