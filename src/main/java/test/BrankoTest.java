package test;

import database.couchDB.CouchDBaccessQuizResults;
import database.couchDB.QuizResultsCouchDAO;
import model.QuizResult;
import view.CouchDBQuizResultsLauncher;

import java.util.ArrayList;

public class BrankoTest {

    private CouchDBQuizResultsLauncher couchDBQuizResultsLauncher = new CouchDBQuizResultsLauncher();

    public static void main(String[] args) {
        ArrayList<QuizResult> quizResults = getQuizresults();
        System.out.println(quizResults);

    }

    public static ArrayList<QuizResult> getQuizresults (){
        CouchDBaccessQuizResults couchDBaccessQuizResults = new CouchDBaccessQuizResults();
        QuizResultsCouchDAO quizResultsCouchDAO = new QuizResultsCouchDAO(couchDBaccessQuizResults);
        ArrayList<QuizResult> allQuizresults = new ArrayList();
        allQuizresults = quizResultsCouchDAO.getAllQuizResults();
        return allQuizresults;
    }

    //dit is een test

    // TODO: 01/06/2021 Shaun toevoeging 2

 

}
