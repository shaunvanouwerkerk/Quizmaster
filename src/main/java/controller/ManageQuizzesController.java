package controller;
/*
@ Author Shaun
Nog niet af! 
 */

// TODO: 08/06/2021 :Als je over quiz gaat dat het aantal vragen wordt getoond.
// TODO: 09/06/2021 Zorgen dat je alleen de quizen ziet die behoort bij de coordinator 

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
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
    TextField warningText;

    public ManageQuizzesController() {this.dbAccess = Main.getDBaccess();}


    public void setup() {
        this.quizDAO = new QuizDAO(dbAccess);

        this.courseDAO = new CourseDAO (dbAccess);
        allCourses = courseDAO.getCoursesByIdCoordinator(Main.loggedInUser.getIdUser());

        for(Course course : allCourses){
            allQuizes.add(quizDAO.getOneByCourseId(course.getIdCourse()));
        }
        for (Quiz quiz : allQuizes) {
            quizList.getItems().add(quiz);
        }
        // Om een nullpointer exception te vermijden
        quizList.getSelectionModel().selectFirst();

        // Methode om aantal vragen te tonen per quiz:
        this.questionDAO = new QuestionDAO(dbAccess);
        Quiz quiz = quizList.getSelectionModel().getSelectedItem(); // Hoe kan ik bij opnieuw klikken weer het aantal tonen.
        ArrayList<Question> totalQuestions = questionDAO.getAllperQuiz(quiz);
        int itemCount = totalQuestions.size();
        System.out.println("Het aantal vragen bij deze quiz is: " + itemCount); // Ff voor de test
        warningText.setText(String.valueOf("Aantal vragen bij deze quiz: " + itemCount + " vragen."));

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

    public void doDeleteQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        this.questionDAO = new QuestionDAO(dbAccess);
        boolean quizHasQuestion = questionDAO.getQuestionBasedOnIdQuiz(quiz.getIdQuiz());
        System.out.println(quizHasQuestion);


        //Checkt eerst of er niet al quizvragen zijn aangemaakt.
        if (!(quizHasQuestion)) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Verwijder quiz");
            deleteAlert.setHeaderText("Weet je zeker dat je de quiz wilt verwijderen?");
            Optional<ButtonType> result = deleteAlert.showAndWait();

            if (result.get() == ButtonType.OK) {
                quizDAO.deleteOne(quizList.getSelectionModel().getSelectedItem());
                Main.getSceneManager().showManageQuizScene();
            }

        } else {
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("Verwijder quiz niet mogelijk!");
            deleteAlert.setHeaderText("Deze quiz kan niet verwijderd worden\ner zijn al vragen aangemaakt.");
            Optional<ButtonType> result = deleteAlert.showAndWait();
        }
    }
}
