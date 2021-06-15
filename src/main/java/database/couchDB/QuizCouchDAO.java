package database.couchDB;

/**
 * @Author Shaun van Ouwerkerk
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Quiz;


public class QuizCouchDAO {

	private CouchDBaccessQuiz couchDBaccessQuiz;
	private Gson gson;

	public QuizCouchDAO(CouchDBaccessQuiz couchDBaccessQuiz) {
		this.couchDBaccessQuiz = couchDBaccessQuiz;
		this.gson = new Gson();
	}

	public String saveSingleQuiz(Quiz quiz) {
		String jsonstring = gson.toJson(quiz);
		System.out.println(jsonstring);
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
		String doc_Id = couchDBaccessQuiz.saveDocument(jsonObject);
		return doc_Id;
	}

	public Quiz getQuizByDocId(String doc_id) {
		JsonObject json = couchDBaccessQuiz.getClient().find(JsonObject.class, doc_id);
		Quiz resultaat = gson.fromJson(json, Quiz.class);
		return resultaat;
	}
}


