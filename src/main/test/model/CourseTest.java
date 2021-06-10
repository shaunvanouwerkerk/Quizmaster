package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    public void unittestcourse() {
    try {
        Course cursus = new Course("Breien voor gevorderden", -9);
        fail();
    } catch (IllegalArgumentException fout){
        System.out.println("De coordinator id moet positief zijn");
    }

}


}