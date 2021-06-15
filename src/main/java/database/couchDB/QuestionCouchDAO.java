package database.couchDB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.mysql.QuestionDAO;
import model.Question;
import model.User;
import view.Main;

import java.util.ArrayList;
import java.util.List;

public class QuestionCouchDAO {

    private CouchDBaccessQuestion couchDBaccessQuestion;
    private Gson gson;
    private QuestionDAO questionDAO;

    public QuestionCouchDAO(CouchDBaccessQuestion couchDBaccessQuestion) {
        this.couchDBaccessQuestion = couchDBaccessQuestion;
        this.gson = new Gson();
        this.questionDAO = new QuestionDAO(Main.getDBaccess());
    }

    // Test methode om een Question naar JSON string om te zetten
    public void printQuestionAsJSONString (Question question) {
        String questionJson = gson.toJson(question);
        System.out.println(questionJson);
    }

    //Methode om een user op te slaan in couchDB
    public void saveQuestion (Question question) {
        String questionJson = gson.toJson(question);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(questionJson).getAsJsonObject();
        couchDBaccessQuestion.getClient().save(jsonObject);
    }

    //Methode om alle Questions uit de SQL db te halen en in CouchDB op te slaan
    public void saveAllQuestionsFromSQLDatabase() {
        ArrayList<Question> allQuestions = questionDAO.getAll();
        for (Question question :allQuestions) {
            saveQuestion(question);
        }
    }

    // Methode die een Question uit CouchDB ophaalt op basis van id
    public Question getOneQuestionById (int idQuestion) {
        ArrayList<Question> allQuestions = new ArrayList<>();
        Question returnQuestion = null;
        List<JsonObject> allQuestionsAsJSONObject = couchDBaccessQuestion.getClient().view("_all_docs").
                includeDocs(true).query(JsonObject.class);
        for (JsonObject jsonObject : allQuestionsAsJSONObject) {
            allQuestions.add(gson.fromJson(jsonObject, Question.class));
        }
        for (Question question:allQuestions ) {
            if (question.getIdQuestion() == idQuestion) {
                returnQuestion = question;
                return returnQuestion;
            }
        }
        return returnQuestion;
    }




}
