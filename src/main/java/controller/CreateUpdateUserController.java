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


    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ComboBox<String> roleButton;



    public CreateUpdateUserController() {
        this.dbAccess = Main.getDBaccess();
        this.userDAO = new UserDAO(dbAccess);
    }


    public void setup(User user) {
        ComboBox<String> keuzeBox = setTaskMenuButtonRoles();
        roleButton.setOnAction(event -> keuzeBox.getSelectionModel().getSelectedItem());
    }

    public void doMenu() {}

    public void doCreateUpdateUser() {
    }

    public void doClear() {

    }

    public ComboBox<String> setTaskMenuButtonRoles() {
        this.allRoles = userDAO.getAllRoles();
        ObservableList<String> observableList = FXCollections.observableArrayList(allRoles);
        ComboBox<String> comboBox = new ComboBox<>(observableList);
        for(String text: observableList) {
            roleButton.getItems().add(text);
        }
        return comboBox;

    }

}
