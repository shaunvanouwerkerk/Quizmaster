package controller;
/*
 * @author Fiona Lampers
 * */
import database.mysql.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import view.Main;

import java.util.ArrayList;
import java.util.Optional;

public class ManageGroupsController {

    private GroupDAO groupDAO;
    private CourseDAO courseDAO;
    private DBAccess dBaccess;
    private ArrayList<Group> allGroups;

    @FXML
    ListView<Group> groupList;
    @FXML
    private Label aantalGroupen;


    public ManageGroupsController() {
        this.dBaccess = Main.getDBaccess();
    }

    public void setup() {
        this.groupDAO = new GroupDAO(dBaccess);
        allGroups = groupDAO.getAll();
        for (Group group : allGroups) {
            groupList.getItems().add(group);
        }
        groupList.getSelectionModel().selectFirst();
        setLabelGroupCount(groupList.getSelectionModel().getSelectedItem().getIdCourse());
        groupList.setOnMouseClicked(mouseEvent -> setLabelGroupCount(groupList.getSelectionModel()
                .getSelectedItem().getIdCourse()));
        groupList.setOnKeyPressed(keyEvent -> setLabelGroupCount(groupList.getSelectionModel()
                .getSelectedItem().getIdCourse()));
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

    public void setLabelGroupCount(int idCourse) {
        courseDAO = new CourseDAO(dBaccess);
        int sumGroup = 0;
        for (Group group : allGroups) {
            if (group.getIdCourse() == idCourse) {
                sumGroup++;
            }
        }
        aantalGroupen.setText(String.format("Het aantal groepen in dezelfde cursus: %s is %d", String.valueOf
                (courseDAO.getOneById(idCourse)), sumGroup));
    }
}
