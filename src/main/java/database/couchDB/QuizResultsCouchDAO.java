package database.couchDB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.QuizResult;
import model.User;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuizResultsCouchDAO {
    private CouchDBaccessQuizResults couchDBaccessQuizResults;
    private Gson gson;

    public QuizResultsCouchDAO(CouchDBaccessQuizResults couchDBaccessQuizResults) {
        this.couchDBaccessQuizResults = couchDBaccessQuizResults;
        this.gson = new Gson();
    }

    //Test methode om te zien of het lukte om een quiz resultaat als Json te printen
    public void printQuizResultInJson(QuizResult quizResult) {
        String quizResultJson = gson.toJson(quizResult);
        System.out.println("Resultaat is");
        System.out.println(quizResultJson);
    }

////    QuizResult quizResult = saveSingleResult(2, 1, 30, LocalDateTime.now());
//    public void saveSingleResult(QuizResult eenQuizResult) {
//        JsonParser jsonParser = new JsonParser();
//        JsonObject jsonObject = jsonParser.parse(gson.toJson(eenQuizResult)).getAsJsonObject();
//        couchDBaccessQuizResults.saveDocument(jsonObject);
//    }

//    public ArrayList<QuizResult> getAllResultsbyQuizIdWithStudentId() {
//        ArrayList<QuizResult> allQuizresults = new ArrayList<>();
//        QuizResult quizResult = null;
//        List<JsonObject> allQuizresultsCouchDb = couchDBaccessQuizResults.getClient()
//                .view("_all_docs").includeDocs(true)
//                .query(JsonObject.class);
//        System.out.println(allQuizresultsCouchDb );
//        for(JsonObject object : allQuizresultsCouchDb ) {
//            quizResult = gson.fromJson(object, QuizResult.class);
//            allQuizresults.add(quizResult);
//        }
//        return allQuizresults;
//    }

}
