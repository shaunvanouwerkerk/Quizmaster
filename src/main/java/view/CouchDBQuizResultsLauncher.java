package view;
/*
 * @author Fiona Lampers
 * for testing of the quizResults DAO and couchDBaccess
 * */
import database.couchDB.CouchDBaccessQuizResults;
import database.couchDB.QuizResultsCouchDAO;
import model.QuizResult;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class CouchDBQuizResultsLauncher {

    private CouchDBaccessQuizResults couchDBaccessQuizResults;
    private QuizResultsCouchDAO quizResultsCouchDAO;

    public CouchDBQuizResultsLauncher() {
        this.couchDBaccessQuizResults = new CouchDBaccessQuizResults();
        this.quizResultsCouchDAO = new QuizResultsCouchDAO(couchDBaccessQuizResults);
    }

    public static void main(String[] args) {
        CouchDBQuizResultsLauncher quizResultLauncher = new CouchDBQuizResultsLauncher();
        quizResultLauncher.run();
        // Om te testen is hier QuizResults aangemaakt
        QuizResult result1 = new QuizResult(1,2, 30, LocalDateTime.of(2020, 12, 8, 12, 30));
        QuizResult result2 = new QuizResult(2,2, 20, LocalDateTime.of(2020, 12, 8, 12, 30));

        ArrayList<QuizResult> allResults = new ArrayList<>();
        allResults.add(result1);
        allResults.add(result2);
        System.out.println(allResults);
        quizResultLauncher.quizResultsCouchDAO.printQuizResultInJson(result1);

        ArrayList<QuizResult> alleQuizResults = quizResultLauncher.quizResultsCouchDAO.getAllQuizResults();
        System.out.println(alleQuizResults);

        System.out.println("-------------------------------------------------------------");
        System.out.println();
        Collections.sort(alleQuizResults);

        for(QuizResult quizResult: alleQuizResults) {
            System.out.println(quizResult.getIdGebruiker());
            System.out.println(quizResult.getIdQuiz());
            System.out.println("");
        }



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

