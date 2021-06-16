package controller;
/*
@ Author Shaun
 */

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Question;
import model.Quiz;
import view.Main;
import java.util.ArrayList;
import java.util.Optional;


public class  ManageQuizzesController {

    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private ArrayList<Course> allCourses= new ArrayList<>();
    private ArrayList <Quiz> allQuizes = new ArrayList<>();


    @FXML
    ListView<Quiz> quizList;
    @FXML
    Label amountQuestionText;
    @FXML
    public Button teamlogo;

    public ManageQuizzesController() {this.dbAccess = Main.getDBaccess();}


    public void setup() {
        this.quizDAO = new QuizDAO(dbAccess);
        this.courseDAO = new CourseDAO(dbAccess);
        allCourses = courseDAO.getCoursesByIdCoordinator(Main.loggedInUser.getIdUser());

        // Checkt of de coordinator quizes beheerd
        if (allCourses.isEmpty()) {
            checkCoordinatorHasCourses();

        } else {
                for (Course course : allCourses) {
                    ArrayList<Quiz> allQuizes = quizDAO.getQuizesByCourseId(course.getIdCourse());
                    for (Quiz quiz : allQuizes) {
                        quizList.getItems().add(quiz);
                    }
                }
                // Om een nullpointer exception te vermijden & aantal vragen te tonen
                quizList.getSelectionModel().selectFirst();
                amountOfQuestions(quizList.getSelectionModel().getSelectedItem());

                // Methode om aantal vragen te tonen per quiz:
                quizList.setOnMouseClicked(mouseEvent -> amountOfQuestions(quizList.getSelectionModel().getSelectedItem()));
                quizList.setOnKeyPressed(keyEvent -> amountOfQuestions(quizList.getSelectionModel().getSelectedItem()));
            }
        }


    public void doMenu(){
        Main.getSceneManager().showWelcomeScene();
    }

    public void doDashboard(){Main.getSceneManager().showCoordinatorDashboard();}

    public void doCreateQuiz(){ Main.getSceneManager().showCreateQuizScene(quizList.getSelectionModel().getSelectedItem()); }

    public void doUpdateQuiz() { Main.getSceneManager().showUpdateQuizScene(quizList.getSelectionModel().getSelectedItem()); }

    public void doDeleteQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        this.questionDAO = new QuestionDAO(dbAccess);
        boolean quizHasQuestion = questionDAO.getQuestionBasedOnIdQuiz(quiz.getIdQuiz());

        //Checkt eerst of er niet al quizvragen zijn aangemaakt.
        if (!(quizHasQuestion)) {
            warningQuizDelete();

        } else {
            warningDeleteQuizNoOption();
        }
    }



    // Methode om te controleren of coordinator ook courses beheert
    public void checkCoordinatorHasCourses () {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Let op");
        alert.setHeaderText("Je hebt nog geen rol als coordinator bij een cursus");
        alert.setContentText("Je kunt dus nog geen quizen aanmaken");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
        Main.getSceneManager().showWelcomeScene();
    }
}

    // Methode om het aantal vragen te tonen
    public void amountOfQuestions (Quiz quiz) {
        this.questionDAO = new QuestionDAO(dbAccess);
        ArrayList<Question> totalQuestions = questionDAO.getAllperQuiz(quiz);
        int itemCount = totalQuestions.size();

        if(itemCount == 0) {
            amountQuestionText.setText(String.valueOf("De quiz bevat momenteel geen vragen."));
        }else if (itemCount == 1){
                amountQuestionText.setText(String.valueOf("Aantal vragen bij deze quiz: " + itemCount + " vraag."));
            } else {
         amountQuestionText.setText(String.valueOf("Aantal vragen bij deze quiz: " + itemCount + " vragen."));
            }
    }

    // Method om waarschuwing te geven bij verwijden
    public void warningQuizDelete () {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Verwijder quiz");
        deleteAlert.setHeaderText("Weet je zeker dat je de quiz wilt verwijderen?");
        Optional<ButtonType> result = deleteAlert.showAndWait();

        if (result.get() == ButtonType.OK) {
            quizDAO.deleteOne(quizList.getSelectionModel().getSelectedItem());
            Main.getSceneManager().showManageQuizScene();
        }
    }

    // Methode om waarschuwing te geven dat verwijderen niet mogelijk is
    public void warningDeleteQuizNoOption () {
        Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
        deleteAlert.setTitle("Verwijder quiz niet mogelijk!");
        deleteAlert.setHeaderText("Deze quiz kan niet verwijderd worden\ner zijn al vragen aangemaakt.");
        Optional<ButtonType> result = deleteAlert.showAndWait();
    }

}
