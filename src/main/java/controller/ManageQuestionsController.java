package controller;
/*
* @Author: Nijad Nazarli
*/

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Question;
import model.QuizResult;
import view.CouchDBQuizResultsLauncher;
import view.Main;

import java.util.ArrayList;
import java.util.Optional;

public class ManageQuestionsController {

    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher;
    private ArrayList<QuizResult> quizResults;

    @FXML
    private ListView<Question> questionList;
    @FXML
    private Label aantalVragen;
    @FXML
    public Button teamlogo;

    public ManageQuestionsController () {
        this.dbAccess = Main.getDBaccess();
        this.questionDAO = new QuestionDAO(dbAccess);
        this.couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();
        couchDBQuizResultsLauncher.run();
        this.quizResults = new ArrayList<>();
    }

    public void setup() {
        ArrayList<Question> alleVragen = questionDAO.getAllperLoggedInCoordinator(Main.loggedInUser.getIdUser());
        for (Question question: alleVragen) {
            questionList.getItems().add(question);
        }
        if (alleVragen.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Er zijn geen door jou aangemaakte vragen in de database. " +
                    "\nEr moeten eerst quizzen aangemaakt worden");
            alert.setHeaderText("Geen vragen");
            alert.setTitle("Geen vragen");
            alert.showAndWait();
            Main.getSceneManager().showWelcomeScene();
        } else {
            questionList.getSelectionModel().selectFirst();
            printNumberOfQuestions(questionDAO.getNumberOfQuestionsInAquiz(questionList.getSelectionModel().getSelectedItem().getIdQuiz()));
            questionList.setOnMouseClicked(mouseEvent -> printNumberOfQuestions(questionDAO.getNumberOfQuestionsInAquiz(questionList.getSelectionModel().getSelectedItem().getIdQuiz())));
            questionList.setOnKeyPressed(keyEvent -> printNumberOfQuestions(questionDAO.getNumberOfQuestionsInAquiz(questionList.getSelectionModel().getSelectedItem().getIdQuiz())));
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

    public void showCoordinatorDashboard() {
        Main.getSceneManager().showCoordinatorDashboard();
    }

    public void doDeleteQuestion(){
        Question vraagOmTeVerwijderen = questionList.getSelectionModel().getSelectedItem();
        if (checkIfQuestionCanBeDeleted()) {
            Alert vraagVerwijderen = new Alert(Alert.AlertType.CONFIRMATION);
            vraagVerwijderen.setContentText("Weet je zeker dat je deze vraag wilt verwijderen?");
            vraagVerwijderen.setHeaderText("Vraag verwijderen");
            vraagVerwijderen.setTitle("Bevestiging");
            Optional<ButtonType> decision = vraagVerwijderen.showAndWait();
            if (decision.get() == ButtonType.OK) {
                questionDAO.deleteOne(vraagOmTeVerwijderen);
                questionList.getItems().remove(vraagOmTeVerwijderen);
                Alert bevestigVerwijderen = new Alert(Alert.AlertType.WARNING);
                bevestigVerwijderen.setContentText("De vraag is succesvol verwijderd");
                bevestigVerwijderen.setHeaderText("Een vraag verwijderen");
                bevestigVerwijderen.setTitle("Een vraag verwijderen");
                bevestigVerwijderen.show();
                questionList.getItems().clear();
                setup();
            } else {
                questionList.getItems().clear();
                setup();
            }
        } else {
            Alert magNietVerwijderdWorden = new Alert(Alert.AlertType.ERROR);
            magNietVerwijderdWorden.setContentText("Dit vraag mag niet verwijderd worden.\nEr staan al Quiz Results in de database");
            magNietVerwijderdWorden.setHeaderText("Vraag verwijderen");
            magNietVerwijderdWorden.setTitle("Vraag verwijderen");
            magNietVerwijderdWorden.showAndWait();
            questionList.getItems().clear();
            setup();
        }
    }

    public void printNumberOfQuestions(int numberOfQuestions) {
        if (numberOfQuestions == 1) {
            aantalVragen.setText(String.format("Deze quiz bestaat uit %d vraag.", numberOfQuestions));
        } else {
            aantalVragen.setText(String.format("Deze quiz bestaat uit %d vragen.", numberOfQuestions));
        }
    }

    public boolean checkIfQuestionCanBeDeleted() {
        boolean canBeDeleted = true;
        Question vraagOmTeVerwijderen = questionList.getSelectionModel().getSelectedItem();
        ArrayList<QuizResult> quizResults = couchDBQuizResultsLauncher.getQuizResultsCouchDAO().getAllQuizResults();
        for (QuizResult quizResult: quizResults ) {
            if (quizResult.getIdQuiz() == vraagOmTeVerwijderen.getIdQuiz()) {
                canBeDeleted = false;
                return canBeDeleted;
            }
        }
        return canBeDeleted;
    }

}
