package database.mysql;
/*
* Author Harold Stevens
* */

import javafx.scene.control.Alert;
import model.Course;
import model.Question;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO extends AbstractDAO implements GenericDAO<Course>{

    public CourseDAO (DBAccess dbAccess) {super(dbAccess);}


    @Override
    public ArrayList<Course> getAll() {
        String sql = "SELECT * FROM Course";
        ArrayList<Course> listCourses = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Course course;
            while (resultSet.next()) {
                String nameCourse = resultSet.getString("nameCourse");
                int idCoordinatorCourse = resultSet.getInt("idCoordinatorCourse");
                course = new Course(nameCourse, idCoordinatorCourse);
                course.setIdCourse(resultSet.getInt("idCourse"));
                listCourses.add(course);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return listCourses;
    }

    @Override
    public Course getOneById(int id) {
        String sql = "SELECT * FROM Course Where idCourse = ?";
        Course result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String nameCourse = resultSet.getString("nameCourse");
                int idCoordinatorCourse = resultSet.getInt("idCoordinatorCourse");
                result = new Course(nameCourse,idCoordinatorCourse);
                result.setIdCourse(id);
            } else {
                System.out.println("Cursus met dit id-nummer bestaat niet");
            }
        }
        catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return result;
    }

    /*@Override
    public void storeOne(Course course) {
        String sql = "Insert into Course(nameCourse, idCoordinatorCourse) values(?,?) ;";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, course.getNameCourse());
            preparedStatement.setInt(2, course.getIdCoordinator());
            int key = executeInsertStatementWithKey();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }*/
    @Override
    public void storeOne(Course course) {
        String sql = "Insert into Course(nameCourse, idCoordinatorCourse) values(?,?) ;";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, course.getNameCourse());
            preparedStatement.setInt(2, course.getIdCoordinator());
            int key = executeInsertStatementWithKey();
            course.setIdCourse(key);
            Alert opgeslagen = new Alert(Alert.AlertType.CONFIRMATION);
            opgeslagen.setContentText(String.format("Cursus %s is opgeslagen!", course.getNameCourse()));
            opgeslagen.show();
        } catch (SQLException sqlException) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            if(sqlException.getMessage().contains("Duplicate")) {
                foutmelding.setContentText("Deze cursus bestaat al! Cursus is niet opgeslagen.");
            } else {
                foutmelding.setContentText("Cursus kon niet worden opgeslagen.");
            }
            foutmelding.show();
            System.out.println(sqlException.getMessage());
        }
    }

    public void storeOne(User user) {
        String sql = "INSERT INTO user(password, name, roleName) VALUES(?, ?, ?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getRoleName());
            int key = executeInsertStatementWithKey();
            user.setIdUser(key);
            Alert opgeslagen = new Alert(Alert.AlertType.CONFIRMATION);
            opgeslagen.setContentText(String.format("Gebruiker met ID: %d, username %s met als rol %s " +
                    "is opgeslagen!", user.getIdUser(), user.getUsername(), user.getRoleName()));
            opgeslagen.show();
        } catch (SQLException sqlException) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            if(sqlException.getMessage().contains("Duplicate")) {
                foutmelding.setContentText("Deze gebuikersnaam bestaat al! Gebruiker is niet opgslagen.");
            } else {
                foutmelding.setContentText("Gebruiker kon niet worden opgeslagen.");
            }
            foutmelding.show();
            System.out.println(sqlException.getMessage());
        }
    }
    //methode voor het verwijderen van een Course
    public void deleteCourse(Course course) {
        String sql = "DELETE FROM course WHERE idCourse = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, course.getIdCourse());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }


    public ArrayList<Course> getCoursesByIdCoordinator(int id) {
        String sql = "SELECT * FROM Course Where idCoordinatorCourse = ?";
        ArrayList<Course> courses = new ArrayList<>();
        Course result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String nameCourse = resultSet.getString("nameCourse");
                int idCourse = resultSet.getInt("idCourse");
                result = new Course(nameCourse,id);
                result.setIdCourse(idCourse);
                courses.add(result);
            }
        }
        catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return courses;
    }
    public void updateCourse(Course course) {
        String sql = "UPDATE course SET nameCourse = ?, idCoordinatorCourse = ? WHERE idCourse = ?";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, course.getNameCourse());
            preparedStatement.setInt(2, course.getIdCoordinator());
            preparedStatement.setInt(3, course.getIdCourse());
            System.out.println(course);
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            if(sqlException.getMessage().contains("Duplicate")) {
                foutmelding.setContentText("Deze cursus bestaat al! Cursus is niet gewijzigd.");
            } else {
                foutmelding.setContentText("Cursus kon niet worden gewijzigd.");
            }
            foutmelding.show();
            System.out.println(sqlException.getMessage());
        }
    }

    }

