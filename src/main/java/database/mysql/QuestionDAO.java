package database.mysql;
/*
 * @Author: Nijad Nazarli
 */

import model.Question;
import model.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO extends AbstractDAO implements GenericDAO<Question>{

    public QuestionDAO (DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public void storeOne(Question question) {

        String sql = "INSERT INTO question(idQuiz, questionString, answerA, answerB, answerC, answerD) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        try {
             setupPreparedStatementWithKey(sql);
             preparedStatement.setInt(1, question.getIdQuiz());
             preparedStatement.setString(2, question.getQuestionString());
             preparedStatement.setString(3, question.getAnswerA());
             preparedStatement.setString(4, question.getAnswerB());
             preparedStatement.setString(5, question.getAnswerC());
             preparedStatement.setString(6, question.getAnswerD());
             question.setIdQuestion(executeInsertStatementWithKey());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

    @Override
    public ArrayList<Question> getAll() {
        ArrayList<Question> alleVragenUitDatabase = new ArrayList<>();
        String sql = "SELECT * FROM question;";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idQuestion = resultSet.getInt(1);
                int idQuiz = resultSet.getInt(2);
                String questionString = resultSet.getString(3);
                String answerA = resultSet.getString(4);
                String answerB = resultSet.getString(5);
                String answerC = resultSet.getString(6);
                String answerD = resultSet.getString(7);
                alleVragenUitDatabase.add(new Question(idQuestion, idQuiz, questionString,
                        answerA, answerB, answerC, answerD));
            }
            if (alleVragenUitDatabase.isEmpty()) {
                System.out.println("Er zijn geen vragen in de database");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return alleVragenUitDatabase;
    }

    // Methode die alle bij een bepaalde quiz behorende vragen uit database haalt
    public ArrayList<Question> getAllperQuiz (Quiz quiz) {
        ArrayList<Question> alleVragenUitDatabase = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE idQuiz = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quiz.getIdQuiz());
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idQuestion = resultSet.getInt(1);
                int idQuiz = resultSet.getInt(2);
                String questionString = resultSet.getString(3);
                String answerA = resultSet.getString(4);
                String answerB = resultSet.getString(5);
                String answerC = resultSet.getString(6);
                String answerD = resultSet.getString(7);
                alleVragenUitDatabase.add(new Question(idQuestion, idQuiz, questionString,
                        answerA, answerB, answerC, answerD));
            }
            if (alleVragenUitDatabase.isEmpty()) {
                System.out.println("Er zijn geen vragen voor deze quiz in de database");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return alleVragenUitDatabase;
    }

    public ArrayList<Question> getAllperLoggedInCoordinator (int idCoordinator) {
        ArrayList<Question> alleVragenUitDatabase = new ArrayList<>();
        String sql = "Select q.idQuestion, q.idQuiz, q.questionString, q.answerA, q.answerB, q.answerC, q.answerD " +
                "FROM question q JOIN " +
                "( Select * FROM quiz q WHERE idCourse IN ( Select idCourse from course where idCoordinatorCourse = ?)) " +
                "as jointTable WHERE q.idQuiz = jointTable.idQuiz;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCoordinator);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idQuestion = resultSet.getInt(1);
                int idQuiz = resultSet.getInt(2);
                String questionString = resultSet.getString(3);
                String answerA = resultSet.getString(4);
                String answerB = resultSet.getString(5);
                String answerC = resultSet.getString(6);
                String answerD = resultSet.getString(7);
                alleVragenUitDatabase.add(new Question(idQuestion, idQuiz, questionString,
                        answerA, answerB, answerC, answerD));
            }
            if (alleVragenUitDatabase.isEmpty()) {
                System.out.println("Er zijn nog geen vragen in de database");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return alleVragenUitDatabase;
    }

    @Override
    public Question getOneById(int questionId) {
        Question questionUitDatabase = null;
        String sql = "SELECT * FROM question WHERE idQuestion = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int idQuestion = resultSet.getInt(1);
                int idQuiz = resultSet.getInt(2);
                String questionString = resultSet.getString(3);
                String answerA = resultSet.getString(4);
                String answerB = resultSet.getString(5);
                String answerC = resultSet.getString(6);
                String answerD = resultSet.getString(7);
                questionUitDatabase = new Question(idQuestion, idQuiz, questionString,
                        answerA, answerB, answerC, answerD);
            } else {
                System.out.println("Er zijn geen vragen met deze vraag ID in de database");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return questionUitDatabase;
    }

    public void deleteOne(Question question) {
        String sql = "DELETE FROM question WHERE idQuestion = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, question.getIdQuestion());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void updateOne(Question question) {
        String sql = "UPDATE question SET questionString = ?, idQuiz = ?, answerA = ?, answerB = ?, answerC = ?, answerD = ? WHERE idQuestion = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, question.getQuestionString());
            preparedStatement.setInt(2, question.getIdQuiz());
            preparedStatement.setString(3, question.getAnswerA());
            preparedStatement.setString(4, question.getAnswerB());
            preparedStatement.setString(5, question.getAnswerC());
            preparedStatement.setString(6, question.getAnswerD());
            preparedStatement.setInt(7, question.getIdQuestion());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public ArrayList<Quiz> getQuizzesByLoggedInUser (int idCoordinator) {
        ArrayList<Quiz> idQuizzenUitDatabase = new ArrayList<>();
        String sql = "Select idQuiz, idCourse, nameQuiz, succesDefinition" +
                " from quiz WHERE idCourse IN ( Select idCourse from course where idCoordinatorCourse = ?);";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCoordinator);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idQuiz = resultSet.getInt(1);
                int idCourse = resultSet.getInt(2);
                String nameQuiz = resultSet.getString(3);
                int succesDefinition = resultSet.getInt(4);
                Quiz quiz = new Quiz(nameQuiz, succesDefinition, idQuiz, idCourse);
                idQuizzenUitDatabase.add(quiz);
            }
            if (idQuizzenUitDatabase.isEmpty()) {
                System.out.println("Er zijn nog geen quizzen in de database");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return idQuizzenUitDatabase;
    }

    public boolean getQuestionBasedOnIdQuiz(int idQuiz) {
        boolean questionsExistInDatabase = false;
        String sql = "SELECT * FROM question WHERE idQuiz = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idQuiz);
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
