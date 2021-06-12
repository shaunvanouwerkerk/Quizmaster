package controller;
/*
* @Author: Nijad Nazarli
*/

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Question;
import view.Main;

import java.util.ArrayList;

public class ManageQuestionsController {

    private DBAccess dbAccess;
    private QuestionDAO questionDAO;

    @FXML
    private ListView<Question> questionList;
    @FXML
    private TextField aantalVragen;

    public ManageQuestionsController () {
        this.dbAccess = Main.getDBaccess();
    }

    public void setup() {
        this.questionDAO = new QuestionDAO(this.dbAccess);
        ArrayList<Question> alleVragen = questionDAO.getAllperLoggedInCoordinator(Main.loggedInUser.getIdUser());
        for (Question question: alleVragen) {
            questionList.getItems().add(question);
        }
        questionList.getSelectionModel().selectFirst();

        // Aantal vragen in een quiz dynamisch laten tonen
        aantalVragen.setText(String.format("Deze quiz bestaat uit %d vraag(en).",
                questionDAO.getNumberOfQuestionsInAquiz(questionList.getSelectionModel().getSelectedItem().getIdQuiz())));
        questionList.setOnMouseClicked(mouseEvent -> aantalVragen.setText(String.format("Deze quiz bestaat" +
                " uit %d vraag(en).", questionDAO.getNumberOfQuestionsInAquiz(questionList.getSelectionModel().getSelectedItem().getIdQuiz()))));
        questionList.setOnKeyPressed(keyEvent -> aantalVragen.setText(String.format("Deze quiz bestaat" +
                " uit %d vraag(en).", questionDAO.getNumberOfQuestionsInAquiz(questionList.getSelectionModel().getSelectedItem().getIdQuiz()))));

        if (alleVragen.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Er zijn geen door jou aangemaakte vragen in de database. " +
                    "\nEr moeten eerst quizzen aangemaakt worden");
            alert.showAndWait();
            Main.getSceneManager().showWelcomeScene();
        }
    }

    public void doMenu(){
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuestion(){
        Main.getSceneManager().showCreateQuestionScene();
    }

    public void doUpdateQuestion(){
        Main.getSceneManager().showUpdateQuestionScene(questionList.getSelectionModel().getSelectedItem());
    }

    public void doDeleteQuestion(){
        Question vraagOmTeVerwijderen = questionList.getSelectionModel().getSelectedItem();
        questionDAO.deleteOne(vraagOmTeVerwijderen);
        questionList.getItems().remove(vraagOmTeVerwijderen);
        Alert bevestigVerwijderen = new Alert(Alert.AlertType.WARNING);
        bevestigVerwijderen.setContentText("De vraag is succesvol verwijderd");
        bevestigVerwijderen.show();
    }
}
