package controller;
/*Author: Branko Visser
* */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.Main;

public class LogoutController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;
    @FXML
    public Button teamlogo;

    public void doLogout(){
        Main.loggedInUser = null;
        Main.getSceneManager().showLoginScene();
    }

    public void doReturn(){
        Main.getSceneManager().showWelcomeScene();
    }
}
