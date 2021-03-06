package controller;

/*Author: Branko Visser
* */
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.QuizResult;
import model.User;
import view.CouchDBQuizResultsLauncher;
import view.Main;
import java.util.ArrayList;
import java.util.Optional;


public class ManageUsersController {

    private UserDAO userDAO;
    private DBAccess dbAccess;
    private ArrayList<User> allUsers;
    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher;

    @FXML
    private ListView<User> userList;
    @FXML
    private Label aantalGebruikers;
    @FXML
    public Button teamlogo;

    public ManageUsersController() {
        this.dbAccess = Main.getDBaccess();
        this.couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();
        couchDBQuizResultsLauncher.run();
    }

    public void setup() {
        this.userDAO = new UserDAO(dbAccess);
        allUsers = userDAO.getAll();
        for(User user : allUsers) {
            userList.getItems().add(user);
        }
        userList.getSelectionModel().selectFirst();
        setLabelRoleCount(userList.getSelectionModel().getSelectedItem().getRoleName());

        userList.setOnMouseClicked(mouseEvent -> setLabelRoleCount(userList.getSelectionModel()
                .getSelectedItem().getRoleName()));
        userList.setOnKeyPressed(keyEvent -> setLabelRoleCount(userList.getSelectionModel()
                .getSelectedItem().getRoleName()));
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {
        Main.getSceneManager().showCreateUserScene();
    }

    public void doUpdateUser() {
        Main.getSceneManager().showUpdateUserScene(userList.getSelectionModel().getSelectedItem());
    }

    public void doDeleteUser() {
        boolean userDeleted;
        User userToDelete = userList.getSelectionModel().getSelectedItem();

        //Eerst checken of de te verwijderen user een technisch beheerder is
        //Als dat zo is, kan het niet zo zijn dat er minder dat 1 technisch beheerder nog is aangezien er dan
        //geen gebruikers meer kunnen worden aangemaakt.

        boolean userToDelteIsTechAdmin = userToDelete.getRoleName().equals(Main.TECHBEHEER_ROL);
        boolean deleteTechUser = userDAO.checkNumberOfTecnicalAdmins();

        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Verwijderen gebruiker");
        deleteAlert.setHeaderText(String.format("Weet je zeker dat je gebruiker %s wilt verwijderen?",
                userToDelete));
        deleteAlert.setContentText("Dit kun je niet ongedaan maken.");

        Optional<ButtonType> result = deleteAlert.showAndWait();
        if (result.get() == ButtonType.OK) {

                //Conditie die checkt of de te verwijderen user zowel een technisch beheerder is ??n of er na het verwijderen
                //nog minimaal ????n technisch beheerder over zal zijn.
            if (userToDelteIsTechAdmin && deleteTechUser) {
                cannotDeleteAdmin();
            } else if(!(checkIfStudentCanBeDeleted())) {
                cannotDeleteStudent();
            } else {
                userDeleted = userDAO.deleteUser(userToDelete);
                if (userDeleted) {
                    deleteUserAlert(userToDelete);
                }
                Main.getSceneManager().showManageUserScene();
            }
        }
    }

    public void setLabelRoleCount(String role) {
        int sumRole = 0;
        for(User user : allUsers) {
            if(user.getRoleName().equals(role)) {
                sumRole++;
            }
        }
        aantalGebruikers.setText(String.format("Het aantal gebruikers met de rol %s is %d", role, sumRole));
    }

    public boolean checkIfStudentCanBeDeleted() {
        boolean canBeDeleted = true;
        User userToDelete = userList.getSelectionModel().getSelectedItem();
        ArrayList<QuizResult> quizResults = couchDBQuizResultsLauncher.getQuizResultsCouchDAO().getAllQuizResults();
        for (QuizResult quizResult: quizResults ) {
            if (quizResult.getIdGebruiker() == userToDelete.getIdUser()) {
                canBeDeleted = false;
                break;
            }
        }
        return canBeDeleted;
    }

    public void cannotDeleteStudent() {
        Alert cannotDeleteStudent = new Alert(Alert.AlertType.ERROR);
        cannotDeleteStudent.setTitle("Verwijdering onmogelijk");
        cannotDeleteStudent.setHeaderText("Gebruiker kan nu niet worden verwijderd.");
        cannotDeleteStudent.setContentText("De student heeft Quiz resultaten.");
        cannotDeleteStudent.show();
    }

    public void cannotDeleteAdmin() {
        Alert cannotDeleteTechAdmin = new Alert(Alert.AlertType.ERROR);
        cannotDeleteTechAdmin.setTitle("Verwijdering onmogelijk");
        cannotDeleteTechAdmin.setHeaderText("Gebruiker kan nu niet worden verwijderd.");
        cannotDeleteTechAdmin.setContentText("Er moet minimaal ????n Technisch Beheerder zijn na verwijdering.");
        cannotDeleteTechAdmin.show();
    }

    public void deleteUserAlert(User userToDelete) {
        Alert deleteInformation = new Alert(Alert.AlertType.INFORMATION);
        deleteInformation.setTitle("Gebruiker verwijderd");
        deleteInformation.setHeaderText(String.format("Gebruiker %s is verwijderd", userToDelete));
        deleteInformation.show();
    }
}
