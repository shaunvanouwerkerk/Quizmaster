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

    private ArrayList<Quiz> quizes;
    private ArrayList<Course> courses;
    private DBAccess dbAccess = Main.getDBaccess();
    private CourseDAO courseDAO = new CourseDAO(dbAccess);
    private UserDAO userDAO = new UserDAO(dbAccess);
    private QuizDAO quizDAO = new QuizDAO(dbAccess);


    @FXML
    ListView<Quiz> quizList;

    public void setup() {
        ArrayList<Integer> courseId = userDAO.getAllCourseId(Main.loggedInUser.getIdUser());
        System.out.println(courseId);


    }

    public void doMenu() {}

    public void doQuiz() {}
}
