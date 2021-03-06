package controller;
/*Author Branko Visser
* */


import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;
import view.Main;

import java.util.ArrayList;

public class LoginController {

    private final DBAccess dbAccess;
    private final UserDAO userDAO;


    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;
    @FXML
    public Button teamlogo;

    public LoginController() {
        this.dbAccess = Main.getDBaccess();
        this.userDAO = new UserDAO(dbAccess);
    }

    public void doLogin(ActionEvent actionEvent) {
        ArrayList<User> allUsers = userDAO.getAll();

        for (User user: allUsers) {
            if(user.getPassword().equals(passwordField.getText()) && user.getUsername().equals(nameTextField.getText())) {

                Main.loggedInUser = new User(user.getIdUser(), user.getPassword(), user.getUsername(), user.getRoleName());
            }
        }
        if(!(Main.loggedInUser == null)) {
            Alert loginSuccessfull = new Alert(Alert.AlertType.INFORMATION);
            loginSuccessfull.setContentText("Login succesvol. Welkom bij Quizmaster!");
            loginSuccessfull.show();

            //geeft de ingelogte user door naar de welcomeController/welcomeScene
            Main.getSceneManager().showWelcomeScene();
        } else {
                Alert foutmelding = new Alert(Alert.AlertType.ERROR);
                foutmelding.setContentText("Je username en/of password zijn incorrect.");
                foutmelding.show();
        }
    }

    public void doQuit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Afsluiten Quizmaster");
        alert.setHeaderText("Je sluit Quizmaster af.");
        alert.setContentText("Euh nee, je hebt zelf op stop geklikt. Balen...");
        alert.showAndWait();
        System.exit(0);
    }
}
