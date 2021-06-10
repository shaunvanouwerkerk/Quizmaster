package view;

import controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class SceneManager {

    private Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Laadt een scene
    public FXMLLoader getScene(String fxml) {
        Scene scene;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            scene = new Scene(root);
            primaryStage.setScene(scene);
            return loader;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    public void setWindowTool() {
        FXMLLoader loader = getScene("/view/fxml/windowTool.fxml");
        if (loader != null) {
            WindowToolController controller = loader.getController();
            controller.populateScreenMenu();
        } else {
            System.out.println("set windowTool: Loader is not initialized");
            System.out.flush();
        }
    }

    public void showLoginScene() {
        getScene("/view/fxml/login.fxml");
    }

    public void showWelcomeScene() {
        FXMLLoader loader = getScene("/view/fxml/welcomeScene.fxml");
        WelcomeController controller = loader.getController();
        controller.setup();
    }

    public void showManageUserScene() {
        FXMLLoader loader = getScene("/view/fxml/manageUsers.fxml");
        ManageUsersController controller = loader.getController();
        controller.setup();
    }

    public void showCreateUserScene() {
        FXMLLoader loader = getScene("/view/fxml/createUpdateUser.fxml");
        CreateUpdateUserController controller = loader.getController();
        controller.setupCreateUser();
    }

    public void showUpdateUserScene(User user) {
        FXMLLoader loader = getScene("/view/fxml/createUpdateUser.fxml");
        CreateUpdateUserController controller = loader.getController();
        controller.setupUpdateUser(user);
    }

    public void showManageCoursesScene() {
        FXMLLoader loader = getScene("/view/fxml/manageCourses.fxml");
        ManageCoursesController controller = loader.getController();
        controller.setup();
    }
    public void showCreateCourseScene() {
        FXMLLoader loader = getScene("/view/fxml/createUpdateCourse.fxml");
        CreateUpdateCourseController controller = loader.getController();
        controller.setupCreateCourse();
    }
    public void showUpdateCourseScene(Course course) {
        FXMLLoader loader = getScene("/view/fxml/createUpdateCourse.fxml");
        CreateUpdateCourseController controller = loader.getController();
        controller.setupUpdateCourse(course);
    }

    /*public void showCreateUpdateCourseScene(Course course) {
        FXMLLoader loader = getScene("/view/fxml/createUpdateCourse.fxml");
        CreateUpdateCourseController controller = loader.getController();
        controller.setup(course);
    }*/

    public void showManageGroupsScene() {
        FXMLLoader loader = getScene("/view/fxml/manageGroups.fxml");
        ManageGroupsController controller = loader.getController();
        controller.setup();
    }

    public void showCreateGroupScene() {
        FXMLLoader loader = getScene("/view/fxml/createUpdateGroup.fxml");
        CreateUpdateGroupController controller = loader.getController();
        controller.setupCreateGroup();
    }

    public void showUpdateGroupScene(Group group) {
        FXMLLoader loader = getScene("/view/fxml/createUpdateGroup.fxml");
        CreateUpdateGroupController controller = loader.getController();
        controller.setupUpdateGroup(group);
    }

    public void showManageQuizScene() {
        FXMLLoader loader = getScene("/view/fxml/manageQuizzes.fxml");
        ManageQuizzesController controller = loader.getController();
        controller.setup();
    }

    public void showCreateQuizScene(Quiz quiz) {
        FXMLLoader loader = getScene("/view/fxml/createUpdateQuiz.fxml");
        CreateUpdateQuizController controller = loader.getController();
        controller.setupCreateQuiz();
    }

    public void showUpdateQuizScene(Quiz quiz) {
        FXMLLoader loader = getScene("/view/fxml/createUpdateQuiz.fxml");
        CreateUpdateQuizController controller = loader.getController();
        controller.setupUpdateQuiz(quiz);
    }

    public void showManageQuestionsScene() {
        FXMLLoader loader = getScene("/view/fxml/manageQuestions.fxml");
        ManageQuestionsController controller = loader.getController();
        controller.setup();
    }

    public void showUpdateQuestionScene(Question question) {
        FXMLLoader loader = getScene("/view/fxml/createUpdateQuestion.fxml");
        CreateUpdateQuestionController controller = loader.getController();
        controller.setup(question);
    }

    public void showCreateQuestionScene() {
        FXMLLoader loader = getScene("/view/fxml/createUpdateQuestion.fxml");
        CreateUpdateQuestionController controller = loader.getController();
        controller.setupCreateQuestion();
    }

    public void showStudentSignInOutScene() {
        FXMLLoader loader = getScene("/view/fxml/studentSignInOut.fxml");
        StudentSignInOutController controller = loader.getController();
        controller.setup();
    }

    public void showSelectQuizForStudent() {
        FXMLLoader loader = getScene("/view/fxml/selectQuizForStudent.fxml");
        SelectQuizForStudentController controller = loader.getController();
        controller.setup();
    }

    public void showFillOutQuiz(Quiz quiz) {
        FXMLLoader loader = getScene("/view/fxml/fillOutQuiz.fxml");
        FillOutQuizController controller = loader.getController();
        controller.setup(quiz);
    }

    public void showStudentFeedback(Quiz quiz) {
        FXMLLoader loader = getScene("/view/fxml/studentFeedback.fxml");
        StudentFeedbackController controller = loader.getController();
        controller.setup(quiz);
    }

    public void showCoordinatorDashboard() {
        FXMLLoader loader = getScene("/view/fxml/coordinatorDashboard.fxml");
        CoordinatorDashboardController controller = loader.getController();
        controller.setup();
    }

    public void showLogoutScene() {
        getScene("/view/fxml/logout.fxml");
    }
}
