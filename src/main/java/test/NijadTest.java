package test;

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class NijadTest {

    public static void main(String[] args) {

        // Vraag met antwoorden in een willekeurige orde uitprinten
        System.out.println("-----Vraag met antwoorden in een willekeurige orde uitprinten-----");
        Question testVraag = new Question(1, "Welk project team is het beste uit de lijst?",
                "Quaranteam", "team 2","team 3", "team 4");
        System.out.println(testVraag);

        // Testen: een vraag uit Database halen
        DBAccess dbAccess = Main.getDBaccess();
        QuestionDAO questionDAO = new QuestionDAO(dbAccess);
        Question question1 = questionDAO.getOneById(1);
        System.out.println("-----Testen: een vraag uit Database halen-----");
        System.out.println(question1);

        // Testen: een vraag in database opslaan
        // questionDAO.storeOne(testVraag);

        // Testen: haal alle vragen uit de database
        ArrayList<Question> alleVragen = questionDAO.getAll();
        System.out.println("-----Testen: haal alle vragen uit de database-----");
        for (Question question:alleVragen ) {
            System.out.println(question);
        }

        // Testen: haal alle vragen die bij een quiz behoren uit de database
        System.out.println("-----Testen: haal alle vragen die bij een quiz behoren uit de database-----");
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        /*ArrayList<Question> alleVragenPerQuiz = questionDAO.getAllperQuiz(quizDAO.getOneById(1));
        for (Question question: alleVragenPerQuiz) {
            System.out.println(question);
        }*/

        // Testen: haal alle vragen die bij een quiz behoren uit de database
        System.out.println("-----Testen: haal alle vragen die bij een quiz behoren uit de database-----");
        System.out.println("-----Als er geen vragen voor een specifieke cursus zijn-----");
        ArrayList<Question> legeQuizzen = questionDAO.getAllperQuiz(quizDAO.getOneById(2));
        for (Question question: legeQuizzen) {
            System.out.println(question);
        }


    }




}
