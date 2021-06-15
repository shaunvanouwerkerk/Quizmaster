package database.couchDB;

import com.google.gson.Gson;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import model.Quiz;
import model.QuizResult;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CouchQuizResultsDAO {
    private CouchDBaccessUser couchDBaccess;
    private Gson gson;

    public CouchQuizResultsDAO(CouchDBaccessUser couchDBaccess) {
        this.couchDBaccess = couchDBaccess;
        this.gson = new Gson();
    }

    //Methode om alle Quizresults uit de SQL DB te halen met een QuizID

//    public ArrayList <QuizResult> getAllbyQuizId (int idQuiz) {
//        ArrayList<QuizResult> quizResultsbyQuizId = new ArrayList<>();
//        for(QuizResult quizResult : quizResultsbyQuizId) {
//
//        }
//    }



}
