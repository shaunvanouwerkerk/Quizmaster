package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import model.User;

public class CreateUpdateUserController {

    private UserDAO userDAO;
    private DBAccess dbAccess;

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private MenuButton taskMenuButton;
    @FXML
    ListView<String> roleList;


    public void setup(User user) {

    }

    public void doMenu() {}

    public void doCreateUpdateUser() {
    }
}
