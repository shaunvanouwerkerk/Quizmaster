package view;

import database.couchDB.CouchDBaccessUser;
import database.couchDB.UserCouchDAO;
import model.User;

import java.util.ArrayList;

public class CouchDBUserLauncher {

    private CouchDBaccessUser couchDBaccessUser;
    private UserCouchDAO couchUserDAO;

    public CouchDBUserLauncher() {
        this.couchDBaccessUser = new CouchDBaccessUser();
        this.couchUserDAO = new UserCouchDAO(couchDBaccessUser);

    }

    public static void main(String[] args) {
        CouchDBUserLauncher userLauncher = new CouchDBUserLauncher();
        userLauncher.run();
        ArrayList<User> allStudents = userLauncher.couchUserDAO.getAllStudents();
        System.out.println(allStudents);

        //Onderstaande regel run je om alle gebruikers die we hebben aangemaakt in de SQL DB,
        // ook in de CouchDB quizmaster te plaatsen
             //userLauncher.couchUserDAO.saveAllUsersFromSQLDatabase();

    }

    public void run() {
        try {
            couchDBaccessUser.setupConnection();
            System.out.println("Connectie open");
        } catch (Exception exception) {
            System.out.println("Er is iets mis gegaan");
            exception.printStackTrace();
        }
    }
}
