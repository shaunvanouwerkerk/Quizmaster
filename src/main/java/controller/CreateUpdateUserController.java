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
    @FXML
    private Button createUpdate;



    public CreateUpdateUserController() {
        this.dbAccess = Main.getDBaccess();
        this.userDAO = new UserDAO(dbAccess);
    }


    public void setupCreateUser() {
        ComboBox<String> keuzebox = setTaskMenuButtonRoles();
        roleButton.getSelectionModel().select(Main.STUDENT_ROL);
        roleButton.setOnAction(event -> keuzebox.getSelectionModel().getSelectedItem());

    }

    public void setupUpdateUser(User user) {
        welcomeLabel.setText("Wijzig gebruiker");
        userNameTextField.setText(user.getUsername());
        passwordTextField.setText(user.getPassword());
        createUpdate.setText("Wijzig gebruker");
        createUpdate.setOnAction(event -> doUpdateUser(user));
        ComboBox<String> keuzeBox = setTaskMenuButtonRoles();
        roleButton.getSelectionModel().select(user.getRoleName());
        roleButton.setOnAction(event -> keuzeBox.getSelectionModel().getSelectedItem());
    }

    public void doMenu() {
        Main.getSceneManager().showManageUserScene();
    }

    public void doCreateUser() {
        boolean correctFilledOut = checkFields();
        if (correctFilledOut) {
            String username = userNameTextField.getText();
            String password = passwordTextField.getText();
            String role = roleButton.getSelectionModel().getSelectedItem();
            User user = new User(password, username, role);
            userDAO.storeOne(user);
            doClear();
        }
    }

    public void doUpdateUser(User user) {
        boolean correctFilledOut = checkFields();
        if (correctFilledOut) {
            user.setPassword(passwordTextField.getText());
            user.setUsername(userNameTextField.getText());
            user.setRoleName(roleButton.getSelectionModel().getSelectedItem());
            userDAO.updateUser(user);
            Main.getSceneManager().showWelcomeScene();
        }
    }

    //Knop op scherm 'Maak invulvelden leeg', reset de velden naar de default waarden
    public void doClear() {
        userNameTextField.clear();
        passwordTextField.clear();
        roleButton.getSelectionModel().select(Main.STUDENT_ROL);
    }

    //Methode checkt of alle velden zijn ingevuld. Als een of meerdere velden niet zijn ingevuld,
    // wordt een foutmelding gegenereerd op het scherm
    public boolean checkFields() {
        boolean allFields = false;
        boolean userName = false;
        boolean password = false;

        Alert foutmelding = new Alert(Alert.AlertType.ERROR);

        if (!(userNameTextField.getText().isEmpty())) {
            userName = true;
        } else if(userNameTextField.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen gebruikersnaam opgegeven");
            foutmelding.show();
        }
        if(!(passwordTextField.getText().isEmpty())) {
            password = true;
        } else if(passwordTextField.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen password opgegeven");
            foutmelding.show();
        }
        if(userNameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen gebruikersnaam én geen wachtwoord opgegeven");
            foutmelding.show();
        }
        if(userName && password) {
            allFields = true;
        }
        return allFields;
    }

    //De methode maakt een gevuld dropdown menu met de rollen uit de DB en voegt deze toe aan de knop roleButton.
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
