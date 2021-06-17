package model;

import org.junit.jupiter.api.Test;
import view.Main;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    void testToString() {
        Group group1= new Group(1, "AA", 1, 1);
        String actual = group1.toString();
        String expected = "AA";
        assertEquals(expected, actual);
    }


    @Test
    void getIdGroup() {
        Group group1= new Group(1, "AA", 1, 1);
        int actual = group1.getIdCourse();
        int expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    void getNameGroup() {
        Group group1= new Group(1, "AA", 1, 1);
        String actual = group1.getNameGroup();
        String expected ="AA";
        assertEquals(expected,actual);
    }

    @Test
    void getIdCoordinator() {
        Group group2= new Group(1, "AA", 2, 1);
        int actual = group2.getIdCooridnator();
        int expected = 2;
        assertEquals(expected,actual);
    }

    @Test
    void getIdCurse() {
        Group group2= new Group(1, "AA", 2, 1);
        int actual = group2.getIdCourse();
        int expected = 1;
        assertEquals(expected,actual);
    }



}