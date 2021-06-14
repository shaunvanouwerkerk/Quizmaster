package test;

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import model.Question;
import model.Quiz;
import view.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NijadTest {

    public static void main(String[] args) {

        /*// Vraag met antwoorden in een willekeurige orde uitprinten
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
        *//*ArrayList<Question> alleVragenPerQuiz = questionDAO.getAllperQuiz(quizDAO.getOneById(1));
        for (Question question: alleVragenPerQuiz) {
            System.out.println(question);
        }*//*

        // Testen: haal alle vragen die bij een quiz behoren uit de database
        System.out.println("-----Testen: haal alle vragen die bij een quiz behoren uit de database-----");
        System.out.println("-----Als er geen vragen voor een specifieke cursus zijn-----");
        ArrayList<Question> legeQuizzen = questionDAO.getAllperQuiz(quizDAO.getOneById(2));
        for (Question question: legeQuizzen) {
            System.out.println(question);
        }*/

        // Een vraag in aanpassen
        DBAccess dbAccess = Main.getDBaccess();
        QuestionDAO questionDAO = new QuestionDAO(dbAccess);
        // Question question = new Question(11, 5, "Dit is een aangepaste vraag", "juiste antwoord",
        //        "b", "c", "d");
        // questionDAO.updateOne(question);
        Question question = questionDAO.getOneById(2);
        String[] answers = setUpShuffledAnswers(question);
        for (int i = 0; i < 4; i++) {
            System.out.println(answers[i]);
        }


    }

    public static String[] setUpShuffledAnswers (Question question) {
        String[] antwoorden = new String[4];
        antwoorden[0] = question.getAnswerA();
        antwoorden[1] = question.getAnswerB();
        antwoorden[2] = question.getAnswerC();
        antwoorden[3] = question.getAnswerD();

        // Shuffle answers
        List<String> listToBeShuffled = Arrays.asList(antwoorden);
        Collections.shuffle(listToBeShuffled);
        listToBeShuffled.toArray(antwoorden);

        return antwoorden;
    }

}
