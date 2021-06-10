package view;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;

import java.util.ArrayList;

public class Main extends Application {

    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static DBAccess dbAccess = null;
    public static User loggedInUser = null;
    public static final String STUDENT_ROL = "student";
    public static final String ADMIN_ROL = "admin";
    public static final String TECHBEHEER_ROL = "technischBeheerder";
    public static final String COORDINATOR_ROL = "coordinator";
    public static final String DOCENT_ROL = "docent";

    public static DBAccess getDBaccess() {
        if (dbAccess == null) {
            dbAccess = new DBAccess("quizmaster", "userQuizmaster", "pwQuizmaster");
        }
        return dbAccess;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Make IT Work - Project 1");
        getSceneManager().setWindowTool();
        primaryStage.show();
    }

    public static SceneManager getSceneManager() {
        if (sceneManager == null) {
            sceneManager = new SceneManager(primaryStage);
        }
        return sceneManager;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }





}