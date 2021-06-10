package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
import model.Quiz;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CreateUpdateCourseController {
    private CourseDAO courseDAO;
    private UserDAO userDAO;
    private DBAccess dbAccess;
    private ArrayList<User>  allUsers;
    private ArrayList<User>  allCoordinators = new ArrayList<>();

    @FXML
    private TextField nameCourseTextfield;
    @FXML
    private Button doCreateUpdateCourse;
    @FXML
    private Button addCourse;
    @FXML
    private Button backToMenu;
    @FXML
    private Label titleLabel;
    @FXML
    private ComboBox<User> userButton;

    public CreateUpdateCourseController() {
        this.dbAccess = Main.getDBaccess();
        this.courseDAO = new CourseDAO(dbAccess);
        this.userDAO = new UserDAO(dbAccess);
    }

    public void setupCreateCourse(){
        ComboBox<User> keuzeDropDown = setUserDropList();
        userButton.getSelectionModel().getSelectedItem();
        userButton.setOnAction(event -> keuzeDropDown.getSelectionModel().getSelectedItem());

    }

    public void setupUpdateCourse(Course course){
        titleLabel.setText("Wijzig cursus");
        nameCourseTextfield.setText(course.getNameCourse());
        addCourse.setText("Wijzig cursus");
        addCourse.setOnAction(event -> doUpdateCourse(course));
        ComboBox<User> keuzeBox = setUserDropList();
        userButton.getSelectionModel().select(course.getIdCourse());
        userButton.setOnAction(event -> keuzeBox.getSelectionModel().getSelectedItem());
    }

    public void doMenu(){
        Main.getSceneManager().showManageCoursesScene();
    }

    public void doCreateCourse() {
        String courseName = nameCourseTextfield.getText();
        int coordinatorId = userButton.getSelectionModel().getSelectedItem().getIdUser();
        Course course = new Course(courseName,coordinatorId);
        courseDAO.storeOne(course);
    }


    public ComboBox<User> setUserDropList(){
        this.allUsers = userDAO.getAll();
        for (User coordinator : allUsers){
            if (coordinator.getRoleName().equals(Main.COORDINATOR_ROL)){
                this.allCoordinators.add(coordinator);
            }
        }
        ObservableList<User> observableList = FXCollections.observableList(allCoordinators);
        ComboBox<User> comboBox = new ComboBox<>(observableList);
        for (User user: observableList) {
                userButton.getItems().add(user);
        }
        return comboBox;
    }

    public void doUpdateCourse(Course course) {
            System.out.println(course);
            course.setNameCourse(nameCourseTextfield.getText());
            course.setIdCoordinator(userButton.getSelectionModel().getSelectedItem().getIdUser());
            courseDAO.updateCourse(course);
        }
    }




