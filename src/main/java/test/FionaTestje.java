package test;

import database.couchDB.CouchDBaccessQuizResults;
import database.couchDB.QuizResultsCouchDAO;
import model.QuizResult;
import view.Main;

import java.util.ArrayList;

public class FionaTestje {

    //Test

    CouchDBaccessQuizResults couchDBaccessQuizResults = new CouchDBaccessQuizResults();
    QuizResultsCouchDAO quizResultsCouchDAO = new QuizResultsCouchDAO(couchDBaccessQuizResults);

    public static void main(String[] args) {

        ArrayList<QuizResult> allResults = getQuizresults();







    }

    public static ArrayList<QuizResult> getQuizresults (){
        CouchDBaccessQuizResults couchDBaccessQuizResults = new CouchDBaccessQuizResults();
        QuizResultsCouchDAO quizResultsCouchDAO = new QuizResultsCouchDAO(couchDBaccessQuizResults);
        ArrayList<QuizResult> allQuizresults = new ArrayList();
        allQuizresults = quizResultsCouchDAO.getAllQuizResults();
        return allQuizresults;
    }



}
