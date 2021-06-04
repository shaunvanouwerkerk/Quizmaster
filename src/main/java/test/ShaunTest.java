package test;
/**
 * @author Shaun van Ouwerkerk
 */

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import model.Quiz;

import java.util.ArrayList;

public class ShaunTest {


    public static void main(String[] args) {

        DBAccess dbAccess = new DBAccess("quizzydraw", "quizzyuser", "quizzyuserPW");
        dbAccess.openConnection();


        // 1. Test voor get all Quiz uit database
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        ArrayList<Quiz> alleQuizen = quizDAO.getAll();
        for (Quiz quiz : alleQuizen){
            System.out.println(quiz);
        }

        // 2. Test voor get een bepaalde quiz met een idee
        Quiz quiz2 = quizDAO.getOneById(2);
        System.out.println(quiz2);







        }
    }

