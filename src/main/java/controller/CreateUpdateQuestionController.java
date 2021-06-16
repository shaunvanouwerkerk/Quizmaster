package controller;
/*
* @Author: Nijad Nazarli
*/

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Question;
import model.Quiz;
import view.Main;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateUpdateQuestionController {
    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private QuizDAO quizDAO;
    private ArrayList<Quiz> allQuizzes;
    private int idQuestion;
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
    private ComboBox<Quiz> quizzen;
    @FXML
    private Button createUpdateQuestionButton;
    @FXML
    public Button teamlogo;

    public CreateUpdateQuestionController () {
        this.dbAccess = Main.getDBaccess();
        this.questionDAO = new QuestionDAO(dbAccess);
        this.quizDAO = new QuizDAO(dbAccess);
    }

    public void setup(Question question) {
        questionString.setText(question.getQuestionString());
        antwoordA.setText(question.getAnswerA());
        antwoordB.setText(question.getAnswerB());
        antwoordC.setText(question.getAnswerC());
        antwoordD.setText(question.getAnswerD());
        this.idQuestion = question.getIdQuestion();
        ComboBox<Quiz> keuzeBox = setTaskMenuButtonQuizzes();
        quizzen.getSelectionModel().select(quizDAO.getOneById(question.getIdQuiz()));
        quizzen.setOnAction(event-> keuzeBox.getSelectionModel().getSelectedItem());
        createUpdateQuestionButton.setOnAction(event-> updateQuestion());
    }

    public void setupCreateQuestion() {
        doClear();
        hoofdTitel.setText(NIEUWE_VRAAG_AANMAKEN);
        setTaskMenuButtonQuizzes();
        quizzen.getSelectionModel().getSelectedItem();
    }

    public void doCreateUpdateQuestion() {
        boolean correctFilledOut = checkFields();
        boolean juisteLengte = checkLengteInvuldVelden();
        if (correctFilledOut) {
            String nieuweQuestionString = questionString.getText();
            String nieuweAntwoordA = antwoordA.getText();
            String nieuweAntwoordB = antwoordB.getText();
            String nieuweAntwoordC = antwoordC.getText();
            String nieuweAntwoordD = antwoordD.getText();
            Question nieuweQuestion = new Question(quizzen.getSelectionModel().getSelectedItem().getIdQuiz(), nieuweQuestionString,
                    nieuweAntwoordA, nieuweAntwoordB, nieuweAntwoordC, nieuweAntwoordD);
            questionDAO.storeOne(nieuweQuestion);

            if (juisteLengte) {
                Alert bevestigAanmakenVraag = new Alert(Alert.AlertType.INFORMATION);
                bevestigAanmakenVraag.setContentText("Vraag is succesvol aangemaakt");
                bevestigAanmakenVraag.setHeaderText("Een vraag aanmaken");
                bevestigAanmakenVraag.setTitle("Een vraag aanmaken");
                bevestigAanmakenVraag.show();
                doClear();
                quizzen.getSelectionModel().getSelectedItem();
            } else {
                Alert bevestigAanmakenVraag = new Alert(Alert.AlertType.ERROR);
                bevestigAanmakenVraag.setContentText("Vraag en/of antwoord mag niet langer dan 45 tekens zijn");
                bevestigAanmakenVraag.setHeaderText("Error lengte van invulvelden");
                bevestigAanmakenVraag.setTitle("Error lengte van invulvelden");
                bevestigAanmakenVraag.showAndWait();
                doClear();
            }
        }
    }

    public void updateQuestion() {
        boolean correctFilledOut = checkFields();
        boolean juisteLengte = checkLengteInvuldVelden();
        if (correctFilledOut) {
            String updateQuestionString = questionString.getText();
            String updateAntwoordA = antwoordA.getText();
            String updateAntwoordB = antwoordB.getText();
            String updateAntwoordC = antwoordC.getText();
            String updateAntwoordD = antwoordD.getText();
            Question updateQuestion = new Question(this.idQuestion, quizzen.getSelectionModel().getSelectedItem().getIdQuiz(), updateQuestionString, updateAntwoordA,
                    updateAntwoordB, updateAntwoordC, updateAntwoordD);
            questionDAO.updateOne(updateQuestion);

            if (juisteLengte) {
                Alert bevestigAanpassenVraag = new Alert(Alert.AlertType.INFORMATION);
                bevestigAanpassenVraag.setContentText("Vraag is succesvol aangepast");
                bevestigAanpassenVraag.setHeaderText("Een vraag bewerken");
                bevestigAanpassenVraag.setTitle("Een vraag bewerken");
                bevestigAanpassenVraag.show();
                doClear();
                Main.getSceneManager().showManageQuestionsScene();
            } else {
                Alert bevestigAanmakenVraag = new Alert(Alert.AlertType.ERROR);
                bevestigAanmakenVraag.setContentText("Vraag en/of antwoord mag niet langer dan 45 tekens zijn");
                bevestigAanmakenVraag.setHeaderText("Error lengte van invulvelden");
                bevestigAanmakenVraag.setTitle("Error lengte van invulvelden");
                bevestigAanmakenVraag.showAndWait();
                doClear();
            }
        }
    }

    // Methode om gevulde dropdown met quizzen te krijgen
    public ComboBox<Quiz> setTaskMenuButtonQuizzes() {
        this.allQuizzes = questionDAO.getQuizzesByLoggedInUser(Main.loggedInUser.getIdUser());
        ObservableList<Quiz> observableList = FXCollections.observableList(allQuizzes);
        ComboBox<Quiz> comboBox = new ComboBox<>(observableList);
        for (Quiz quiz: observableList) {
            quizzen.getItems().add(quiz);
        }
        return comboBox;
    }

    // Methode die alle velden leeg maakt
    public void doClear() {
        questionString.clear();
        antwoordA.clear();
        antwoordB.clear();
        antwoordC.clear();
        antwoordD.clear();
    }

    public void doMenu() {
        Main.getSceneManager().showManageQuestionsScene();
    }

    public boolean checkFields() throws IllegalArgumentException {
        boolean allFields = false;
        boolean questionStringBool = false;
        boolean antwoordAbool = false;
        boolean antwoordBbool = false;
        boolean antwoordCbool = false;
        boolean antwoordDbool = false;

        Alert foutmelding = new Alert(Alert.AlertType.ERROR);

        if (!(questionString.getText().isEmpty())) {
            questionStringBool = true;
        } else if(questionString.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen vraag opgeschreven");
            foutmelding.show();
        }
        if(!(antwoordA.getText().isEmpty())) {
            antwoordAbool = true;
        } else if(antwoordA.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen antwoord opgegeven");
            foutmelding.show();
        }
        if(!(antwoordB.getText().isEmpty())) {
            antwoordBbool = true;
        } else if(antwoordB.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen antwoord opgegeven");
            foutmelding.show();
        }
        if(!(antwoordC.getText().isEmpty())) {
            antwoordCbool = true;
        } else if(antwoordC.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen antwoord opgegeven");
            foutmelding.show();
        }
        if(!(antwoordD.getText().isEmpty())) {
            antwoordDbool = true;
        } else if(antwoordD.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen antwoord opgegeven");
            foutmelding.show();
        }

        if(questionString.getText().isEmpty() && antwoordA.getText().isEmpty()
                && antwoordB.getText().isEmpty() && antwoordC.getText().isEmpty()
                && antwoordD.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen vraag en/of antwoorden opgegeven");
            foutmelding.show();
        }
        if(questionStringBool && antwoordAbool && antwoordBbool && antwoordCbool && antwoordDbool) {
            allFields = true;
        }
        return allFields;
    }

    public boolean checkLengteInvuldVelden () {
        boolean juisteLengte = questionString.getText().length() <= LENGTE_INVULL_VELDEN
                && antwoordA.getText().length() <= LENGTE_INVULL_VELDEN
                && antwoordB.getText().length() <= LENGTE_INVULL_VELDEN
                && antwoordC.getText().length() <= LENGTE_INVULL_VELDEN
                && antwoordD.getText().length() <= LENGTE_INVULL_VELDEN;
        return juisteLengte;
    }

}