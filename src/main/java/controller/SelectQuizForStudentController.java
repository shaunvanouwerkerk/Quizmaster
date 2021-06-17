package controller;

//Author Branko Visser

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import view.CouchDBQuizResultsLauncher;
import view.Main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class SelectQuizForStudentController {

    private ArrayList<Quiz> quizes = new ArrayList<>();
    private DBAccess dbAccess = Main.getDBaccess();
    private UserDAO userDAO = new UserDAO(dbAccess);
    private QuizDAO quizDAO = new QuizDAO(dbAccess);
    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();


    @FXML
    ListView<Quiz> quizList;
    @FXML
    public Button teamlogo;
    @FXML
    private Label resultLabel;

    public void setup() {
        couchDBQuizResultsLauncher.run();
        fillQuizesForStudent();


        if (quizes.isEmpty()) {
            Alert geenQuizes = new Alert(Alert.AlertType.WARNING);
            geenQuizes.setHeaderText("Je hebt geen Quizen om te selecteren!");
            geenQuizes.setContentText("Schrijf je eerst in voor een cursus.");
            geenQuizes.showAndWait();
            Main.getSceneManager().showStudentSignInOutScene();
        }

        for(Quiz quiz : quizes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();
        setLabelText();

        quizList.setOnMouseClicked(mouseEvent -> setLabelText());
        quizList.setOnKeyPressed(keyEvent -> setLabelText());
    }


    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doQuiz() {
        Main.getSceneManager().showFillOutQuiz(quizList.getSelectionModel().getSelectedItem());
    }

    public void fillQuizesForStudent() {
        ArrayList<Integer> allCourseId = userDAO.getAllCourseId(Main.loggedInUser.getIdUser());
        for(Integer courseId: allCourseId) {
            ArrayList<Quiz> quizzesPerCourse = quizDAO.getQuizesByCourseId(courseId);
            for(Quiz quiz : quizzesPerCourse) {
                quizes.add(quiz);
            }
        }
    }

    public QuizResult findLatestQuizResult(int idQuiz) {
        ArrayList<QuizResult> quizResults = couchDBQuizResultsLauncher.getQuizResultsCouchDAO().getAllQuizResults();
        QuizResult lastResult = null;
        for (QuizResult quizResult : quizResults) {
            if(Main.loggedInUser.getIdUser() == quizResult.getIdGebruiker() && quizResult.getIdQuiz() == idQuiz){
                lastResult = quizResult;
                break;
            }
        }
        return lastResult;
    }

    public String checkAndPrintResult(int aantalJuisteAntwoorden, int successDefinition) {
        StringBuilder result = new StringBuilder();
        String afnameInfo = "Afname info:";
        result.append(String.format("%S\n\n",afnameInfo));
        result.append("Vorig resultaat: ");

        if (aantalJuisteAntwoorden >= successDefinition) {
            result.append("gehaald");
        } else {
            result.append("niet gehaald");
        }

        result.append(String.format("\nJe had %d antwoorden goed.\n", aantalJuisteAntwoorden));
        result.append(String.format("\nDe cescuur van deze Quiz is %d vrag(en) goed.",
                successDefinition));

        return result.toString();
    }

    public void setLabelTextWithResult(QuizResult quizResult, Quiz quiz) {
        StringBuilder stringBuilderResult = new StringBuilder();
        String resultQuiz = checkAndPrintResult(quizResult.getNumberAnswersRight(), quiz.getSuccesDefinition());
        stringBuilderResult.append(resultQuiz);
        stringBuilderResult.append("\nAfgenomen op: ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateResult = quizResult.getDateTimeQuiz();
        String formatDateTime = dateResult.format(formatter);

        stringBuilderResult.append(formatDateTime);

        resultLabel.setText(stringBuilderResult.toString());
    }

    public void setLabelText() {
        QuizResult result = findLatestQuizResult(quizList.getSelectionModel().getSelectedItem().getIdQuiz());
        if(!(result == null)) {
            setLabelTextWithResult(result, quizList.getSelectionModel().getSelectedItem());
        } else {
            setLabelTextNoResult();
        }
    }

    public void setLabelTextNoResult() {
        resultLabel.setText("Deze Quiz heb je nog niet eerder gemaakt!");
    }

}
