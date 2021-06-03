package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.User;
import view.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class  WelcomeController {

    private User userJava;

    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;


//    public void setUserJavaUser(User user) {
//        this.userJava = user;
//        System.out.println(userJava.getRoleName());
//        setLabels(userJava);
//
////        switch (userJava.getRoleName()) {
////            case "student":
////                setupStudent();
////                setLabels();
////                welcomeLabel.setText("Welkom " + userJava.getRoleName() + " " + userJava.getUsername());
////
////        }
//
//    }

    public void setLabels() {

        welcomeLabel.setText("Welkom " + Main.loggedInUser.getRoleName() + " " + Main.loggedInUser.getUsername());
    }

    public void setupStudent() {

        MenuItem item1 = new MenuItem("In- en Uitschrijven cursus");
        MenuItem item2 = new MenuItem("Kies een quiz");

        item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
        item2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());
        taskMenuButton.getItems().add(item1);
        taskMenuButton.getItems().add(item2);

    }

    public void setup() {
        setLabels();
        System.out.println(Main.loggedInUser.getIdUser());
        setupStudent();
    }

    public void doLogout(ActionEvent event) {
        Main.getSceneManager().showLogoutScene();
    }

}
