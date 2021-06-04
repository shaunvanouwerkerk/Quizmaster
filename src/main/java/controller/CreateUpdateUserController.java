package controller;

/*
* @Author Branko
*
* UNDER CONSTRUCTION!
* */

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CreateUpdateUserController {

    private UserDAO userDAO;
    private DBAccess dbAccess;
    private ArrayList<String> allRoles;
    private ComboBox<String> roles;

    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private MenuButton taskMenuButton;
    @FXML
    ListView<String> roleList;
    @FXML
    ComboBox<String> comboBox;

    public CreateUpdateUserController() {
        this.dbAccess = Main.getDBaccess();
        this.userDAO = new UserDAO(dbAccess);
    }


    public void setup(User user) {
//        setTaskMenuButtonRoles();
    }

    public void doMenu() {}

    public void doCreateUpdateUser() {
    }

//    public void setTaskMenuButtonRoles() {
//        this.allRoles = userDAO.getAllRoles();
//        ObservableList<String> observableList = FXCollections.observableList(allRoles);
//        final ComboBox comboBox = new ComboBox((ObservableList) allRoles);
//        for (String role: allRoles) {
////            MenuItem menuItem = new MenuItem(role);
////            taskMenuButton.getItems().add(menuItem);
//            comboBox.setItems((ObservableList) allRoles);
//        }
//    }
//
////    public void setTextMenuButton(ActionEvent event) {
////        taskMenuButton.setOnAction(event -> taskMenuButton.setText(allRoles.g));
////    }
}
