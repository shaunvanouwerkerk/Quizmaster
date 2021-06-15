package database.mysql;

/**
 * @author Shaun van Ouwerkerk
 */
// TODO: 08/06/2021 1.Update DAO MAKEN


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Question;
import model.Quiz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class QuizDAO extends AbstractDAO implements GenericDAO <Quiz> {


    public QuizDAO(DBAccess dbAccess) { super(dbAccess); }


    @Override
    public ArrayList<Quiz> getAll() {
        ArrayList<Quiz> resultlist = new ArrayList<>();
        String sql = "Select * FROM Quiz";
        Quiz quiz;

        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();

            while (resultSet.next()) {
                String namequiz = resultSet.getString("nameQuiz");
                int succesdefinition = resultSet.getInt("succesDefinition");
                int idquiz = resultSet.getInt("idQuiz");
                int idcourse = resultSet.getInt("idCourse");
                quiz = new Quiz(namequiz, succesdefinition, idquiz, idcourse);
                resultlist.add(quiz);
            }
        } catch (SQLException sqlFout) {
            System.out.println("SQL fout" + sqlFout.getMessage());
        }
        return resultlist;
    }

    @Override
    public Quiz getOneById(int quizId) {
        Quiz quiz = null;
        String sql = "SELECT * FROM Quiz WHERE idQuiz = ?";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quizId);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String namequiz = resultSet.getString("nameQuiz");
                int succesdefinition = resultSet.getInt("succesDefinition");
                int idcourse = resultSet.getInt("idCourse");
                quiz = new Quiz(namequiz, succesdefinition, quizId, idcourse);

            } else {
                System.out.println("Er bestaat geen gebruiker met dit ID");

            }
        } catch (SQLException sqlException) {
            System.out.println("SQL fout" + sqlException.getMessage());
        }
        return quiz;
    }

    public ArrayList<Quiz> getQuizesByCourseId(int courseId) {
        ArrayList<Quiz> allQuizes = new ArrayList<>();
        Quiz quiz = null;
        String sql = "SELECT * FROM Quiz WHERE idCourse = ?";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String namequiz = resultSet.getString("nameQuiz");
                int succesdefinition = resultSet.getInt("succesDefinition");
                int idquiz = resultSet.getInt("idQuiz");
                quiz = new Quiz(namequiz, succesdefinition,idquiz,courseId);
                allQuizes.add(quiz);

            } if (allQuizes.isEmpty()) {
                Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
                deleteAlert.setTitle("Geen quiz aanwezig");
                deleteAlert.setHeaderText("Er bestaat geen quiz bij deze cursus.");
                Optional<ButtonType> result = deleteAlert.showAndWait();
            }
        } catch (SQLException sqlException) {

            System.out.println("SQL fout" + sqlException.getMessage());
        }
        return allQuizes;
    }


    @Override
    public void storeOne(Quiz quiz) {

        String sql = "INSERT INTO Quiz (idCourse, nameQuiz, succesDefinition) VALUES (?,?,?) ;";

        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setInt(1, quiz.getIdCourse());
            preparedStatement.setString(2,quiz.getNameQuiz());
            preparedStatement.setInt(3,quiz.getSuccesDefinition());
            quiz.setIdQuiz(executeInsertStatementWithKey());


        } catch (SQLException sqlFout) {
            System.out.println("SQL fout: " + sqlFout.getMessage());
        }
    }

    public void updateOne(Quiz quiz) {

        String sql = "UPDATE quiz SET idCourse = ?, nameQuiz = ?, succesDefinition = ? WHERE idQuiz = ?";

        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setInt(1, quiz.getIdCourse());
            preparedStatement.setString(2,quiz.getNameQuiz());
            preparedStatement.setInt(3,quiz.getSuccesDefinition());
            preparedStatement.setInt(4,quiz.getIdQuiz());
            executeManipulateStatement();

        } catch (SQLException sqlFout) {
            System.out.println("SQL fout: " + sqlFout.getMessage());
        }
    }

    public void deleteOne(Quiz quiz){
        String sql = "DELETE FROM Quiz WHERE idQuiz = ? ";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1,quiz.getIdQuiz() );
            executeManipulateStatement();

        }catch (SQLException sqlFout) {
            System.out.println(sqlFout.getMessage());
        }

    }
    public boolean getQuizBasedOnIdCourse(int idCourse) {
        boolean questionsExistInDatabase = false;
        String sql = "SELECT * FROM quiz WHERE idCourse = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCourse);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                questionsExistInDatabase = true;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return questionsExistInDatabase;
    }

}
