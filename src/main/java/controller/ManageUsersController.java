package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.User;
import view.Main;

import java.util.ArrayList;
import java.util.Optional;

public class ManageUsersController {


    private UserDAO userDAO;
    private DBAccess dbAccess;
    private ArrayList<User> allUsers;


    @FXML
    private ListView<User> userList;
    @FXML
    private Label aantalGebruikers;

    public ManageUsersController() {
        this.dbAccess = Main.getDBaccess();
    }


    public void setup() {
        this.userDAO = new UserDAO(dbAccess);
        allUsers = userDAO.getAll();
        for(User user : allUsers) {
            userList.getItems().add(user);
        }
        userList.getSelectionModel().selectFirst();
        userList.setOnMouseClicked(mouseEvent -> setLabelRoleCount(userList.getSelectionModel()
                .getSelectedItem().getRoleName()));
        userList.setOnKeyPressed(keyEvent -> setLabelRoleCount(userList.getSelectionModel()
                .getSelectedItem().getRoleName()));

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
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Verwijderen gebruiker");
        deleteAlert.setHeaderText(String.format("Weet je zeker dat je gebruiker %s wilt verwijderen?",
                userList.getSelectionModel().getSelectedItem().toString()));
        deleteAlert.setContentText("Dit kun je niet ongedaan maken.");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if(result.get() == ButtonType.OK) {
            userDAO.deleteUser(userList.getSelectionModel().getSelectedItem());
        }
    }

    public void setLabelRoleCount(String role) {
        int sumRole = 0;
        for(User user : allUsers) {
            if(user.getRoleName().equals(role)) {
                sumRole++;
            }
        }
        aantalGebruikers.setText(String.format("Het aantal gebruikers met de rol %s is %d", role, sumRole));
    }
}
