package database.couchDB;

import com.google.gson.*;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import model.QuizResult;
import model.User;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuizResultsCouchDAO {
    DBAccess dbAccess;
    private CouchDBaccessQuizResults couchDBaccessQuizResults;
    private Gson gson;

    public QuizResultsCouchDAO(CouchDBaccessQuizResults couchDBaccessQuizResults) {
        this.couchDBaccessQuizResults = couchDBaccessQuizResults;
        this.gson = new Gson();
    }

    //Test methode om te zien of het lukte om een quiz resultaat als Json te printen
    public void printQuizResultInJson(QuizResult quizResult) {
        // Magic box om LocalDateTime naar string om te zetten.
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE_TIME))).create();
        String quizResultJson = gson.toJson(quizResult);
        System.out.println("Resultaat is");
        System.out.println(quizResultJson);
    }

    //Methode om quiz resultaat op te slaan
    public String saveQuizResultInJson(QuizResult quizResult) {
        // Magic box om LocalDateTime naar string om te zetten.
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE_TIME))).create();
        String quizResultJson = gson.toJson(quizResult);
        System.out.println("Resultaat is");
        System.out.println(quizResultJson);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(quizResultJson).getAsJsonObject();
        String doc_Id = couchDBaccessQuizResults.saveDocument(jsonObject);
        return doc_Id;
    }











    public ArrayList<QuizResult> getAllResultsbyQuizIdWithStudentId() {
        QuizDAO quizDAO = new QuizDAO(dbAccess);
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
