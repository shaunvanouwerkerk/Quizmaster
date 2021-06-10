package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
import model.Quiz;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CreateUpdateQuizController {
    private DBAccess dbAccess;
    private QuestionDAO questionDAO;
    private QuizDAO quizDAO;
    private CourseDAO courseDAO;
    private ArrayList<Course> allCourses;

    @FXML
    private Label titelCreateUpdate;
    @FXML
    private TextField textfieldQuizName;
    @FXML
    private TextField textfieldSuccesDefinition;
    @FXML
    private Button adQuiz;
    @FXML
    private TextField textfieldQuizId;
    @FXML
    private ComboBox<Course> courseButton;


    public CreateUpdateQuizController() {
        this.dbAccess = Main.getDBaccess();
        this.quizDAO = new QuizDAO(dbAccess);
        this.courseDAO = new CourseDAO(dbAccess);
        this.questionDAO = new QuestionDAO(dbAccess);
    }


    public void setup(Quiz quiz) {}

    public void setupCreateQuiz(){
        ComboBox<Course> keuzeDropDown = setCoursesDropList();
        courseButton.getSelectionModel().getSelectedItem();
        courseButton.setOnAction(event -> keuzeDropDown.getSelectionModel().getSelectedItem().getIdCourse());

    }

    public void setupUpdateQuiz (Quiz quiz) {
        titelCreateUpdate.setText("Quiz bewerken");
        textfieldQuizName.setText(quiz.getNameQuiz());
        textfieldQuizId.setText(String.valueOf(quiz.getIdQuiz()));
        textfieldSuccesDefinition.setText(String.valueOf(quiz.getSuccesDefinition()));
        adQuiz.setText("Bewerken");
        adQuiz.setOnAction(event -> doUpdateQuiz(quiz));
        ComboBox<Course> keuzeDropDown = setCoursesDropList();
        courseButton.getSelectionModel().getSelectedItem();
        // TODO: 08/06/2021  Dit gaat nog niet goed. Eigenlijk moet er een extra veld komen waarin je ziet dat de QUIZ valt in de COURSE
        // TODO: 08/06/2021 Daarnaast moet je deze course kunnen wijzigen en moet er een melding zijn dat je alleen coursese kan kiezen
        // TODO: 08/06/2021 waarvoor je bent ingeschreven.

    }

    public void doMenu() {
        Main.getSceneManager().showManageQuizScene();
    }

    public void doCreateQuiz() {
        int courseId = courseButton.getSelectionModel().getSelectedItem().getIdCourse();
        String quizeName = textfieldQuizName.getText();
        int succesDefinition = Integer.parseInt(textfieldSuccesDefinition.getText());
        Quiz quiz = new Quiz(quizeName,succesDefinition,courseId);
        quizDAO.storeOne(quiz);
        doClear();
    }

    public void doUpdateQuiz(Quiz quiz) {
        quiz.setNameQuiz(textfieldQuizName.getText());
        quiz.setSuccesDefinition(Integer.parseInt(textfieldSuccesDefinition.getText()));
        quiz.setIdCourse(courseButton.getSelectionModel().getSelectedItem().getIdCourse());
        quizDAO.updateOne(quiz);
        doClear();
    }

    public void doClear(){
        textfieldQuizName.clear();
        textfieldSuccesDefinition.clear();

    }
    // Methode om gevulde dropdown met courses te krijgen
    public ComboBox<Course> setCoursesDropList(){
        this.allCourses = courseDAO.getCoursesByIdCoordinator(Main.loggedInUser.getIdUser());
        ObservableList<Course> observableList = FXCollections.observableList(allCourses);
        ComboBox<Course> comboBox = new ComboBox<>(observableList);
        for (Course course: observableList) {
            courseButton.getItems().add(course);
        }
        return comboBox;
    }


}