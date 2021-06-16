package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

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

    @Test
    void checkNameQuizDuplicates(){
        Quiz quiz1 = new Quiz("Aardijkskunde",5,1,1);
        Quiz quiz2 = new Quiz("Geschiedenis",7,2,1);
        Quiz quiz3 = new Quiz("Aardrijksunde",8,3,2);
        Quiz quiz4 = new Quiz("Engels",7,4,2);
        ArrayList <Quiz> quizen = new ArrayList<>();
        quizen.add(quiz1);
        quizen.add(quiz2);
        quizen.add(quiz3);
        quizen.add(quiz4);

        int actual = quizen.lastIndexOf(quiz3);
        int expected = 2;
        assertEquals(expected,actual);
        }

    }

