package controller;
/*
* @Author: Nijad Nazarli
*/

import com.mysql.cj.result.StringConverter;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import javafx.beans.Observable;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Question;
import view.Main;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateUpdateQuestionController {
    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private ArrayList<Integer> allIdQuizzes;
    private static final String NIEUWE_VRAAG_AANMAKEN = "Een vraag aanmaken";
    private static final int LENGTE_INVULL_VELDEN = 45;

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
    private ComboBox<Integer> idQuiz;
    @FXML
    private TextField idQuestion;

    public CreateUpdateQuestionController () {
        this.dbAccess = Main.getDBaccess();
        this.questionDAO = new QuestionDAO(dbAccess);
    }

    public void setup(Question question) {
        questionString.setText(question.getQuestionString());
        idQuestion.setText(String.valueOf(question.getIdQuestion()));
        antwoordA.setText(question.getAnswerA());
        antwoordB.setText(question.getAnswerB());
        antwoordC.setText(question.getAnswerC());
        antwoordD.setText(question.getAnswerD());
        ComboBox<Integer> keuzeBox = setTaskMenuButtonRoles();
        idQuiz.getSelectionModel().select(question.getIdQuiz());
        idQuiz.setEditable(false);
    }

    public void setupCreateQuestion() {
        doClear();
        hoofdTitel.setText(NIEUWE_VRAAG_AANMAKEN);
        ComboBox<Integer> keuzeBox = setTaskMenuButtonRoles();
        idQuiz.setOnAction(event-> keuzeBox.getSelectionModel().getSelectedItem());
    }

    public void doMenu() {
        Main.getSceneManager().showManageQuestionsScene();
    }

    // Methode die alle velden leeg maakt
    public void doClear() {
        questionString.clear();
        idQuiz.getSelectionModel().clearSelection();
        idQuestion.clear();
        antwoordA.clear();
        antwoordB.clear();
        antwoordC.clear();
        antwoordD.clear();
    }

    public void doCreateUpdateQuestion() {
        try {
            // 1. Create Question Scenario
            if (hoofdTitel.getText().equals(NIEUWE_VRAAG_AANMAKEN)){
                Question nieuweVraag = fillOutQuestionFields();
                nieuweVraag.setIdQuiz(idQuiz.getSelectionModel().getSelectedItem());
                questionDAO.storeOne(nieuweVraag);
                Alert bevestigAanmakenVraag = new Alert(Alert.AlertType.INFORMATION);
                bevestigAanmakenVraag.setContentText("Vraag is succesvol aangemaakt");
                bevestigAanmakenVraag.show();
                doClear();
                // setup(nieuweVraag);
            } else {
                // 2. Update Question Scenario
                Question aangepasteVraag = fillOutQuestionFields();
                aangepasteVraag.setIdQuestion(Integer.parseInt(idQuestion.getText()));
                questionDAO.updateOne(aangepasteVraag);

                Alert bevestigAanpassenVraag = new Alert(Alert.AlertType.INFORMATION);
                bevestigAanpassenVraag.setContentText("Vraag is succesvol aangepast");
                bevestigAanpassenVraag.show();
                doClear();
                // setup(aangepasteVraag);
            }
        } catch (IllegalArgumentException foutLegeVuld) {
            Alert legeVuldAlert = new Alert(Alert.AlertType.ERROR);
            legeVuldAlert.setTitle("Error Dialog");
            legeVuldAlert.setHeaderText("Lege vuld error dialog");
            legeVuldAlert.setContentText("Vul alle velden aub");
            legeVuldAlert.showAndWait();
        } catch (SQLException sqlFout) {
            Alert sqlAlert = new Alert(Alert.AlertType.ERROR);
            sqlAlert.setTitle("Error Dialog");
            sqlAlert.setHeaderText("Verkeerde invullengte");
            sqlAlert.setContentText("Vraag en/of antwoorden mogen niet langer dan " + LENGTE_INVULL_VELDEN + " tekens zijn!");
            sqlAlert.showAndWait();
        }
    }

    public Question fillOutQuestionFields() throws IllegalArgumentException, SQLException {
        boolean correctFilledOut = false;
        Question nieuweVraag = null;

        do {
            // Text velden laten invullen
            int nieuweIdQuiz = idQuiz.getSelectionModel().getSelectedItem();
            String nieuweQuestionString = questionString.getText();
            String nieuweAntwoordA = antwoordA.getText();
            String nieuweAntwoordB = antwoordB.getText();
            String nieuweAntwoordC = antwoordC.getText();
            String nieuweAntwoordD = antwoordD.getText();

            // Testen of invulvelden leeg zijn
            if (idQuiz.getSelectionModel().isEmpty() ||
                    questionString.getText().isEmpty() ||
                    antwoordA.getText().isEmpty() ||
                    antwoordB.getText().isEmpty() ||
                    antwoordC.getText().isEmpty() ||
                    antwoordD.getText().isEmpty()) {
                throw new IllegalArgumentException();
            // Testen of ingevulde Strings langer dan 45 zijn
            } else if (questionString.getText().length() > LENGTE_INVULL_VELDEN ||
                    antwoordA.getText().length() > LENGTE_INVULL_VELDEN ||
                    antwoordB.getText().length() > LENGTE_INVULL_VELDEN ||
                    antwoordC.getText().length() > LENGTE_INVULL_VELDEN ||
                    antwoordD.getText().length() > LENGTE_INVULL_VELDEN) {
                throw new SQLException();
            } else {
                nieuweVraag = new Question(nieuweIdQuiz,
                        nieuweQuestionString, nieuweAntwoordA, nieuweAntwoordB, nieuweAntwoordC, nieuweAntwoordD);
                correctFilledOut = true;
            }
        } while (correctFilledOut = false);

        return nieuweVraag;
    }

    public ComboBox<Integer> setTaskMenuButtonRoles() {
        this.allIdQuizzes = questionDAO.getQuizzesByLoggedInUser(Main.loggedInUser.getIdUser());
        ObservableList<Integer> observableList = FXCollections.observableArrayList(allIdQuizzes);
        ComboBox<Integer> comboBox = new ComboBox<>(observableList);
        for(Integer id: observableList) {
            idQuiz.getItems().add(id);
        }
        return comboBox;
    }



}