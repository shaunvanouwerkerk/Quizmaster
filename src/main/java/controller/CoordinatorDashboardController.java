package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class CoordinatorDashboardController {
    // SHAUNS TOEVOEGING
//    private CourseDAO courseDAO;
//    private QuizDAO quizDAO;
//    private DBAccess dBaccess;


    @FXML
    private ListView<Course> courseList;
    @FXML
    private ListView<Quiz> quizList;
    @FXML
    private ListView<Question> questionList;

    // SHAUNS TOEVOEGING
//    public CoordinatorDashboardController() {this.dBaccess = Main.getDBaccess();}

    public void setup() {


        courseList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Course>() {
                    @Override
                    public void changed(ObservableValue<? extends Course> observableValue, Course oldCourse, Course newCourse) {
                        System.out.println("Geselecteerde cursus: " + observableValue + ", " + oldCourse + ", " + newCourse);
                    }
                });

        quizList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Quiz>() {
                    @Override
                    public void changed(ObservableValue<? extends Quiz> observableValue, Quiz oldQuiz, Quiz newQuiz) {
                        System.out.println("Geselecteerde quiz: " + observableValue + ", " + oldQuiz + ", " + newQuiz);
                    }
                });
    }
    // SHAUNS TOEVOEGING
    //        this.courseDAO = new CourseDAO(this.dBaccess);
//        ArrayList<Course> allCourses = courseDAO.getAll();
//        for (Course course : allCourses) {
//            courseList.getItems().add(course);
//        }
//        courseList.getSelectionModel().selectFirst();

    public void doNewQuiz() {
        Main.getSceneManager().showCreateQuizScene(quizList.getSelectionModel().getSelectedItem());
    }

    public void doEditQuiz() {
        Main.getSceneManager().showUpdateQuizScene(quizList.getSelectionModel().getSelectedItem());
    }

    public void doNewQuestion() {
        Main.getSceneManager().showCreateQuestionScene();
    }

    public void doEditQuestion() {
        Main.getSceneManager().showUpdateQuestionScene(questionList.getSelectionModel().getSelectedItem());
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }
}
