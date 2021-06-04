package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.Main;

public class LogoutController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;

    public void doLogout(){
        System.exit(0);
    }

    public void doReturn(){
        Main.getSceneManager().showWelcomeScene();
    }
}
