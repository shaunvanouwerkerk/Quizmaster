package model;

import org.junit.jupiter.api.Test;
import view.Main;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testToString() {
        User testUser = new User("testWachtwoord", "testNaam", Main.ADMIN_ROL);
        String actual = testUser.toString();
        String expected = "testNaam";
        assertEquals(expected, actual);
    }

    @Test
    void getIdUserTestOne() {
        User testUser = new User("testWachtwoord", "testNaam", Main.STUDENT_ROL);
        int actual = testUser.getIdUser();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getIdUserTestTwo() {
        User testUser = new User(2500,"testWachtwoord", "testNaam", Main.STUDENT_ROL);
        int actual = testUser.getIdUser();
        int expected = 2500;
        assertEquals(expected, actual);
    }

    @Test
    void getPassword() {
        User testUser = new User(2500,"testWachtwoord", "testNaam", Main.STUDENT_ROL);
        String actual = testUser.getPassword();
        String expected = "testWachtwoord";
        assertEquals(actual, expected);
    }
}