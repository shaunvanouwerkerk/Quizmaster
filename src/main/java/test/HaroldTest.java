package test;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import model.Course;

import java.util.ArrayList;

public class HaroldTest{
    public static void main(String[] args) {
        DBAccess dbAccess = new DBAccess("quizzydraw", "quizzyuser", "quizzyuserPW");
        dbAccess.openConnection();


        //controle werking Course class
        Course cursus1 = new Course(5,"HTML/CSS",25);
        System.out.println(cursus1);

        //controle werking methode gettAll in CourseDAO class
        CourseDAO test = new CourseDAO(dbAccess);
        System.out.println(test.getAll());

        //controle werking methode getOneById CourseDAO class
        System.out.println(test.getOneById(4));

        //controle werking methode storeOne
        test.storeOne(cursus1);




    }

    //Dit is Harold's test
}
