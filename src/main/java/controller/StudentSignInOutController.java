package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import model.Course;
import view.Main;

import java.util.ArrayList;

public class StudentSignInOutController {
    private CourseDAO courseDAO;
    private DBAccess dBaccess;
    private ArrayList<Course> coursesSignIn;
    private ArrayList<Course> coursesSignOut;

    @FXML
    private ListView<Course> signedOutCourseList;
    @FXML
    private ListView <Course> signedInCourseList;
    @FXML
    private Button signIn;

    public StudentSignInOutController(){
        this.dBaccess =  Main.getDBaccess();
        this.courseDAO = new CourseDAO(dBaccess);
        this.coursesSignIn = new ArrayList<>();
        this.coursesSignOut = new ArrayList<>();
    }

    public void setup() {
        coursesSignIn = courseDAO.getCoursesStudentSignIn(Main.loggedInUser.getIdUser());
        for (Course course : coursesSignIn) {
            signedOutCourseList.getItems().add(course);
        }

        coursesSignOut = courseDAO.getCoursesStudentSignOut(Main.loggedInUser.getIdUser());
        for (Course course : coursesSignOut) {
            signedInCourseList.getItems().add(course);
        }
        signedOutCourseList.getSelectionModel().selectFirst();
        signedOutCourseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    public void doMenu() {Main.getSceneManager().showWelcomeScene();}

    public void doSignIn() {
        /*int idStudent = Main.loggedInUser.getIdUser();
        ArrayList <Course> test = new ArrayList<>();
        signIn.setOnAction(Event -> test.add(signedInCourseList.getSelectionModel().getSelectedItem()));
                    signedInCourseList.refresh();
                }

            coursesSignIn.add
            if (selection != null) {
                signedInCourseList.getSelectionModel().clearSelection();
                coursesSignIn.remove(selection);
                coursesSignOut.add(selection);
            }
        });*/



        /*Course selectedCourses = signedInCourseList.getSelectionModel().getSelectedItem();
        coursesSignIn.add(selectedCourses);
        coursesSignOut.remove(selectedCourses);
        ListView<c>*/


        coursesSignIn.add(signedOutCourseList.getSelectionModel().getSelectedItem());
        coursesSignIn.remove(signedOutCourseList.getSelectionModel().getSelectedItem());
        signedOutCourseList.refresh();
        signedInCourseList.refresh();
        System.out.println(coursesSignOut);
        System.out.println(coursesSignIn);



        /*ObservableList<Course> content = signedOutCourseList.getSelectionModel().getSelectedItems();
        signedInCourseList.(content);
        ObservableList<Course> selectedItem = signedOutCourseList.getSelectionModel().getSelectedItems();
        signedOutCourseList.remove(selectedItem);
        signedInCourseList.add(selectedItem);

        signIn.setOnAction(Event -> signedOutCourseList.getSelectionModel().getSelectedItems().add(course));

*/
    }


    public void doSignOut() {}
}
