package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        courseButton.setOnAction(event -> keuzeDropDown.getSelectionModel().getSelectedItem());

    }

    public void setupUpdateQuiz (Quiz quiz) {
        titelCreateUpdate.setText("Quiz bewerken");
        textfieldQuizName.setText(quiz.getNameQuiz());
        textfieldSuccesDefinition.setText(String.valueOf(quiz.getSuccesDefinition()));
        adQuiz.setText("Bewerken");
        adQuiz.setOnAction(event -> doUpdateQuiz(quiz));
        ComboBox<Course> keuzeDropDown = setCoursesDropList();
        courseButton.getSelectionModel().select(courseDAO.getOneById(quiz.getIdCourse()));
        courseButton.setOnAction(event -> keuzeDropDown.getSelectionModel().getSelectedItem());
    }

    public void doMenu() {
        Main.getSceneManager().showManageQuizScene();
    }

    public void doCreateQuiz() {
        boolean correctFilledOUt = checkFields();
        if (correctFilledOUt) {
            int courseId = courseButton.getSelectionModel().getSelectedItem().getIdCourse();
            String quizeName = textfieldQuizName.getText();
            int succesDefinition = Integer.parseInt(textfieldSuccesDefinition.getText());
            Quiz quiz = new Quiz(quizeName, succesDefinition, courseId);
            quizDAO.storeOne(quiz);
            Alert quizStored = new Alert(Alert.AlertType.INFORMATION);
            quizStored.setHeaderText("De quiz is toegevoegd");
            quizStored.show();
            doClear();
        }
    }

    public void doUpdateQuiz(Quiz quiz) {
        boolean correctFilledOut = checkFields();
        if (correctFilledOut) {
            quiz.setNameQuiz(textfieldQuizName.getText());
            quiz.setSuccesDefinition(Integer.parseInt(textfieldSuccesDefinition.getText()));
            quiz.setIdCourse(courseButton.getSelectionModel().getSelectedItem().getIdCourse());
            quizDAO.updateOne(quiz);
            Alert quizStored = new Alert(Alert.AlertType.INFORMATION);
            quizStored.setHeaderText("De quiz is aangepast");
            quizStored.show();
            doClear();
        }
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

//    methode om te controleren of alle velden zijn gevuld bij het toevoegen/wijzigen van een nieuwe quiz
        public boolean checkFields() {
        boolean allFields = false;
        boolean quizname = false;
        boolean succesdefinition = false;
        boolean courseid = false;


        Alert foutmelding = new Alert(Alert.AlertType.ERROR);

        if (!(textfieldQuizName.getText().isEmpty())) {
            quizname = true;
        } else if(textfieldQuizName.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen quiznaam opgegeven");
            foutmelding.show();
        }
        if (!(textfieldSuccesDefinition.getText().isEmpty())) {
                succesdefinition = true;
        } else if(textfieldSuccesDefinition.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen succes definitie opgegeven");
            foutmelding.show();
            }
        if(!(courseButton.getSelectionModel().isEmpty())) {
            courseid= true;
        } else if(courseButton.getSelectionModel().isEmpty()) {
            foutmelding.setContentText("Je hebt geen course geselecteerd");
            foutmelding.show();
        }
        if(textfieldQuizName.getText().isEmpty() && textfieldSuccesDefinition.getText().isEmpty()) {
            foutmelding.setContentText("Je hebt geen quizname én succes definitie opgegeven");
            foutmelding.show();
        }
        if(quizname && succesdefinition && courseid) {
            allFields = true;
        }
        System.out.println(allFields);
        return allFields;
    }


}