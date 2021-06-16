package view;
/*
 * @Author Nijad Nazarli
 */

import database.couchDB.CouchDBaccessQuestion;
import database.couchDB.QuestionCouchDAO;
import model.Question;
;

public class CouchDBQuestionLauncher {

    private CouchDBaccessQuestion couchDBaccessQuestion;
    private QuestionCouchDAO questionCouchDAO;

    public CouchDBQuestionLauncher() {
        this.couchDBaccessQuestion = new CouchDBaccessQuestion();
        this.questionCouchDAO = new QuestionCouchDAO(couchDBaccessQuestion);
    }

    public static void main(String[] args) {
        CouchDBQuestionLauncher userLauncher = new CouchDBQuestionLauncher();
        userLauncher.run();

        // 1. Print Question as JSON String
        Question question = new Question(1, "Dit is een vraag omgezet naar JSON string",
                "Json AntwoordA", "Json AntwoordB", "JsonAntwoordC", "Json Antwoord D");
        userLauncher.questionCouchDAO.printQuestionAsJSONString(question);
        // 2. Save Question in CouchDB
        // userLauncher.questionCouchDAO.saveQuestion(question);
        // 3. Save All questions in CouchDB
        // userLauncher.questionCouchDAO.saveAllQuestionsFromSQLDatabase();
        // 4. Get one Question from CouchDB by questionId
        Question question2 = userLauncher.questionCouchDAO.getOneQuestionById(3);
        System.out.println(question2);
    }

    public void run() {
        try {
            couchDBaccessQuestion.setupConnection();
            System.out.println("Connectie open");
        } catch (Exception exception) {
            System.out.println("Er is iets mis gegaan");
            exception.printStackTrace();
        }
    }
}
