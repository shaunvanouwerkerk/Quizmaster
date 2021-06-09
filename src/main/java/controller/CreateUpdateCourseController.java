package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CreateUpdateCourseController {
    private CourseDAO courseDAO;
    private DBAccess dbAccess;

    @FXML
    private TextField nameCourseTextfield;
    @FXML
    private Button doCreateUpdateCourse;
    @FXML
    private Button backToMenu;
    @FXML
    private Label titleLabel;
    @FXML
    private ComboBox<String> naamCoordinator;

    public CreateUpdateCourseController() {
        this.dbAccess = Main.getDBaccess();
        this.courseDAO = new CourseDAO(dbAccess);
    }

    public void setup(Course course) {
        titleLabel.setText("Wijzig cursus");
        nameCourseTextfield.setText(course.getNameCourse());
        naamCoordinator.getSelectionModel().getSelectedItem();
    }
    public void setupCreateCourse(){}

    public void setupUpdateCourse(Course course){}


    @FXML
    public void doMenu(){
        Main.getSceneManager().showManageCoursesScene();
    }

    public void doCreateUpdateCourse() {}
}
