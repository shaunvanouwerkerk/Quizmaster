package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import view.Main;

import java.util.ArrayList;

public class StudentSignInOutController {
    private CourseDAO courseDAO;
    private DBAccess dBaccess;

    @FXML
    private ListView<Course> signedOutCourseList;
    @FXML
    private ListView <Course> signedInCourseList;

    public void setup() {
        this.courseDAO = new CourseDAO(dBaccess);
        ArrayList<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            signedInCourseList.getItems().add(course);
        }
        signedInCourseList.getSelectionModel().selectFirst();
    }


    public void doMenu() {}

    public void doSignIn() {}

    public void doSignOut() {}
}
