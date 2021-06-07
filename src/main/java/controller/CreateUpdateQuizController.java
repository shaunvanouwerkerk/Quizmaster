package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class CreateUpdateQuizController {
    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private QuizDAO quizDAO;
    private CourseDAO courseDAO;
    private ArrayList<String> allCourses;

    @FXML
    private Label titelCreateUpdate;
    @FXML
    private TextField textfieldQuizName;
    @FXML
    private TextField textfieldSuccesDefinition;
    @FXML
    private Button adQuiz;


    public CreateUpdateQuizController() {
        this.dbAccess = Main.getDBaccess();
        this.quizDAO = new QuizDAO(dbAccess);
        this.courseDAO = new CourseDAO(dbAccess);
        this.questionDAO = new QuestionDAO(dbAccess);
    }

//    public void setupCreateQuiz (){
//        ComboBox<String> courseChoice = set
//    }


    public void setup(Quiz quiz) {}

    public void doMenu() {
        Main.getSceneManager().showManageQuizScene();
    }

    public void doCreateUpdateQuiz() {}

//    // Methode om een dropdownlist te krijgen van cursussen.
//    public ComboBox<String> setCourseMenuButton () {
//
//    }

}