package database.mysql;
/*
* Author Harold Stevens
* */

import model.Course;

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

    @Override
    public void storeOne(Course course) {
        String sql = "Insert into Course(nameCourse, idCoordinatorCourse) values(?,?) ;";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, course.getNameCourse());
            preparedStatement.setInt(2, course.getIdCoordinator());
            int key = executeInsertStatementWithKey();
            course.setIdCourse(key);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    public ArrayList<Course> getCoursesByIdCoordinator(int id) {
        String sql = "SELECT * FROM Course Where idCoordinator = ?";
        ArrayList<Course> courses = new ArrayList<>();
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
                courses.add(result);
            } else {
                System.out.println("Er zijn geen courses voor deze Coordinator");
            }
        }
        catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return courses;
    }

    }

