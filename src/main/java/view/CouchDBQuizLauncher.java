package view;

/**
 * @Author Shaun van Ouwerkerk
 */


import com.google.gson.Gson;
import database.couchDB.CouchDBaccessQuiz;
import database.couchDB.QuizCouchDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import model.Course;
import model.Quiz;
import java.util.ArrayList;


public class CouchDBQuizLauncher {

	private CouchDBaccessQuiz couchDBaccessQuiz;
	private QuizCouchDAO quizCouchDAO;
	private QuizDAO quizDAO;
	private DBAccess dbAccess;

	public CouchDBQuizLauncher() {
		super();
		couchDBaccessQuiz = new CouchDBaccessQuiz();
		quizCouchDAO = new QuizCouchDAO(couchDBaccessQuiz);
		this.dbAccess = Main.getDBaccess();
	}

	public static void main(String[] args) {
		CouchDBQuizLauncher couchDBQuizLauncher = new CouchDBQuizLauncher();
		couchDBQuizLauncher.run();
		couchDBQuizLauncher.testWithQuizes();
	}

	public void run() {
//		Maak een connectie met CouchDB, gebruik het CouchDbaccess object.
		try {
			couchDBaccessQuiz.setupConnection();
			System.out.println("Connection open");
		} catch (Exception e) {
			System.out.println("\nEr is iets fout gegaan\n");
			e.printStackTrace();
		}
	}

	// Voorbeelden om te werken met Couch DB
	public void testWithQuizes () {
		Quiz quiz1 = new Quiz("CouchdbQuiz", 8);
		Gson gson = new Gson();
		String quiz1Json = gson.toJson(quiz1);
		System.out.println("Quiz1 als Json: ");
		System.out.println(quiz1Json);

		// Voorbeeld van een Json string met complex object
		Quiz quiz2 = new Quiz("Landenkennis", 7);
		Course course1 = new Course(1, "Topografie", 22);
		quiz1.setIdCourse(course1.getIdCourse());
		String[] landen = {"Spanje", "Engeland"};
		quiz2.setCategorie(landen);
		String quize2Json = gson.toJson(quiz2);
		System.out.println("Quiz2 als Json: ");
		System.out.println(quize2Json);

		// Voorbeeld om van een Json string weer een Quiz(class) te maken
		Quiz quiz3 = gson.fromJson(quiz1Json, Quiz.class);
		System.out.println("Json omgezet naar quiz, zal String moeten tonen van Quiz (naam van quiz)");
		System.out.println(quiz3);

		// Voorbeeld om Quiz op te halen uit CouchDb
		Quiz quizId = quizCouchDAO.getQuizByDocId("9e582e974ec84e41b05a1454518c2585");
		System.out.println("Quiz uit COUCH DB: \n" + quizId);

		// Voorbeeld om quiz1 op te slaan in CouchDb
		saveOneQuiz(quiz1);

		// Voorbeeld om alle quizen op te slaan in CouchDb
		saveAllQuizes();
	}

	// Voorbeeldmethode om een quiz op te slaan in CouchDB
	public void saveOneQuiz (Quiz quiz) {
		quizCouchDAO.saveSingleQuiz(quiz);
	}

	// Voorbeeldmethode om alle quizen uit de database op de slaan in CouchDB
	public void saveAllQuizes () {
		this.quizDAO = new QuizDAO(dbAccess);
		ArrayList<Quiz> allQuizesDatabase = quizDAO.getAll();
		for (Quiz quiz : allQuizesDatabase) {
			saveOneQuiz(quiz);
		}
	}
}
