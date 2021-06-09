package database.mysql;

import model.User;
import org.junit.jupiter.api.Test;
import view.Main;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final DBAccess dbAccess = Main.getDBaccess();
    private UserDAO userDAO = new UserDAO(dbAccess);
    private User testUser = new User("grover", "SuperGrover", Main.STUDENT_ROL);


    @Test
    void storeOne() {
//        userDAO.storeOne(testUser);
    }

    int testUserId = testUser.getIdUser();

    @Test
    void getAll() {
    }

    @Test
    void getOneById(int testUserId) {
    }
}