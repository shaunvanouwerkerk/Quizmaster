package view;

import database.couchDB.CouchDBaccess;
import database.couchDB.CouchUserDAO;

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
