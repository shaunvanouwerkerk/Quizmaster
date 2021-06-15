package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.Course;
import view.Main;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class StudentSignInOutController {
    private CourseDAO courseDAO;
    private DBAccess dBaccess;
    private ArrayList<Course> coursesToSignIn;
    private ArrayList<Course> coursesToSignOut;

    @FXML
    private ListView<Course> signedOutCourseListView;
    @FXML
    private ListView <Course> signedInCourseListView;
    @FXML
    private Button signIn;

    public StudentSignInOutController(){
        this.dBaccess =  Main.getDBaccess();
        this.courseDAO = new CourseDAO(dBaccess);
        this.coursesToSignIn = new ArrayList<>();
        this.coursesToSignOut = new ArrayList<>();
    }

    public void setup() {
        coursesToSignIn = courseDAO.getCoursesStudentSignIn(Main.loggedInUser.getIdUser());
        coursesToSignOut = courseDAO.getCoursesStudentSignOut(Main.loggedInUser.getIdUser());

        setupScreen();
    }

    public void setupScreen(){
        signedOutCourseListView.getItems().clear();
        for (Course course : coursesToSignIn) {
            signedOutCourseListView.getItems().add(course);
        }

        signedInCourseListView.getItems().clear();
        for (Course course : coursesToSignOut) {
            signedInCourseListView.getItems().add(course);
        }
        signedOutCourseListView.getSelectionModel().selectFirst();
        signedOutCourseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        signedInCourseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        AtomicReference<ObservableList<Course>> selectedCoursesToSignIn = new AtomicReference<>(FXCollections.observableArrayList(coursesToSignIn));
        selectedCoursesToSignIn.get().clear();

                //signedOutCourseListView.getSelectionModel().selectFirst();

        signedOutCourseListView.setOnMouseClicked(mouseEvent -> {
            selectedCoursesToSignIn.set(signedOutCourseListView.getSelectionModel().getSelectedItems());
            System.out.println(selectedCoursesToSignIn);

        });
       /* listView.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();

                for(String s : selectedItems){
                    System.out.println("selected item " + s);
                }

            }

        });*/
    }


    public void doMenu() {Main.getSceneManager().showWelcomeScene();}

    public void doSignIn() {

        System.out.println(coursesToSignOut);
        System.out.println(coursesToSignIn);
        coursesToSignOut.add(signedOutCourseListView.getSelectionModel().getSelectedItem());
        coursesToSignIn.remove(signedOutCourseListView.getSelectionModel().getSelectedItem());
        setupScreen();







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


        /*coursesSignIn.add(signedOutCourseList.getSelectionModel().getSelectedItem());
        coursesSignIn.remove(signedOutCourseList.getSelectionModel().getSelectedItem());
        signedOutCourseList.refresh();
        signedInCourseList.refresh();*/
        System.out.println(coursesToSignOut);
        System.out.println(coursesToSignIn);



        /*ObservableList<Course> content = signedOutCourseList.getSelectionModel().getSelectedItems();
        signedInCourseList.(content);
        ObservableList<Course> selectedItem = signedOutCourseList.getSelectionModel().getSelectedItems();
        signedOutCourseList.remove(selectedItem);
        signedInCourseList.add(selectedItem);

        signIn.setOnAction(Event -> signedOutCourseList.getSelectionModel().getSelectedItems().add(course));

*/
    }


    public void doSignOut() {
        System.out.println(coursesToSignOut);
        System.out.println(coursesToSignIn);
        coursesToSignIn.add(signedInCourseListView.getSelectionModel().getSelectedItem());
        coursesToSignOut.remove(signedInCourseListView.getSelectionModel().getSelectedItem());
        System.out.println(coursesToSignOut);
        System.out.println(coursesToSignIn);
        setupScreen();

    }
}
