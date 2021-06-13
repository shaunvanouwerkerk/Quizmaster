package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    void testToString() {
        Group testGroup= new Group(1, "ProgGr1", 1,1);
        String actual = testGroup.toString();
        String expected = "Group id: 1";
        assertEquals(expected, actual);
    }

    @Test
    void getIdGroup() {
    }

    @Test
    void setIdGroup() {
    }

}