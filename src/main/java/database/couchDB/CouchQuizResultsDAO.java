package database.couchDB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.QuizResult;


import java.util.ArrayList;
import java.util.List;

public class CouchQuizResultsDAO {
    private CouchDBaccessQuizResults couchDBaccessQuizResults;
    private Gson gson;

    public CouchQuizResultsDAO(CouchDBaccessQuizResults couchDBaccessQuizResults) {
        this.couchDBaccessQuizResults = couchDBaccessQuizResults;
        this.gson = new Gson();
    }

    public void saveSingleResult(QuizResult quizResult) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(gson.toJson(quizResult)).getAsJsonObject();
        couchDBaccessQuizResults.saveDocument(jsonObject);
    }

    public ArrayList<QuizResult> getAllResultsbyQuizIdWithStudentId() {
        ArrayList<QuizResult> allQuizresults = new ArrayList<>();
        QuizResult quizResult = null;
        List<JsonObject> allQuizresultsCouchDb = couchDBaccessQuizResults.getClient()
                .view("_all_docs").includeDocs(true)
                .query(JsonObject.class);
        System.out.println(allQuizresultsCouchDb );
        for(JsonObject object : allQuizresultsCouchDb ) {
            quizResult = gson.fromJson(object, QuizResult.class);
            allQuizresults.add(quizResult);
        }
        return allQuizresults;
    }

}
