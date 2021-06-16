package view;
/*
 * @author Fiona Lampers
 * for testing of the quizResults DAO and couchDBaccess
 * */
import database.couchDB.CouchDBaccessQuizResults;
import database.couchDB.QuizResultsCouchDAO;

public class CouchDBQuizResultsLauncher {

    private CouchDBaccessQuizResults couchDBaccessQuizResults;
    private QuizResultsCouchDAO quizResultsCouchDAO;

    public CouchDBQuizResultsLauncher() {
        this.couchDBaccessQuizResults = new CouchDBaccessQuizResults();
        this.quizResultsCouchDAO = new QuizResultsCouchDAO(couchDBaccessQuizResults);
    }

    public void run() {
        try {
            couchDBaccessQuizResults.setupConnection();
            System.out.println("Connectie open");
        } catch (Exception exception) {
            System.out.println("Er is iets mis gegaan");
            exception.printStackTrace();
        }
    }

    public CouchDBaccessQuizResults getCouchDBaccessQuizResults() {
        return couchDBaccessQuizResults;
    }

    public QuizResultsCouchDAO getQuizResultsCouchDAO() {
        return quizResultsCouchDAO;
    }
}

