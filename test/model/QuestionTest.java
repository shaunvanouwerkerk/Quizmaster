package model;

/*
 * @Author Nijad Nazarli
 */

        import org.junit.jupiter.api.Test;

        import java.util.ArrayList;

        import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void testToString() {
        Question testVraag = new Question(10, "Wat is het hoogste punt van Nederland?",
                "De Vaalserberg", "Vaalseberg", "De Vaalberg", "De Vaalsebert");
        String actual = testVraag.toString();
        String expected = "Wat is het hoogste punt van Nederland?";
        assertEquals(expected, actual);
    }

    @Test
    void testArrayListWithQuestions () {
        Question testVraag1 = new Question(1, 1, "Test vraag 1?", "A1", "B1",
                "C1", "D1");
        Question testVraag2 = new Question(1, 1, "Test vraag 2?", "A2", "B2",
                "C2", "D2");
        Question testVraag3 = new Question(1, 1, "Test vraag 3?", "A3", "B3",
                "C3", "D3");
        ArrayList<Question> arrayListQuestions = new ArrayList<>();
        arrayListQuestions.add(testVraag1);
        arrayListQuestions.add(testVraag2);
        arrayListQuestions.add(testVraag3);

        int expected = 3;
        int actual = arrayListQuestions.size();

        assertEquals(expected, actual);
    }

    @Test
    void testIdQuiz () {
        Question testVraag1 = new Question(1, "Test vraag 1?", "A1", "B1", "C1", "D1");
        int expectedQuestionId = 0;
        int actualQuestionId = testVraag1.getIdQuestion();

        assertEquals(expectedQuestionId, actualQuestionId);
    }

}