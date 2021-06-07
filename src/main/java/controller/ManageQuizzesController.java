package controller;
/*
@ Author Shaun
Nog niet af!
 */

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import model.Quiz;
import view.Main;
import java.util.ArrayList;


public class ManageQuizzesController {

    private QuizDAO quizDAO;
    private DBAccess dbAccess;

    @FXML
    ListView<Quiz> quizList;
    @FXML
    TextField warningText;

    public ManageQuizzesController() {this.dbAccess = Main.getDBaccess();}


    public void setup() {
        this.quizDAO = new QuizDAO(dbAccess);
        ArrayList<Quiz> allQuizes = quizDAO.getAll();
        for (Quiz quiz : allQuizes){
            quizList.getItems().add(quiz);
        }
//         Om een nullpointer exception te vermijden
        quizList.getSelectionModel().selectFirst();


    }

    public void doMenu(){Main.getSceneManager().showWelcomeScene();}


    public void doCreateQuiz(){};


    public void doUpdateQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();

        if (quiz == null) {
            warningText.setVisible(true);
            warningText.setText("Je dient eerst een quiz te kiezen");
        } else {
            Main.getSceneManager().showCreateUpdateQuizScene(quizList.getSelectionModel().getSelectedItem());
        }
    }

    public void doDeleteQuiz(){
        // TODO: 06/06/2021 Gaat dit met een DAO die lijnen verwijdered?
//        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
//
//        if (quiz == null) {
//            warningText.setVisible(true);
//            warningText.setText("Je dient eerst een quiz te kiezen");
//        } else { //Nog toevoegen.
    }
}