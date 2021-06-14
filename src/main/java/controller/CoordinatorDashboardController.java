package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Course;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.ArrayList;
import java.util.Optional;

public class CoordinatorDashboardController {
    private DBAccess dBaccess;
    private CourseDAO courseDAO;
    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
    private ArrayList<Course> allCourses = new ArrayList<>();


    @FXML
    private ListView<Course> courseList;
    @FXML
    private ListView<Quiz> quizList;
    @FXML
    private ListView<Question> questionList;

    public CoordinatorDashboardController() {
        this.dBaccess = Main.getDBaccess();
    }

    public void setup() {
        courseDAO = new CourseDAO(dBaccess);
        allCourses = courseDAO.getCoursesByIdCoordinator(Main.loggedInUser.getIdUser());
        if (allCourses.isEmpty()) {
            checkRegistrationCoordinator();

        } else {
            listOfCourse();
            courseList.getSelectionModel().selectedItemProperty().addListener(
                    new ChangeListener<Course>() {
                        @Override
                        public void changed(ObservableValue<? extends Course> observableValue, Course oldCourse, Course newCourse) {
                            System.out.println("Geselecteerde cursus: " + observableValue + ", " + oldCourse + ", " + newCourse);
                            courseList.setOnMouseClicked(mouseEvent -> getQuizListNewCourse(newCourse).getSelectionModel().getSelectedItem());
                        }
                    });

            quizList.getSelectionModel().selectedItemProperty().addListener(
                    new ChangeListener<Quiz>() {
                        @Override
                        public void changed(ObservableValue<? extends Quiz> observableValue, Quiz oldQuiz, Quiz newQuiz) {
                            System.out.println("Geselecteerde quiz: " + observableValue + ", " + oldQuiz + ", " + newQuiz);
                            quizList.setOnMouseClicked(mouseEvent -> getQuestionListNewQuiz(newQuiz).getSelectionModel().getSelectedItem());
                        }
                    });
        }
    }

    public void doNewQuiz() {
        Main.getSceneManager().showCreateQuizScene(quizList.getSelectionModel().getSelectedItem());
    }

    public void doEditQuiz() {
        Main.getSceneManager().showUpdateQuizScene(quizList.getSelectionModel().getSelectedItem());
    }

    public void doNewQuestion() {
        Main.getSceneManager().showCreateQuestionScene();
    }

    public void doEditQuestion() {
        Main.getSceneManager().showUpdateQuestionScene(questionList.getSelectionModel().getSelectedItem());
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }
    // Methode om lijst met courses te tonen van een bepaalde gebruiker
    public void listOfCourse() {
        this.courseDAO = new CourseDAO(this.dBaccess);
        ArrayList<Course> allCourses = courseDAO.getCoursesByIdCoordinator(Main.loggedInUser.getIdUser());
        for (Course course : allCourses) {
            courseList.getItems().add(course);
        }
    }
    // Methode om quizlijst te clearen en te tonen bij click op nieuwe course
    public ListView<Quiz> getQuizListNewCourse(Course courseNew) {
        this.quizList.getItems().clear();
        this.quizDAO = new QuizDAO(this.dBaccess);
        ArrayList<Quiz> allQuizesNew = quizDAO.getQuizesByCourseId(courseNew.getIdCourse());
        for (Quiz quiz : allQuizesNew) {
            quizList.getItems().add(quiz);
        }
        return quizList;
    }
    //Methode om questionlijst te clearen en te tonen bij click op nieuwe quize
    public ListView<Question> getQuestionListNewQuiz(Quiz quizeNew){
        this.questionList.getItems().clear();
        this.questionDAO = new QuestionDAO(this.dBaccess);
        ArrayList<Question> allQuestionsNew = questionDAO.getAllperQuiz(quizeNew);
        for (Question question : allQuestionsNew){
            questionList.getItems().add(question);
        }
        return questionList;
    }

    // Methode die checkt of de coordinator al een course heeft toegewezen
    public void checkRegistrationCoordinator (){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Je hebt nog geen rol als coordinator bij een cursus.\n Hierdoor is het dashboard leeg en kan je geen quizen en vragen aanmaken");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Main.getSceneManager().showWelcomeScene();
            }
        }
}



