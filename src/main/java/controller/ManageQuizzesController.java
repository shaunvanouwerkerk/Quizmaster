package controller;
/*
@ Author Shaun
Nog niet af! 
 */

// TODO: 08/06/2021 :Als je over quiz gaat dat het aantal vragen wordt getoond. 

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import model.Question;
import model.Quiz;
import view.Main;
import java.util.ArrayList;
import java.util.Optional;


public class  ManageQuizzesController {

    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
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

    public void doMenu(){
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuiz(){
        Main.getSceneManager().showCreateQuizScene(quizList.getSelectionModel().getSelectedItem());
    }

    public void doUpdateQuiz() {
       Main.getSceneManager().showUpdateQuizScene(quizList.getSelectionModel().getSelectedItem());
    }

    public void doDeleteQuiz(){
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        this.questionDAO = new QuestionDAO(dbAccess);
        Question question = questionDAO.getOneById(quiz.getIdCourse());

        //Checkt eerst of er niet al quizvragen zijn aangemaakt.
        if(question == null) {
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING);
            deleteAlert.setTitle("Verwijder quiz");
            deleteAlert.setHeaderText("Weet je zeker dat je de quiz wilt verwijderen?");
            Optional<ButtonType> result = deleteAlert.showAndWait();

            if(result.get() == ButtonType.OK){
                quizDAO.deleteOne(quizList.getSelectionModel().getSelectedItem());
                Main.getSceneManager().showManageQuizScene();
            }

        }else{
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("Verwijder quiz niet mogelijk!");
            deleteAlert.setHeaderText("Deze quiz kan niet verwijderd worden\ner zijn al vragen aangemaakt.");
            Optional<ButtonType> result = deleteAlert.showAndWait();
        }
    }
}
