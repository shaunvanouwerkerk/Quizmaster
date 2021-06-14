package view;

import database.couchDB.CouchDBaccess;
import database.couchDB.CouchUserDAO;
import model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CouchDBUserLauncher {

    private CouchDBaccess couchDBaccess;
    private CouchUserDAO couchUserDAO;

    public CouchDBUserLauncher() {
        this.couchDBaccess = new CouchDBaccess();
        this.couchUserDAO = new CouchUserDAO(couchDBaccess);
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
            couchDBaccess.setupConnection();
            System.out.println("Connectie open");
        } catch (Exception exception) {
            System.out.println("Er is iets mis gegaan");
            exception.printStackTrace();
        }
    }
}
