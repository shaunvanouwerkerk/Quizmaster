package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Course;
import view.Main;

import java.util.ArrayList;

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
        Main.getSceneManager().showCreateUpdateCourseScene(courseList.getSelectionModel().getSelectedItem());
    }



    public void doUpdateCourse(){
        Main.getSceneManager().showCreateUpdateCourseScene(courseList.getSelectionModel().getSelectedItem());
    }

    public void doDeleteCourse(){
        Course removeCourse = courseList.getSelectionModel().getSelectedItem();
        courseDAO.deleteCourse(removeCourse);
        courseList.getItems().remove(removeCourse);

    }

}
