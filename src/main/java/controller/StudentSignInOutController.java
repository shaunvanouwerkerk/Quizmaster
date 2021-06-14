package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import model.Course;
import view.Main;

import java.util.ArrayList;

public class StudentSignInOutController {
    private CourseDAO courseDAO;
    private DBAccess dBaccess = Main.getDBaccess();

    @FXML
    private ListView<Course> signedOutCourseList;
    @FXML
    private ListView <Course> signedInCourseList;
    @FXML
    private Button signIn;

    public void setup() {
        this.courseDAO = new CourseDAO(dBaccess);
        ArrayList<Course> CoursesSignIn = courseDAO.getCoursesStudentSignIn(Main.loggedInUser.getIdUser());
        for (Course course : CoursesSignIn) {
            signedOutCourseList.getItems().add(course);
        }

        ArrayList<Course> CoursesSignOut = courseDAO.getCoursesStudentSignOut(Main.loggedInUser.getIdUser());
        for (Course course : CoursesSignOut) {
            signedInCourseList.getItems().add(course);
        }
        signedOutCourseList.getSelectionModel().selectFirst();
        signedOutCourseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);




    }


    public void doMenu() {Main.getSceneManager().showWelcomeScene();}

    public void doSignIn() {
       /* ObservableList<Course> selectedItem = signedOutCourseList.getSelectionModel().getSelectedItems();
        signedOutCourseList.remove(selectedItem);
        signedInCourseList.add(selectedItem);

        signIn.setOnAction(Event -> signedOutCourseList.getSelectionModel().getSelectedItems().add(course));
*/


        }

    public void doSignOut() {}
}
