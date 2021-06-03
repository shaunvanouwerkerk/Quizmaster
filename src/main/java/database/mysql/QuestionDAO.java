package database.mysql;
/*
 * @Author: Nijad Nazarli
 * */

import model.Question;

import java.util.ArrayList;

public class QuestionDAO extends AbstractDAO implements GenericDAO<Question>{

    public QuestionDAO (DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public void storeOne(Question question) {
    }

    @Override
    public ArrayList getAll() {
        return null;
    }

    @Override
    public Question getOneById(int id) {
        return null;
    }
}
