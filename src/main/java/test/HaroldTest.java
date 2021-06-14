package test;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HaroldTest{
    public static void main(String[] args) {
        DBAccess dbAccess = new DBAccess("quizmaster", "userQuizmaster", "pwQuizmaster");
        dbAccess.openConnection();


        //controle werking Course class
        //Course cursus1 = new Course("HTML/CSS",25);
        //System.out.println(cursus1);

        //controle werking methode gettAll in CourseDAO class
        CourseDAO test = new CourseDAO(dbAccess);

        //System.out.println(test.getAll());

        //controle werking methode getNamesCoordinator in UserDAO class
        //UserDAO test1 = new UserDAO(dbAccess);
        //System.out.println(test1.getNamesCoordinators());

        //controle werking methode getOneById CourseDAO class
        //System.out.println(test.getOneById(4));

        //controle werking methode returnNumberOfStudentsByIdCourse
        System.out.println(test.returnNumberOfStudentsByIdCourse(8));

        //controle werking methode storeOne
        //test.storeOne(cursus1);




    }


    //Dit is Harold's test
}
