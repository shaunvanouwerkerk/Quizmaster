package controller;

import database.mysql.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Group;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.ArrayList;
import java.util.Optional;

public class ManageGroupsController {

    private GroupDAO groupDAO;
    private DBAccess dBaccess;
    private UserDAO userDAO;
    private CourseDAO courseDAO;

    @FXML
    ListView<Group> groupList;
    @FXML
    private TextField textfieldCourseName;
    @FXML
    private TextField textfieldCoordinatorName;

    public ManageGroupsController() {
        this.dBaccess = Main.getDBaccess();
    }

    public void setup() {
        this.groupDAO = new GroupDAO(dBaccess);
        ArrayList<Group> allGroups = groupDAO.getAll();
        for (Group group : allGroups) {
            groupList.getItems().add(group);
        }
        // Om een nullpointer exception te vermijden
        groupList.getSelectionModel().selectFirst();
//        textfieldCoordinatorName.setText(String.valueOf(userDAO.getOneById(group.getIdCooridnator())));
//        textfieldCourseName.setText(String.valueOf(courseDAO.getOneById(group.getIdCourse())));
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateGroup() {
        Main.getSceneManager().showCreateGroupScene();
    }

    public void doUpdateGroup() {
        Main.getSceneManager().showUpdateGroupScene(groupList.getSelectionModel().getSelectedItem());
    }

    public void doDeleteGroup() {
        Group groupToDelete = groupList.getSelectionModel().getSelectedItem();
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Verwijder groep");
        deleteAlert.setHeaderText("Weet je zeker dat je de groep wilt verwijderen?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            groupDAO.deleteGroup(groupToDelete);
            Main.getSceneManager().showManageGroupsScene();
        }
    }



}
