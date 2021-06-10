package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.GroupDAO;
import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    @FXML
    ListView<Group> groupList;

    @FXML
    TextField warningText;

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
        deleteAlert.setTitle("Verwijder group");
        deleteAlert.setHeaderText("Weet je zeker dat je de group wilt verwijderen?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            groupDAO.deleteGroup(groupToDelete);
            Main.getSceneManager().showManageGroupsScene();
        }
    }


}
