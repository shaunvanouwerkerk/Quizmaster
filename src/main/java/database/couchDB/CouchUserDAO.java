package database.couchDB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.mysql.UserDAO;
import model.User;
import view.Main;

import java.util.ArrayList;

public class CouchUserDAO {

    private CouchDBaccess couchDBaccess;
    private Gson gson;

    public CouchUserDAO(CouchDBaccess couchDBaccess) {
        this.couchDBaccess = couchDBaccess;
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
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        ArrayList<User> users = userDAO.getAll();
        for(User user : users) {
            printUserInJson(user);
        }
    }

    public void saveSingleUser(User user) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(gson.toJson(user)).getAsJsonObject();
        couchDBaccess.saveDocument(jsonObject);
    }

    public void saveAllUsersFromSQLDatabase() {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        ArrayList<User> users = userDAO.getAll();
        for(User user : users) {
            saveSingleUser(user);
        }
    }



}
