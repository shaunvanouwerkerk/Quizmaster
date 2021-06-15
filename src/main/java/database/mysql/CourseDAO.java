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

    //methode om alle cursussen te selecteren
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

    //methode om een cursus te selecteren op basis van het id
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

    //methode om een nieuwe cursus op te slaan
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

    //methode om een cursus te wijzigen
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

    //???? waar is deze methode voor bedoelt?
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

    //methode om het aantal studenten per cursus op te vragen
    public int returnNumberOfStudentsByIdCourse (int idCourse){
        String sql = "SELECT idCourse, COUNT(*) aantalStudenten FROM studentincourse WHERE idCourse = ?;";
        int aantalStudenten = 0;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCourse);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                aantalStudenten = resultSet.getInt("aantalStudenten");
            }
            if (!(resultSet.next())) {
                return aantalStudenten;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return aantalStudenten;
    }

    //methode om cursussen te tonen waar een student zich voor kan inschrijven
    public ArrayList<Course> getCoursesStudentToSignIn(int idStudent) {
        String sql = "SELECT * FROM course WHERE idCourse NOT IN (SELECT idCourse FROM studentincourse WHERE idStudent = ?);";
        ArrayList<Course> courses = new ArrayList<>();
        Course result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idStudent);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String nameCourse = resultSet.getString("nameCourse");
                int idCourse = resultSet.getInt("idCourse");
                result = new Course(nameCourse,0);
                result.setIdCourse(idCourse);
                courses.add(result);
            }
        }
        catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return courses;
    }

    //methode om cursussen te tonen waar een student zich voor kan uitschrijven
    public ArrayList<Course> getCoursesStudentToSignOut(int idStudent) {
        String sql = "SELECT * FROM course C LEFT JOIN studentincourse S ON C.idCourse = S.idCourse WHERE idStudent = ?;";
        ArrayList<Course> courses = new ArrayList<>();
        Course result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idStudent);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String nameCourse = resultSet.getString("nameCourse");
                int idCourse = resultSet.getInt("idCourse");
                //Het id van de coordinator heeft geen toegevoegde waarde in dit course object.
                result = new Course(nameCourse,0);
                result.setIdCourse(idCourse);
                courses.add(result);
            }
        }
        catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return courses;
    }

    //methode voor het verwijderen van een inschrijving
    public void deleteCoursesStudentSignOut(int idCourse, int idStudent) {
        String sql = "DELETE FROM studentincourse \n" +
                "WHERE idStudent = ? AND idCourse = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idStudent);
            preparedStatement.setInt(2, idCourse);
            executeManipulateStatement();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    //methode voor het toevoegen van een inschrijving aan de tabel studentInCourse
    public void addCourseStudentSignIn(int idCourse, int idStudent) {
        String sql = "INSERT INTO studentincourse (idCourse, idStudent) VALUES(?, ?);";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCourse);
            preparedStatement.setInt(2, idStudent);
            executeManipulateStatement();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}







