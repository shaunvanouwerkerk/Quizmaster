package controller;
/*
* @Author: Nijad Nazarli
*/

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Question;
import view.Main;

public class CreateUpdateQuestionController {
    private DBAccess dbAccess;
    private QuestionDAO questionDAO;

    @FXML
    private Label hoofdTitel;
    @FXML
    private TextField questionString;
    @FXML
    private TextField antwoordA;
    @FXML
    private TextField antwoordB;
    @FXML
    private TextField antwoordC;
    @FXML
    private TextField antwoordD;
    @FXML
    private TextField idQuiz;
    @FXML
    private TextField idQuestion;

    public CreateUpdateQuestionController () {
        this.dbAccess = Main.getDBaccess();
        this.questionDAO = new QuestionDAO(dbAccess);
    }

    public void setup(Question question) {
        questionString.setText(question.getQuestionString());
        idQuiz.setText(String.valueOf(question.getIdQuiz()));
        idQuestion.setText(String.valueOf(question.getIdQuestion()));
        antwoordA.setText(question.getAnswerA());
        antwoordB.setText(question.getAnswerB());
        antwoordC.setText(question.getAnswerC());
        antwoordD.setText(question.getAnswerD());
    }

    public void setupCreateQuestion() {
        doClear();
        hoofdTitel.setText("Een vraag aanmaken");
    }

    public void doMenu() {
        Main.getSceneManager().showManageQuestionsScene();
    }

    // Methode die alle velden leeg maakt
    public void doClear() {
        questionString.clear();
        idQuiz.clear();
        idQuestion.clear();
        antwoordA.clear();
        antwoordB.clear();
        antwoordC.clear();
        antwoordD.clear();
    }

    public void doUpdateQuestion() {
        // TODO: Controleren of invoer juist is (niet langer dan 45 char)
        // TODO: Wat gebeurt als de velden leeg gemaakt worden
        int nieuweIdQuiz = Integer.parseInt(idQuiz.getText());
        String nieuweQuestionString = questionString.getText();
        String nieuweAntwoordA = antwoordA.getText();
        String nieuweAntwoordB = antwoordB.getText();
        String nieuweAntwoordC = antwoordC.getText();
        String nieuweAntwoordD = antwoordD.getText();
        Question nieuweVraag = new Question(Integer.parseInt(idQuestion.getText()), nieuweIdQuiz,
                nieuweQuestionString, nieuweAntwoordA, nieuweAntwoordB, nieuweAntwoordC, nieuweAntwoordD);
        questionDAO.updateOne(nieuweVraag);
        Alert bevestigAanpassenVraag = new Alert(Alert.AlertType.CONFIRMATION);
        bevestigAanpassenVraag.setContentText("Vraag is succesvol aangepast");
        setup(nieuweVraag);
    }

    public void doCreateQuestion() {
        // 1. Scenario voor Create Question


    }
}