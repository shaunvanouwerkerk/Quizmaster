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

    public static DBAccess getDBaccess() {
        if (dbAccess == null) {
            dbAccess = new DBAccess("quizzydraw", "quizuser", "quizuserPW");
        }
        return dbAccess;
    }


    public static void main(String[] args) {
        launch(args);

//        DBAccess dbAccess1 = getDBaccess();
//        dbAccess1.openConnection();
//
//
//        UserDAO userDAO = new UserDAO(dbAccess1);
//        ArrayList<User> users = userDAO.getUsers();
//        System.out.println(users);
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