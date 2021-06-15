package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    @Test
    void getIdQuiz() {
        Quiz quiz1 = new Quiz("Pubquiz",8,8);
        int actual = quiz1.getIdQuiz();
        int expected = 0;
        assertEquals(expected,actual);
    }

    @Test
    void getIdCourse() {
        Quiz quiz2 = new Quiz("Pubquiz2",5);
        int actual = quiz2.getIdCourse();
        int expected = 0;
        assertEquals(expected,actual);
    }

}