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
import model.Question;
import view.Main;

import java.util.ArrayList;

public class ManageQuestionsController {

    private DBAccess dbAccess;
    private QuestionDAO questionDAO;

    @FXML
    private ListView<Question> questionList;

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

        if (alleVragen.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Er zijn geen door jou gemaakte vragen in de database. " +
                    "\nDruk op 'Nieuw' om een nieuwe vraag aan te kunnen maken");
            alert.show();
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
