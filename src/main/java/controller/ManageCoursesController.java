package controller;
/*
 * Author Harold Stevens
 * */
import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.ArrayList;
import java.util.Optional;

public class ManageCoursesController {
    private CourseDAO courseDAO;
    private QuizDAO quizDAO;
    private DBAccess dBaccess;

    @FXML
    ListView<Course> courseList;

    @FXML
    TextField warningText;

    @FXML
    Label aantalStudentenText;

    public ManageCoursesController() {
        this.dBaccess = Main.getDBaccess();
    }

    public void setup() {
        this.courseDAO = new CourseDAO(dBaccess);
        ArrayList<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            courseList.getItems().add(course);
        }
        courseList.getSelectionModel().selectFirst();

        courseList.setOnMouseClicked(mouseEvent -> numberOfStudents());
    }

    public void doMenu(ActionEvent actionEvent){
        dBaccess.closeConnection();
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateCourse(){
        Main.getSceneManager().showCreateCourseScene();
    }

    public void doUpdateCourse(){
        Main.getSceneManager().showUpdateCourseScene(courseList.getSelectionModel().getSelectedItem());
    }

    public void showNumberOfStudentsPerCourse (){

    }



    public void doDeleteCourse() {
        Course course = courseList.getSelectionModel().getSelectedItem();
        this.quizDAO = new QuizDAO(dBaccess);
        boolean courseHasQuizzes = quizDAO.getQuizBasedOnIdCourse(course.getIdCourse());



        //Checkt eerst of er niet al quizzen zijn aangemaakt.
        if (!(courseHasQuizzes)) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Verwijder course");
            deleteAlert.setHeaderText("Weet je zeker dat je de cursus wilt verwijderen?");
            Optional<ButtonType> result = deleteAlert.showAndWait();

            if (result.get() == ButtonType.OK) {
                courseDAO.deleteCourse(courseList.getSelectionModel().getSelectedItem());
                Main.getSceneManager().showManageCoursesScene();
            }

        } else {
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("Verwijderen van cursus niet mogelijk!");
            deleteAlert.setHeaderText("Deze cursus kan niet verwijderd worden\ner zijn al quizzen aangemaakt.");
            Optional<ButtonType> result = deleteAlert.showAndWait();
        }
    }
    // Methode om het aantal studenten per cursus te tonen
    public void numberOfStudents () {
        int aantalStudenten = courseDAO.returnNumberOfStudentsByIdCourse
                (courseList.getSelectionModel().getSelectedItem().getIdCourse());
        if (aantalStudenten == 1){
            aantalStudentenText.setText(String.valueOf(aantalStudenten +
                    " student heeft zich ingeschreven voor deze cursus "));
        } else if (aantalStudenten == 0){
            aantalStudentenText.setText(String.valueOf("Er zijn nog geen studenten ingeschreven voor deze cursus "));
        } else
        aantalStudentenText.setText(String.valueOf(aantalStudenten +
                " studenten hebben zich ingeschreven voor deze cursus "));
    }

}
