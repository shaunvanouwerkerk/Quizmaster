package controller;
/*
 * Author Harold Stevens
 * */
import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        boolean correctFilledOut = checkFields();
        if (correctFilledOut) {
            String courseName = nameCourseTextfield.getText();
            int coordinatorId = userButton.getSelectionModel().getSelectedItem().getIdUser();
            Course course = new Course(courseName, coordinatorId);
            courseDAO.storeOne(course);
            Alert cursusSuccesvolToegevoegd = new Alert(Alert.AlertType.INFORMATION);
            cursusSuccesvolToegevoegd.setTitle("");
            cursusSuccesvolToegevoegd.setHeaderText("De cursus is succesvol toegevoegd");
            cursusSuccesvolToegevoegd.show();
            Main.getSceneManager().showManageCoursesScene();
        }
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
        boolean correctFilledOut = checkFields();
        if (correctFilledOut) {
            System.out.println(course);
            course.setNameCourse(nameCourseTextfield.getText());
            course.setIdCoordinator(userButton.getSelectionModel().getSelectedItem().getIdUser());
            courseDAO.updateCourse(course);
            Alert cursusSuccesvolGewijzigd = new Alert(Alert.AlertType.INFORMATION);
            cursusSuccesvolGewijzigd.setTitle("");
            cursusSuccesvolGewijzigd.setHeaderText("De cursus is succesvol gewijzigd");
            cursusSuccesvolGewijzigd.show();
            Main.getSceneManager().showManageCoursesScene();
        }
    }
    //methode om te controleren of alle velden zijn gevuld bij het toevoegen/wijzigen van een nieuwe cursus
    public boolean checkFields() {
        boolean allFields = false;
        boolean coursName = false;
        boolean idCoordinator = false;

        Alert foutmelding = new Alert(Alert.AlertType.ERROR);

        if (!(nameCourseTextfield.getText().isEmpty())) {
            coursName = true;
        } else if(nameCourseTextfield.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen cursusnaam opgegeven");
            foutmelding.show();
        }
        if(!(userButton.getSelectionModel().isEmpty())) {
            idCoordinator = true;
        } else if(userButton.getSelectionModel().isEmpty()) {
            foutmelding.setContentText("Je hebt geen coördinator geselecteerd");
            foutmelding.show();
        }
        if(nameCourseTextfield.getText().isEmpty() && userButton.getSelectionModel().isEmpty()) {
            foutmelding.setContentText("Je hebt geen cursusnaam én coördinator opgegeven");
            foutmelding.show();
        }
        if(coursName && idCoordinator) {
            allFields = true;
        }
        System.out.println(allFields);
        return allFields;
    }

   }




