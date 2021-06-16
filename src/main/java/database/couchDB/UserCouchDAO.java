package database.couchDB;

//Author: Branko Visser


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.User;
import view.Main;
import java.util.ArrayList;
import java.util.List;

public class UserCouchDAO {

    private CouchDBaccessUser couchDBaccessUser;
    private Gson gson;

    public UserCouchDAO(CouchDBaccessUser couchDBaccessUser) {
        this.couchDBaccessUser = couchDBaccessUser;
        this.gson = new Gson();
    }

    //Test methode om te zien of het lukte om een user als Json te printen
    public void printUserInJson(User user) {
        String userJson = gson.toJson(user);
        System.out.println(userJson);
    }

    //Methode om alle users uit de SQL DB te halen en te printen mbv printUser methode
    //deze methode heb ik geschreven om 'te spelen' met couchDB en het te begrijpen
    public void createUserAsJson() {
        database.mysql.UserDAO userDAO = new database.mysql.UserDAO(Main.getDBaccess());
        ArrayList<User> users = userDAO.getAll();
        for(User user : users) {
            printUserInJson(user);
        }
    }

    //Methode om een user op te slaan in couchDB
    public void saveSingleUser(User user) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(gson.toJson(user)).getAsJsonObject();
        couchDBaccessUser.saveDocument(jsonObject);
    }

    //Hulpmethode om alle users uit de SQL db te halen en in CouchDB op te slaan
    public void saveAllUsersFromSQLDatabase() {
        database.mysql.UserDAO userDAO = new database.mysql.UserDAO(Main.getDBaccess());
        ArrayList<User> users = userDAO.getAll();
        for(User user : users) {
            saveSingleUser(user);
        }
    }

    //De methode heb ik uit gecomment omdat die werkt als er in de couchDB alléén users staan. Zodra je meerdere objecten
    //gaat aanmaken in dezelfde DB, werkt dit niet meer.
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        User user = null;
        List<JsonObject> allUsersCouchDb = couchDBaccessUser.getClient()
                .view("_all_docs").includeDocs(true)
                .query(JsonObject.class);
        System.out.println(allUsersCouchDb);
        for(JsonObject object : allUsersCouchDb) {
            user = gson.fromJson(object, User.class);
            allUsers.add(user);
        }
        return allUsers;
    }

    public ArrayList<User> getAllStudents() {
        ArrayList<User> users = getAllUsers();
        ArrayList<User> students = new ArrayList<>();
        for(User user : users) {
            if(user.getRoleName().equals(Main.STUDENT_ROL)) {
                students.add(user);
            }
        }
        return students;
    }

}
