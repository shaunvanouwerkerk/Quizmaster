package database.couchDB;

import com.google.gson.Gson;

public class CouchUserDAO {

    private CouchDBaccess couchDBaccess;
    private Gson gson;

    public CouchUserDAO(CouchDBaccess couchDBaccess) {
        this.couchDBaccess = couchDBaccess;
        this.gson = new Gson();
    }


}
