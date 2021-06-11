package model;

        import org.junit.jupiter.api.Test;

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
}