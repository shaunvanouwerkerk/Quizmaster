package database.mysql;

import model.Question;

import java.util.ArrayList;

public class QuestionDAO extends AbstractDAO implements GenericDAO{

    public QuestionDAO (DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public void storeOne(Object question) {

    }

    @Override
    public ArrayList getAll() {
        return null;
    }

    @Override
    public Object getOneById(int id) {
        return null;
    }
}
