package controller;
/*
* @Author: Nijad Nazarli
*/

import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Question;
import view.Main;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class CreateUpdateQuestionController {
    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private static final String NIEUWE_VRAAG_AANMAKEN = "Een vraag aanmaken";

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
        hoofdTitel.setText(NIEUWE_VRAAG_AANMAKEN);
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

    public void doCreateUpdateQuestion() {
        // TODO: Controleren of invoer juist is (niet langer dan 45 char)
        // TODO: Wat gebeurt als de velden leeg gemaakt worden
        // 1. Create Question Scenario
            if (hoofdTitel.getText().equals(NIEUWE_VRAAG_AANMAKEN)){
                int nieuweIdQuestion = questionDAO.getCurrentQuestionId() + 1;
                Question nieuweVraag = fillOutQuestionFields();
                nieuweVraag.setIdQuestion(nieuweIdQuestion);
                idQuestion.setText(String.valueOf(nieuweIdQuestion));
                questionDAO.storeOne(nieuweVraag);
                Alert bevestigAanmakenVraag = new Alert(Alert.AlertType.INFORMATION);
                bevestigAanmakenVraag.setContentText("Vraag is succesvol aangemaakt");
                bevestigAanmakenVraag.show();
                setup(nieuweVraag);
            } else {
        // 2. Update Question Scenario
                Question aangepasteVraag = fillOutQuestionFields();
                aangepasteVraag.setIdQuestion(Integer.parseInt(idQuestion.getText()));
                questionDAO.updateOne(aangepasteVraag);
                Alert bevestigAanpassenVraag = new Alert(Alert.AlertType.INFORMATION);
                bevestigAanpassenVraag.setContentText("Vraag is succesvol aangepast");
                bevestigAanpassenVraag.show();
                setup(aangepasteVraag);
            }
    }

    public Question fillOutQuestionFields() {
        int nieuweIdQuiz = Integer.parseInt(idQuiz.getText());
        String nieuweQuestionString = questionString.getText();
        String nieuweAntwoordA = antwoordA.getText();
        String nieuweAntwoordB = antwoordB.getText();
        String nieuweAntwoordC = antwoordC.getText();
        String nieuweAntwoordD = antwoordD.getText();
        Question nieuweVraag = new Question(nieuweIdQuiz,
                    nieuweQuestionString, nieuweAntwoordA, nieuweAntwoordB, nieuweAntwoordC, nieuweAntwoordD);
        return nieuweVraag;
    }

}