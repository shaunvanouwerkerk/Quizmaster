package database.mysql;
/*
 * @Author: Nijad Nazarli
 * */

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
        // Question op auto increment?
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
}
