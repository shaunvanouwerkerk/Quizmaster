package controller;
/*
 * @Author Harold Stevens
 * */
import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Group;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CreateUpdateCourseController {
    private CourseDAO courseDAO;
    private UserDAO userDAO;
    private DBAccess dbAccess;
    private ArrayList<User>  allUsers;
    private ArrayList<User>  allCoordinators = new ArrayList<>();

    @FXML
    private TextField nameCourseTextfield;
    @FXML
    private Button addCourse;
    @FXML
    private Label titleLabel;
    @FXML
    private ComboBox<User> coordinatorDropDown;
    @FXML
    public Button teamlogo;

    public CreateUpdateCourseController() {
        this.dbAccess = Main.getDBaccess();
        this.courseDAO = new CourseDAO(dbAccess);
        this.userDAO = new UserDAO(dbAccess);
    }
    //methode voor het aanmaken van een nieuwe cursus
    public void setupCreateCourse(){
        ComboBox<User> keuzeDropDown = setUserDropList();
        coordinatorDropDown.getSelectionModel().selectFirst();
        coordinatorDropDown.getSelectionModel().getSelectedItem();
        coordinatorDropDown.setOnAction(event -> keuzeDropDown.getSelectionModel().getSelectedItem());
    }
    //methode voor het wijzigen van een bestaande cursus
    public void setupUpdateCourse(Course course){
        titleLabel.setText("Wijzig cursus");
        nameCourseTextfield.setText(course.getNameCourse());
        addCourse.setText("Wijzig cursus");
        addCourse.setOnAction(event -> doUpdateCourse(course));
        ComboBox<User> keuzeDropDown = setUserDropList();
        coordinatorDropDown.getSelectionModel().select(userDAO.getOneById(course.getIdCoordinator()));
        coordinatorDropDown.setOnAction(event -> keuzeDropDown.getSelectionModel().getSelectedItem());
    }

    public void doMenu(){
        Main.getSceneManager().showManageCoursesScene();
    }

    //methode voor het wegschrijven van een nieuwe cursus in de database
    public void doCreateCourse() {
        boolean correctFilledOut = checkFields();
        if (correctFilledOut) {
            String courseName = nameCourseTextfield.getText();
            int coordinatorId = coordinatorDropDown.getSelectionModel().getSelectedItem().getIdUser();
            Course course = new Course(courseName, coordinatorId);
            courseDAO.storeOne(course);
            doClear();
        }
    }

    //methode voor het vullen van de Combobox met coordinatoren
    public ComboBox<User> setUserDropList(){
        this.allUsers = userDAO.getAll();
        for (User coordinator : allUsers){
            if (coordinator.getRoleName().equals(Main.COORDINATOR_ROL)){
                this.allCoordinators.add(coordinator);
            }
        }
        ObservableList<User> observableList = FXCollections.observableList(allCoordinators);
        ComboBox<User> comboBox = new ComboBox<>(observableList);
        for (User user: observableList) {
                coordinatorDropDown.getItems().add(user);
        }
        return comboBox;
    }


    //methode voor het opslaan van wijzigingen in de database
    public void doUpdateCourse(Course course) {
        boolean correctFilledOut = checkFields();
        if (correctFilledOut) {
            System.out.println(course);
            course.setNameCourse(nameCourseTextfield.getText());
            course.setIdCoordinator(coordinatorDropDown.getSelectionModel().getSelectedItem().getIdUser());
            courseDAO.updateCourse(course);
            Alert cursusSuccesvolGewijzigd = new Alert(Alert.AlertType.INFORMATION);
            cursusSuccesvolGewijzigd.setTitle("");
            cursusSuccesvolGewijzigd.setHeaderText("De cursus is succesvol gewijzigd");
            cursusSuccesvolGewijzigd.show();
            Main.getSceneManager().showManageCoursesScene();
        }
    }

    //methode om te controleren of alle velden zijn gevuld bij het toevoegen van een nieuwe cursus
    public boolean checkFields() {
        boolean allFields = false;
        boolean coursName = false;

        Alert foutmelding = new Alert(Alert.AlertType.ERROR);

        if (!(nameCourseTextfield.getText().isEmpty())) {
            coursName = true;
        } else if(nameCourseTextfield.getText().isEmpty()) {
            foutmelding.setTitle("Ontbrekende gegevens");
            foutmelding.setHeaderText("Je hebt geen cursusnaam opgegeven");
            foutmelding.show();
        }
        if(coursName) {
            allFields = true;
        }
        System.out.println(allFields);
        return allFields;
    }


    //methode om de invoervelden leeg te maken
    public void doClear() {
        nameCourseTextfield.clear();
    }

   }




