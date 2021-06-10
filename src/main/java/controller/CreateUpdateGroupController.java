package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.GroupDAO;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
import model.Group;
import model.Quiz;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CreateUpdateGroupController {
    private GroupDAO groupDAO;
    private UserDAO userDAO;
    private CourseDAO courseDAO;
    private DBAccess dbAccess;
    private ArrayList<User> allUsers;
    private ArrayList<User> allCoordinators = new ArrayList<>();
    private ArrayList<Course> allCourses;

    @FXML
    private ComboBox<User> coordinatorButton;
    @FXML
    private TextField textfieldGroupId;
    @FXML
    private ComboBox<Course> courseButton;
    @FXML
    private Label titleUpdateLabel;


    public CreateUpdateGroupController() {
        this.dbAccess = Main.getDBaccess();
        this.groupDAO = new GroupDAO(dbAccess);
        this.userDAO = new UserDAO(dbAccess);
        this.courseDAO = new CourseDAO(dbAccess);
    }

    public void setupCreateGroup() {
        ComboBox<User> keuzeUserDropDown = setUserDropList();
        ComboBox<Course> keuzeCourseDropDown = setCourseDropdownList();
        coordinatorButton.getSelectionModel().getSelectedItem();
        coordinatorButton.setOnAction(event -> keuzeUserDropDown.getSelectionModel().getSelectedItem().getIdUser());
        courseButton.getSelectionModel().getSelectedItem();
        courseButton.setOnAction(event -> keuzeCourseDropDown.getSelectionModel().getSelectedItem().getIdCourse());
    }

    public void setupUpdateGroup(Group group) {
        titleUpdateLabel.setText("Wijzig group");
        textfieldGroupId.setText(String.valueOf(group.getIdGroup()));
        ComboBox<User> keuzeUserDropDown = setUserDropList();
        coordinatorButton.getSelectionModel().getSelectedItem();
        coordinatorButton.setOnAction(event -> keuzeUserDropDown.getSelectionModel().getSelectedItem().getIdUser());
    }
//    welcomeLabel.setText("Wijzig gebruiker");
//        userNameTextField.setText(user.getUsername());
//        passwordTextField.setText(user.getPassword());
//
//        createUpdate.setText("Wijzig gebruker");
//        createUpdate.setOnAction(event -> doUpdateUser(user));
//    ComboBox<String> keuzeBox = setTaskMenuButtonRoles();
//        roleButton.getSelectionModel().select(user.getRoleName());
//        roleButton.setOnAction(event -> keuzeBox.getSelectionModel().getSelectedItem());
//}


    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateGroup() {}

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
            coordinatorButton.getItems().add(user);
        }
        return comboBox;
        }

    public ComboBox <Course> setCourseDropdownList(){
        this.allCourses = courseDAO.getAll();
        ObservableList <Course> observableList = FXCollections.observableList(allCourses);
        ComboBox<Course> combobox = new ComboBox<>(observableList);
        for (Course course : observableList) {
            courseButton.getItems().add(course);
        }
        return null;
    }
}

