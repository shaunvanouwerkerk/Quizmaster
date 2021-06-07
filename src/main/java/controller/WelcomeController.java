package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.Course;
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



    public void setLabels() {
        String role = "";
        switch (Main.loggedInUser.getRoleName()) {
            case "technischBeheerder": role = "Technisch Beheerder";
            break;
            case "admin": role = "Administrator";
            break;
            case "student": role = "Student";
            break;
            case "docent": role = "Docent";
            break;
            case "coordinator": role = "Coördinator";
            break;


        }
        welcomeLabel.setText(String.format("Welkom %s! Je bent ingelogd als %s.", Main.loggedInUser.getUsername()
        , role));
    }

    public void setupStudent() {

        MenuItem item1 = new MenuItem("In- en Uitschrijven cursus");
        MenuItem item2 = new MenuItem("Kies een quiz");

        item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
        item2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());

        taskMenuButton.getItems().add(item1);
        taskMenuButton.getItems().add(item2);
    }

    public void setupDocent() { // TODO: 03/06/2021 we moeten nog weten wat hier kan komen voor een docent.
        MenuItem item1 = new MenuItem("[NOG INVULLEN]");
        MenuItem item2 = new MenuItem("[NOG INVULLEN]");

        item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
        item2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());

        taskMenuButton.getItems().add(item1);
        taskMenuButton.getItems().add(item2);
    }

    public void setupAdmin() {
        MenuItem item1 = new MenuItem("Beheer groepen");
        MenuItem item2 = new MenuItem("Beheer cursussen");
        //MenuItem item3 = new MenuItem("Maak nieuwe cursus aan");

        item1.setOnAction(event -> Main.getSceneManager().showManageGroupsScene());
        item2.setOnAction(event -> Main.getSceneManager().showManageCoursesScene());
        //item3.setOnAction(event -> Main.getSceneManager().showCreateCorseScene());
        taskMenuButton.getItems().add(item1);
        taskMenuButton.getItems().add(item2);
        //taskMenuButton.getItems().add(item3);

    }

    public void setupTechBeheer() {
        MenuItem item1 = new MenuItem("Beheer gebruikers");
        MenuItem item2 = new MenuItem("Maak nieuwe gebruiker");

        item1.setOnAction(event -> Main.getSceneManager().showManageUserScene());
        item2.setOnAction(event -> Main.getSceneManager().showCreateUserScene());

        taskMenuButton.getItems().add(item1);
        taskMenuButton.getItems().add(item2);
    }

    public void setupCoordinator() {
        MenuItem item1 = new MenuItem("Beheer Quizzen");
        MenuItem item2 = new MenuItem("Beheer vragen");


        item1.setOnAction(event -> Main.getSceneManager().showManageQuizScene());
        item2.setOnAction(event -> Main.getSceneManager().showManageQuestionsScene());

        taskMenuButton.getItems().add(item1);
        taskMenuButton.getItems().add(item2);
    }

    public void setup() {
        setLabels();
        switch (Main.loggedInUser.getRoleName()) {
            case Main.STUDENT_ROL: setupStudent();
            break;
            case Main.ADMIN_ROL: setupAdmin();
            break;
            case Main.COORDINATOR_ROL:setupCoordinator();
            break;
            case Main.TECHBEHEER_ROL:setupTechBeheer();
            break;
            case Main.DOCENT_ROL: setupDocent();
            break;
            default: Main.getSceneManager().showLogoutScene();

        }
    }



    public void doLogout(ActionEvent event) {
        Main.getSceneManager().showLogoutScene();
    }

}
