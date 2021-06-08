package test;
/**
 * @author Shaun van Ouwerkerk
 */

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import model.Course;
import model.Quiz;
import model.User;
import view.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShaunTest {


    public static void main(String[] args) {

//    public Course getOneByIdCoordinator(int idCoordinator) {
//        String sql = "SELECT * FROM Course Where idCoordinatorCourse = ?";
//        Course result = null;
//        try {
//            setupPreparedStatement(sql);
//            preparedStatement.setInt(1, idCoordinator);
//            ResultSet resultSet = executeSelectStatement();
//            if (resultSet.next()) {
//                String nameCourse = resultSet.getString("nameCourse");
//                int idCoordinatorCourse = idCoordinator;
//                result = new Course(nameCourse,idCoordinatorCourse);
//                result.setIdCourse(idCoordinator);
//            } else {
//                System.out.println("Cursus met dit id-nummer bestaat niet");
//            }
//        }
//        catch (SQLException sqlException){
//            System.out.println("SQL error " + sqlException.getMessage());
//        }
//        return result;
    }
}





