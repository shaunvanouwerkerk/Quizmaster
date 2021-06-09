package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Course;
import view.Main;

import java.util.ArrayList;
import java.util.Optional;

public class ManageCoursesController {
    private CourseDAO courseDAO;
    private DBAccess dBaccess;

    @FXML
    ListView<Course> courseList;

    @FXML
    TextField warningText;

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



    public void doDeleteCourse() {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Verwijderen cursus");
        deleteAlert.setHeaderText(String.format("Weet je zeker dat je de cursus %s wilt verwijderen?",
                courseList.getSelectionModel().getSelectedItem().toString()));
        deleteAlert.setContentText("Dit kun je niet ongedaan maken.");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if(result.get() == ButtonType.OK) {
            courseDAO.deleteCourse(courseList.getSelectionModel().getSelectedItem());
            Main.getSceneManager().showManageCoursesScene();
        }

    }

}
