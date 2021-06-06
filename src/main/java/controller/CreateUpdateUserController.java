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

import java.sql.SQLException;
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
        roleButton.getSelectionModel().select(Main.STUDENT_ROL);
        roleButton.setOnAction(event -> keuzeBox.getSelectionModel().getSelectedItem());

    }

    public void doMenu() {
        Main.getSceneManager().showManageUserScene();
    }

    public void doCreateUpdateUser() {
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
        boolean role = false;
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
        switch (roleButton.getSelectionModel().getSelectedItem()) {
                case Main.ADMIN_ROL:
                case Main.TECHBEHEER_ROL:
                case Main.COORDINATOR_ROL:
                case Main.STUDENT_ROL:
                case Main.DOCENT_ROL: {
                    role = true;
                    break;
            }
        }
        if(userName && password && role) {
            allFields = true;
        }
        System.out.println(allFields);
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
