package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.User;
import view.Main;

import java.util.ArrayList;

public class ManageUsersController {

    private UserDAO userDAO;
    private DBAccess dbAccess;

    @FXML
    ListView<User> userList;

    public ManageUsersController() {
        this.dbAccess = Main.getDBaccess();
    }


    public void setup() {
        this.userDAO = new UserDAO(dbAccess);
        ArrayList<User> allUsers = userDAO.getAll();
        for(User user : allUsers) {
            userList.getItems().add(user);
        }
        userList.getSelectionModel().selectFirst();

    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {
        Main.getSceneManager().showCreateUserScene();
    }

    public void doUpdateUser() {
        Main.getSceneManager().showUpdateUserScene(userList.getSelectionModel().getSelectedItem());
    }

    public void doDeleteUser() {


    }
}
