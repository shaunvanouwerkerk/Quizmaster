package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {


    @Test
    void testToString() {
        Course cursus1= new Course(1,"Programming",3);
        String actual = cursus1.toString();
        String expected = "Programming";
        assertEquals(expected, actual);
    }
    @Test
    void getIdCourse() {
        Course cursus2 = new Course(1,"Programming",3);
        int actual = cursus2.getIdCourse();
        int expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    void getNameCourse(){
        Course cursus2 = new Course(1,"Programming",3);
        String actual = cursus2.getNameCourse();
        String expected = "Programming";
    }

    @Test
    void getIdCoordinator(){
        Course cursus2 = new Course(1,"Programming",3);
        int actual = cursus2.getIdCoordinator();
        int expected = 3;
    }


}