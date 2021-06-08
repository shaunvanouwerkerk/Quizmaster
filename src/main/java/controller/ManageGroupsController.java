package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.GroupDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Course;
import model.Group;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class ManageGroupsController {

    private GroupDAO groupDAO;
    private DBAccess dBaccess;

    @FXML
    ListView<Group> groupList;

    @FXML
    TextField warningText;

    public ManageGroupsController () {
        this.dBaccess = Main.getDBaccess();
    }

    public void setup() {
        this.groupDAO = new GroupDAO(dBaccess);
        ArrayList<Group> allGroups = groupDAO.getAll();
        for (Group group : allGroups){
        groupList.getItems().add(group);
    }
        // Om een nullpointer exception te vermijden
        groupList.getSelectionModel().selectFirst();
    }

    public void doMenu() {}

    public void doCreateGroup() {}

    public void doUpdateGroup() {}

    public void doDeleteGroup() {}
}
