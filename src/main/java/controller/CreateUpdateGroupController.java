package controller;
/*
 * @author Fiona Lampers
 * */
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
    private static final int MAX_CHAR_GROUP_NAME = 45;

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
    private TextField textfieldGroupName;
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
        coordinatorButton.getSelectionModel().selectFirst();
        coordinatorButton.setOnAction(event -> keuzeUserDropDown.getSelectionModel().getSelectedItem());
        ComboBox<Course> keuzeCourseDropDown = setCourseDropdownList();
        courseButton.getSelectionModel().selectFirst();
        courseButton.setOnAction(event -> keuzeCourseDropDown.getSelectionModel().getSelectedItem());
    }

    public void setupUpdateGroup(Group group) {
        titleUpdateLabel.setText("Wijzig groep");
        textfieldGroupName.setText(group.getNameGroup());
        textfieldGroupName.setEditable(true);
        labelIdCoordinator.setText("Huidige coördinator");
        textfieldCurrentCoordinatorId.setVisible(true);
        textfieldCurrentCoordinatorId.setText(String.valueOf(userDAO.getOneById(group.getIdCooridnator())));
        labelIdCourse.setText("Huidige cursus");
        textfieldCurrentCourseId.setVisible(true);
        textfieldCurrentCourseId.setText(String.valueOf(courseDAO.getOneById(group.getIdCourse())));
        addGroup.setText("Wijzig groep");
        addGroup.setOnAction(event -> doUpdateGroup(group));
        ComboBox<User> keuzeUserDropDown = setUserDropList();
        coordinatorButton.getSelectionModel().select(userDAO.getOneById(group.getIdCooridnator()));
        coordinatorButton.setOnAction(event -> keuzeUserDropDown.getSelectionModel().getSelectedItem());
        ComboBox<Course> keuzeCourseDropDown = setCourseDropdownList();
        courseButton.getSelectionModel().select(courseDAO.getOneById(group.getIdCourse()));
        courseButton.setOnAction(event -> keuzeCourseDropDown.getSelectionModel().getSelectedItem());
    }

    public void doCreateGroup() {
      if (checkFields()) {
            String nameGroup = textfieldGroupName.getText();
            int coordinatorId = coordinatorButton.getSelectionModel().getSelectedItem().getIdUser();
            int courseId = courseButton.getSelectionModel().getSelectedItem().getIdCourse();
            Group group = new Group(nameGroup, coordinatorId, courseId);
            groupDAO.storeOne(group);
            Main.getSceneManager().showCreateGroupScene();
        }
    }

    public void doUpdateGroup(Group group) {
        if (checkFields()) {
            group.setNameGroup(textfieldGroupName.getText());
            group.setIdCooridnator(coordinatorButton.getSelectionModel().getSelectedItem().getIdUser());
            group.setIdCourse(courseButton.getSelectionModel().getSelectedItem().getIdCourse());
            groupDAO.updateGroup(group);
            Main.getSceneManager().showManageGroupsScene();
        }
    }

    public void doMenu() {
        Main.getSceneManager().showManageGroupsScene();
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

    //methode om te controleren of alle velden zijn gevuld bij het toevoegen/wijzigen van een nieuwe groep
    public boolean checkFields() {
        boolean allFields = false;
        boolean groupName = false;
        boolean nameGroupLengthCorrect = false;

        Alert foutmelding = new Alert(Alert.AlertType.ERROR);

        if (!(textfieldGroupName.getText().isEmpty())) {
            groupName =true;
        } else {
            foutmelding.setTitle("");
            foutmelding.setContentText("Je hebt geen groepnaam opgegeven");
            foutmelding.show();
        }
        if (textfieldGroupName.getLength() < MAX_CHAR_GROUP_NAME) {
            nameGroupLengthCorrect = true;
        } else {
            foutmelding.setContentText("De groepsnaam mag niet langer dan 45 tekens zijn");
            foutmelding.show();
        }
        if(groupName && nameGroupLengthCorrect) {
            allFields = true;
        }
        return allFields;
    }


}

