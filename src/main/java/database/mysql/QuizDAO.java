package database.mysql;

/**
 * @author Shaun van Ouwerkerk
 */


import model.Quiz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

}
