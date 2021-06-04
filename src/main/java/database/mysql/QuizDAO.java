package database.mysql;

/**
 * @author Shaun van Ouwerkerk
 */

import javafx.scene.control.Alert;
import model.Quiz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDAO extends AbstractDAO implements GenericDAO <Quiz> {


    public QuizDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

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
    public Quiz getOneById(int id) {
        Quiz quiz = null;3
        String sql = "SELECT * FROM Quiz WHERE idQuiz = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String namequiz = resultSet.getString("nameQuiz");
                int succesdefinition = resultSet.getInt("succesDefinition");
                int idcourse = resultSet.getInt("idCourse");
                quiz = new Quiz(namequiz, succesdefinition, id, idcourse); // TODO: 03/06/2021 moet het ID onderdeel zijn van de quiz

            } else {
                System.out.println("Er bestaat geen gebruiker met dit ID");

// TODO: 03/06/2021 Onderstaande code lijkt niet te werken, krijg error
////                Alert foutmelding = new Alert(Alert.AlertType.ERROR);
////               foutmelding.setContentText("Er bestaat geen gebruiker met dit ID");
////                foutmelding.show();
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL fout" + sqlException.getMessage());
        }
        return quiz;
    }


    @Override
    public void storeOne(Quiz quiz) {
}
    // TODO: 03/06/2021  Onderstaande methode moet nog wachten op Course DAO en nog checken
///        CourseDAO courseDAO = new CourseDAO(DBAccess);
//        int gegenereerdeSleutel1 = courseDAO.storeOne(quiz);
//        int gegenereerdeSleutel2 = 0;
//        String sql = "INSERT INTO Quiz (idCourse, nameQuiz, succesDefinition) VALUES (?,?,?) ;";
//
//        try {
//            setupPreparedStatementWithKey(sql);
//            preparedStatement.setInt(1, gegenereerdeSleutel1);
//            preparedStatement.setString(2,quiz.getNameQuiz());
//            preparedStatement.setInt(3,quiz.getSuccesDefinition());
//            gegenereerdeSleutel2 = executeInsertStatementWithKey();
//            executeManipulateStatement();
//
//        } catch (SQLException sqlFout) {
//            System.out.println("SQL fout: " + sqlFout.getMessage());
//        }
//    }

}
