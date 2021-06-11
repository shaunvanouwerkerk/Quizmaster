package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.GroupDAO;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Group;
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
    private ComboBox <User> coordinatorButton;
    @FXML
    private ComboBox <Course> courseButton;
    @FXML
    private Button addGroup;
    @FXML
    private Label labelIdCoordinator;
    @FXML
    private TextField textfieldCurrentCoordinatorId;
    @FXML
    private Label labelIdCourse;
    @FXML
    private TextField textfieldCurrentCourseId;
    @FXML
    private TextField textfieldGroupId;
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
        coordinatorButton.getSelectionModel().getSelectedItem();
        coordinatorButton.setOnAction(event -> keuzeUserDropDown.getSelectionModel().getSelectedItem());
        ComboBox<Course> keuzeCourseDropDown = setCourseDropdownList();
        courseButton.getSelectionModel().getSelectedItem();
        courseButton.setOnAction(event -> keuzeCourseDropDown.getSelectionModel().getSelectedItem());
    }

    public void setupUpdateGroup(Group group) {
        titleUpdateLabel.setText("Wijzig group met Id: ");
        textfieldGroupId.setText(String.valueOf(group.getIdGroup()));
        labelIdCoordinator.setText("Huidige coordinator");
        textfieldCurrentCoordinatorId.setText(String.valueOf(userDAO.getOneById(group.getIdCooridnator())));
        labelIdCourse.setText("Huidige course");
        textfieldCurrentCourseId.setText(String.valueOf(courseDAO.getOneById(group.getIdCourse())));
        addGroup.setText("Wijzig group");
        addGroup.setOnAction(event -> doUpdateGroup(group));
        ComboBox<User> keuzeUserDropDown = setUserDropList();
        coordinatorButton.getSelectionModel().getSelectedItem();
        coordinatorButton.setOnAction(event -> keuzeUserDropDown.getSelectionModel().getSelectedItem());
        ComboBox<Course> keuzeCourseDropDown = setCourseDropdownList();
        courseButton.getSelectionModel().getSelectedItem();
        courseButton.setOnAction(event -> keuzeCourseDropDown.getSelectionModel().getSelectedItem());
    }

    public void doCreateGroup() {
        addGroup.setText("Wijzig group");
        int coordinatorId = coordinatorButton.getSelectionModel().getSelectedItem().getIdUser();
        int courseId = courseButton.getSelectionModel().getSelectedItem().getIdCourse();
        Group group = new Group(coordinatorId, courseId);
        groupDAO.storeOne(group);
        Alert groupSuccesvolToegevoegd = new Alert(Alert.AlertType.INFORMATION);
        groupSuccesvolToegevoegd.setTitle("");
        groupSuccesvolToegevoegd.setHeaderText("De group is succesvol toegevoegd");
        groupSuccesvolToegevoegd.show();
        Main.getSceneManager().showManageGroupsScene();
    }

    public void doUpdateGroup(Group group) {
        group.setIdCooridnator(coordinatorButton.getSelectionModel().getSelectedItem().getIdUser());
        group.setIdCourse(courseButton.getSelectionModel().getSelectedItem().getIdCourse());
        groupDAO.updateGroup(group);
        Alert groupSuccesvolGewijzigd = new Alert(Alert.AlertType.INFORMATION);
        groupSuccesvolGewijzigd.setTitle("");
        groupSuccesvolGewijzigd.setHeaderText("De cursus is succesvol gewijzigd");
        groupSuccesvolGewijzigd.show();
        Main.getSceneManager().showManageGroupsScene();
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
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
        return combobox;
    }

    //methode om te controleren of alle velden zijn gevuld bij het toevoegen/wijzigen van een nieuwe cursus
    public boolean checkFields() {
        boolean allFields = false;
        boolean idCourse = false;
        boolean idCoordinator = false;

        Alert foutmelding = new Alert(Alert.AlertType.ERROR);

        if(!(coordinatorButton.getSelectionModel().isEmpty())) {
            idCoordinator = true;
        } else if(coordinatorButton.getSelectionModel().isEmpty()) {
            foutmelding.setContentText("Je hebt geen coördinator geselecteerd");
            foutmelding.show();
        }
        if(!(courseButton.getSelectionModel().isEmpty())) {
            idCoordinator = true;
        } else if(courseButton.getSelectionModel().isEmpty()) {
            foutmelding.setContentText("Je hebt geen course geselecteerd");
            foutmelding.show();
        }
        if(idCoordinator && idCourse) {
            allFields = true;
        }
        System.out.println(allFields);
        return allFields;
    }


}

