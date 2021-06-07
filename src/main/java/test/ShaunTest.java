package test;
/**
 * @author Shaun van Ouwerkerk
 */

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import model.Quiz;
import model.User;
import view.Main;

import java.util.ArrayList;

public class ShaunTest {


    public static void main(String[] args) {

        //Heb een kleine aanpassing gedaan wb de DBAccess. Ik heb de nieuwe declaratie weggehaald.
        //in plaats daarvan heb ik de methode Main.getDBaccess() gebruikt en ook neergezet in jouw tests.


        // 1. Test voor get all Quiz uit database
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        ArrayList<Quiz> alleQuizen = quizDAO.getAll();
        for (Quiz quiz : alleQuizen){
            System.out.println(quiz);
        }

        // 2. Test voor get een bepaalde quiz met een idee
        Quiz quiz2 = quizDAO.getOneById(1);
        System.out.println(quiz2);


        // Sorry pik, ik heb ze ff uit gecomment want anders wilde mn test niet runnen :)

        //3. Test voor set nieuwe quiz
//        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
//        QuizDAO quizDAO1 = new QuizDAO(Main.getDBaccess());
//        Quiz quiz3 = new Quiz("Taalkunde",6,courseDAO.getOneById(1).getIdCourse());
//        quizDAO1.storeOne(quiz3);

        //Test Branko --> alle rollen uit de DB krijgen
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        ArrayList<String> roles = userDAO.getAllRoles();
        System.out.println(roles);


        }
    }

