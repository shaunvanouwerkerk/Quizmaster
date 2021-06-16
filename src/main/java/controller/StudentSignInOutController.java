package controller;
/*
* @Author Harold Stevens/Branko Visser
* */
import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.Course;
import view.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentSignInOutController {
    private CourseDAO courseDAO;
    private DBAccess dBaccess;
    private ArrayList<Course> coursesToSignIn;
    private ArrayList<Course> coursesToSignOut;
    private ObservableList<Course> selectedCoursesToSignIn;
    private ObservableList<Course> selectedCoursesToSignOut;
    private ArrayList<Course> coursesToCheckWhenSave;


    @FXML
    private ListView<Course> signedOutCourseListView;
    @FXML
    private ListView <Course> signedInCourseListView;

    public StudentSignInOutController(){
        this.dBaccess =  Main.getDBaccess();
        this.courseDAO = new CourseDAO(dBaccess);
        this.coursesToSignIn = new ArrayList<>();
        this.coursesToSignOut = new ArrayList<>();
        this.coursesToCheckWhenSave = new ArrayList<>();
    }
    //methode om twee arraylists te vullen met cursussen om in/uit te schrijven
    //en een kopie klaarzetten van de cursussen waar de student al voor is ingeschreven
    //zodat we die na de wijzigingen kunnen vergelijken met het origineel
    public void setup() {
        coursesToSignIn = courseDAO.getCoursesStudentToSignIn(Main.loggedInUser.getIdUser());
        coursesToSignOut = courseDAO.getCoursesStudentToSignOut(Main.loggedInUser.getIdUser());
        for (Course courseToSave : coursesToSignOut) {
            coursesToCheckWhenSave.add(courseToSave);
        }
        System.out.println(coursesToCheckWhenSave);
        setupScreen();
    }
    //methode om het scherm(Listview) te vullen met de cursussen uit de arraylists van setup
    //Multiple selection aanzetten
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
        signedInCourseListView.getSelectionModel().selectFirst();
        signedOutCourseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        signedInCourseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Maak observable list aan
        selectedCoursesToSignIn = FXCollections.observableArrayList(coursesToSignIn);
        selectedCoursesToSignOut = FXCollections.observableArrayList(coursesToSignOut);
        selectedCoursesToSignIn.clear();
        selectedCoursesToSignOut.clear();

        //Voeg de eerst geselecteerde toe
        selectedCoursesToSignIn.add(signedOutCourseListView.getSelectionModel().getSelectedItem());
        selectedCoursesToSignOut.add(signedInCourseListView.getSelectionModel().getSelectedItem());


        signedOutCourseListView.setOnMouseClicked(mouseEvent -> {
            selectedCoursesToSignIn.clear();
            selectedCoursesToSignIn.addAll(signedOutCourseListView.getSelectionModel().getSelectedItems());
        });

        signedInCourseListView.setOnMouseClicked(mouseEvent -> {
            selectedCoursesToSignOut.clear();
            selectedCoursesToSignOut.addAll(signedInCourseListView.getSelectionModel().getSelectedItems());
        });
    }

    public void doMenu() {
        for(Course courseNew : coursesToSignOut) {
            if(!(coursesToCheckWhenSave.contains(courseNew))) {
                courseDAO.addCourseStudentSignIn(courseNew.getIdCourse(), Main.loggedInUser.getIdUser());
            }
        }
        for(Course courseOld : coursesToCheckWhenSave) {
            if(!(coursesToSignOut.contains(courseOld))) {
                courseDAO.deleteCoursesStudentSignOut(courseOld.getIdCourse(), Main.loggedInUser.getIdUser());
            }
        }
        if(!(coursesToCheckWhenSave.equals(coursesToSignOut))) {
            Alert opgeslagen = new Alert(Alert.AlertType.CONFIRMATION);
            opgeslagen.setHeaderText("Wijzigingen opgeslagen!");
            opgeslagen.setContentText("Veel succes met de cursussen!");
            opgeslagen.showAndWait();
        }
        Main.getSceneManager().showWelcomeScene();
    }

    public void doSignIn() {
        for (Course course : selectedCoursesToSignIn) {
            if (!(course == null)) {
                coursesToSignOut.add(course);
                coursesToSignIn.remove(course);
            }
        }
        setupScreen();
    }

    public void doSignOut() {
        for(Course course : selectedCoursesToSignOut) {
            if(!(course == null)) {
                coursesToSignIn.add(course);
                coursesToSignOut.remove(course);
            }
        }
        setupScreen();
    }
}
